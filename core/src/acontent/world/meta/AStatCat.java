package acontent.world.meta;

import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.*;
import mindustry.world.meta.*;

import java.util.Locale;

public class AStatCat implements Comparable {
    private static final ObjectMap<String, AStatCat> statCatMap = new ObjectMap<>();
    private static final Seq<AStatCat> allStatCats = new Seq<>();
    public static Seq<AStatCat> getAllStatCats(){
        return allStatCats.copy();
    }
    static {
        for (StatCat value : StatCat.values()) {
            get(value);
        }
    }

    public final String name;
    public final StatCat statCat;

    private AStatCat(String name, int index) {
        this.name=name;
//        statCatMap.put(name, this);
        if (index < 0 || index > allStatCats.size) {
            allStatCats.add(this);
        } else {
            allStatCats.insert(index, this);
        }
        statCat = Structs.find(StatCat.values(), other->other.name().equals(name()));
    }

    public static AStatCat get(StatCat category) {
        return get(category.name());
    }
    public static AStatCat get(StatCat category,int index) {
        return get(category.name(),index);
    }

    public static AStatCat get(String category) {
        return get(category,-1);
    }
    public static AStatCat get(String category,int index) {
        return statCatMap.get(category.toLowerCase(Locale.ROOT), () -> new AStatCat(category,index));
    }
    public static AStatCat get(String category,StatCat nearby) {
        return get(category,nearby, Offset.defaultOffset);
    }
    public static AStatCat get(String category, StatCat nearby, Offset offset) {
        return get(category,get(nearby),offset);
    }

    public static AStatCat get(String category,AStatCat nearby) {
        return get(category,nearby, Offset.defaultOffset);
    }
    public static AStatCat get(String category, AStatCat nearby, Offset offset) {
        return get(category,offset.calculateIndex(nearby.index()));
    }


    public String name() {
        return name;
    }

    public int index() {
        return allStatCats.indexOf(this);
    }

    /**
     * @return statCat with same name in enum StatCat or null
     */
    public StatCat toStatCat(){
        return statCat;
    }
    @Override
    public int compareTo(Object o) {
        if (o instanceof AStatCat) {
            AStatCat other = (AStatCat) o;
            return Mathf.clamp(index() - other.index(), -1, 1);
        }
        return 0;
    }
    @Override
    public String toString() {
        return name();
    }
}
