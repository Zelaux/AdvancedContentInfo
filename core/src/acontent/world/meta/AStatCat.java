package acontent.world.meta;

import arc.func.Prov;
import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import mindustry.world.meta.StatCat;

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

    private final Prov<String> nameProvider;

    private AStatCat(String name, int index) {
        nameProvider = () -> name;
//        statCatMap.put(name, this);
        if (index < 0 || index > allStatCats.size) {
            allStatCats.add(this);
        } else {
            allStatCats.insert(index, this);
        }
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

    public String name() {
        return nameProvider.get();
    }

    public int index() {
        return allStatCats.indexOf(this);
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
