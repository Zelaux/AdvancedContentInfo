package acontent.world.meta;

import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.Vars;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.meta.*;

import java.util.Iterator;

public class AStats extends Stats{
    @Nullable
    private OrderedMap<AStatCat, OrderedMap<AStat, Seq<StatValue>>> aMap;
    private boolean dirty;

    public AStats() {
    }
    @Override
    public void add(Stat stat, float value, StatUnit unit) {
        this.add(stat, (StatValues.number (value, unit)));
    }

    @Override
    public void add(Stat stat, float value) {
        this.add(stat, value, StatUnit.none);
    }

    @Override
    public void addPercent(Stat stat, float value) {
        this.add(stat, (StatValues.number((float)((int)(value * 100.0F)), StatUnit.percent)));
    }

    @Override
    public void add(Stat stat, boolean value) {
        this.add(stat, (StatValues.bool(value)));
    }

    @Override
    public void add(Stat stat, Item item) {
        this.add(stat, (StatValues.items(new ItemStack(item, 1))));
    }

    @Override
    public void add(Stat stat, ItemStack item) {
        this.add(stat, (StatValues.items(item)));
    }

    @Override
    public void add(Stat stat, Liquid liquid, float amount, boolean perSecond) {
        this.add(stat, (StatValues.liquid(liquid, amount, perSecond)));
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
        this.add(stat, (StatValues.number(value, unit)));
    }

    public void add(AStat stat, float value) {
        this.add(stat, value, StatUnit.none);
    }

    public void addPercent(AStat stat, float value) {
        this.add(stat, (StatValues.number((float)((int)(value * 100.0F)), StatUnit.percent)));
    }

    public void add(AStat stat, boolean value) {
        this.add(stat, (StatValues.bool(value)));
    }

    public void add(AStat stat, Item item) {
        this.add(stat, (StatValues.items(new ItemStack(item, 1))));
    }

    public void add(AStat stat, ItemStack item) {
        this.add(stat, (StatValues.items(item)));
    }

    public void add(AStat stat, Liquid liquid, float amount, boolean perSecond) {
        this.add(stat, (StatValues.liquid(liquid, amount, perSecond)));
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
        Iterator<Block> var6 = Vars.content.blocks().select((blockx) -> {
            Floor f;

            return blockx instanceof Floor && (f = (Floor)blockx) == blockx && f.attributes.get(attr) != 0.0F && (!f.isLiquid || floating);
        }).<Block>as().with((s) -> {
            s.sort((f) -> {
                return ((Floor)f).attributes.get(attr);
            });
        }).iterator();

        while(var6.hasNext()) {
            Floor block = (Floor)var6.next();

            this.add(stat, ( StatValues.floorEfficiency(block, block.attributes.get(attr) * scale, startZero)));
        }

    }
    @Override
    public void add(Stat stat, Attribute attr, boolean floating, float scale, boolean startZero) {
        super.add(stat,attr,floating,scale,startZero);
        add(AStat.fromExist(stat),attr,floating,scale,startZero);

    }

    @Override
    public void add(Stat stat, String format, Object... args) {
        this.add(stat, (StatValues.string(format, args)));
    }
    public void add(AStat stat, String format, Object... args) {
        this.add(stat, (StatValues.string(format, args)));
    }

    public void add(Stat stat, StatValue value) {
        super.add(stat,value);
        add(AStat.fromExist(stat),value);
    }
    public void add(AStat stat, StatValue value) {
        if (this.aMap == null) {
            this.aMap = new OrderedMap<>();
        }

        if (!this.aMap.containsKey(stat.category)) {
            this.aMap.put(stat.category, new OrderedMap<>());
        }

        ((Seq)((OrderedMap)this.aMap.get(stat.category)).get(stat, Seq::new)).add(value);
        this.dirty = true;
    }

    @Override
    public void remove(Stat stat) {
        if (this.aMap == null) {
            this.aMap = new OrderedMap<>();
        }

        AStatCat category = AStatCat.get(stat.category);
        if (this.aMap.containsKey(category) && ((OrderedMap)this.aMap.get(category)).containsKey(stat)) {
            ((OrderedMap)this.aMap.get(category)).remove(stat);
            this.dirty = true;
        } else {
            throw new RuntimeException("No stat entry found: \"" + stat + "\" in block.");
        }
    }

    public OrderedMap<AStatCat, OrderedMap<AStat, Seq<StatValue>>> toAMap() {
        if (this.aMap == null) {
            this.aMap = new OrderedMap<>();
        }

        if (this.dirty) {
            this.aMap.orderedKeys().sort();
            ObjectMap.Entries var1 = this.aMap.entries().iterator();

            while(var1.hasNext()) {
                ObjectMap.Entry<StatCat, OrderedMap<Stat, Seq<StatValue>>> entry = var1.next();
                ((OrderedMap)entry.value).orderedKeys().sort();
            }

            this.dirty = false;
        }

        return this.aMap;
    }

    public Stats copy(Stats stats) {
        this.useCategories=stats.useCategories;
        this.intialized=stats.intialized;
        return this;
    }
}
