package acontent.world.meta;

import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.world.meta.*;

public class AStats extends Stats{
    @Nullable
    private OrderedMap<AStatCat, OrderedMap<AStat, Seq<StatValue>>> aMap;
    private boolean dirty;

    public AStats() {
    }
    @Override
    public void add(Stat stat, float value, StatUnit unit) {
        this.add(stat, (AStatValues.number (value, unit)));
    }

    public void add(Stat stat, float value, AStatUnit unit) {
        this.add(stat, (AStatValues.number (value, unit)));
    }

    @Override
    public void add(Stat stat, float value) {
        this.add(stat, value, StatUnit.none);
    }

    @Override
    public void addPercent(Stat stat, float value) {
        this.add(stat, (AStatValues.number((float)((int)(value * 100.0F)), StatUnit.percent)));
    }

    @Override
    public void add(Stat stat, boolean value) {
        this.add(stat, (AStatValues.bool(value)));
    }

    @Override
    public void add(Stat stat, Item item) {
        this.add(stat, (AStatValues.items(new ItemStack(item, 1))));
    }

    @Override
    public void add(Stat stat, ItemStack item) {
        this.add(stat, (AStatValues.items(item)));
    }

    @Override
    public void add(Stat stat, Liquid liquid, float amount, boolean perSecond) {
        this.add(stat, (AStatValues.liquid(liquid, amount, perSecond)));
    }

    @Override
    public void add(Stat stat, Attribute attr) {
        this.add(stat, attr, false, 1.0F, false);
    }
    @Override
    public void add(Stat stat, Attribute attr, float scale) {
        this.add(stat, attr, false, scale, false);
    }

    @Override
    public void add(Stat stat, Attribute attr, boolean floating) {
        this.add(stat, attr, floating, 1.0F, false);
    }
    public void add(AStat stat, float value, StatUnit unit) {
        this.add(stat, (AStatValues.number(value, unit)));
    }
    public void add(AStat stat, float value, AStatUnit unit) {
        this.add(stat, (AStatValues.number(value, unit)));
    }

    public void add(AStat stat, float value) {
        this.add(stat, value, StatUnit.none);
    }

    public void addPercent(AStat stat, float value) {
        this.add(stat, (AStatValues.number((float)((int)(value * 100.0F)), StatUnit.percent)));
    }

    public void add(AStat stat, boolean value) {
        this.add(stat, (AStatValues.bool(value)));
    }

    public void add(AStat stat, Item item) {
        this.add(stat, (AStatValues.items(new ItemStack(item, 1))));
    }

    public void add(AStat stat, ItemStack item) {
        this.add(stat, (AStatValues.items(item)));
    }

    public void add(AStat stat, Liquid liquid, float amount, boolean perSecond) {
        this.add(stat, (AStatValues.liquid(liquid, amount, perSecond)));
    }

    public void add(AStat stat, Attribute attr) {
        this.add(stat, attr, false, 1.0F, false);
    }

    public void add(AStat stat, Attribute attr, float scale) {
        this.add(stat, attr, false, scale, false);
    }

    public void add(AStat stat, Attribute attr, boolean floating) {
        this.add(stat, attr, floating, 1.0F, false);
    }

    public void add(AStat stat, Attribute attr, boolean floating, float scale, boolean startZero) {
        add(stat, StatValues.floors(attr, floating, scale, startZero));

    }
    @Override
    public void add(Stat stat, Attribute attr, boolean floating, float scale, boolean startZero) {
        add(AStat.get(stat),attr,floating,scale,startZero);

    }

    @Override
    public void add(Stat stat, String format, Object... args) {
        this.add(stat, (AStatValues.string(format, args)));
    }
    public void add(AStat stat, String format, Object... args) {
        this.add(stat, (AStatValues.string(format, args)));
    }

    public void add(Stat stat, StatValue value) {
        add(AStat.get(stat),value);
    }
    public void add(AStat stat, StatValue value) {
        if (stat.toStat()!=null){
            super.add(stat.toStat(),value);
        }
        if (aMap == null) {
            aMap = new OrderedMap<>();
        }

        if (!aMap.containsKey(stat.category)) {
            aMap.put(stat.category, new OrderedMap<>());
        }

        aMap.get(stat.category).get(stat, Seq::new).add(value);
        dirty = true;
    }

    @Override
    public void remove(Stat stat) {
//        super.remove(stat);
        remove(AStat.get(stat));
    }
    public void remove(AStat stat) {
        if (stat.toStat()!=null){
            super.remove(stat.toStat());
        }
        if (aMap == null) {
            aMap = new OrderedMap<>();
        }

        AStatCat category = stat.category;
        if (aMap.containsKey(category) && aMap.get(category).containsKey(stat)) {
            this.aMap.get(category).remove(stat);
            dirty = true;
        } else {
            throw new RuntimeException("No stat entry found: \"" + stat + "\" in block.");
        }
    }

    public OrderedMap<AStatCat, OrderedMap<AStat, Seq<StatValue>>> toAMap(){
        if(aMap == null) aMap = new OrderedMap<>();

        //sort stats by index if they've been modified
        if(dirty){
            aMap.orderedKeys().sort();
            for(ObjectMap.Entry<AStatCat, OrderedMap<AStat, Seq<StatValue>>> entry : aMap.entries()){
                entry.value.orderedKeys().sort();
            }

            dirty = false;
        }
        return aMap;
    }

    public Stats copy(Stats stats) {
        this.useCategories=stats.useCategories;
        this.intialized=stats.intialized;
        return this;
    }
}
