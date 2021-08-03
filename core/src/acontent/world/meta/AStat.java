package acontent.world.meta;

import arc.Core;
import arc.func.Prov;
import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

import java.util.Locale;

public class AStat implements Comparable {
    private static final ObjectMap<String, AStat> statMap = new ObjectMap<>();
    private static final Seq<AStat> allStats = new Seq<>();

    static {
        for (Stat value : Stat.values()) {
            get(value);
        }
    }

    public final AStatCat category;
    private final Prov<String> nameProvider;

    private AStat(String name, AStatCat category, int index) {
        nameProvider = () -> name;
        this.category = category;
        if (index < 0 || index > allStats.size) {
            allStats.add(this);
        } else {
            allStats.insert(index, this);
        }
    }

    public static Seq<AStat> getAllStats() {
        return allStats.copy();
    }

    public static AStat get(String name, AStatCat category, int index) {
        return statMap.get(name.toLowerCase(Locale.ROOT), () -> new AStat(name, category, index));
    }

    public static AStat get(String name, AStatCat category) {
        return get(name, category, -1);
    }

    public static AStat get(String name, String category) {
        return get(name, AStatCat.get(category));
    }

    public static AStat get(String name, String category, int index) {
        return get(name, AStatCat.get(category), index);
    }

    public static AStat get(Stat stat) {
        return get(stat.name(), stat.category, stat.ordinal());
    }

    public static AStat get(String name, StatCat category) {
        return get(name, category.name());
    }

    public static AStat get(String name, StatCat category, int index) {
        return get(name, category.name(), index);
    }

    public String name() {
        return nameProvider.get();
    }

    public String localized() {
        return Core.bundle.get("stat." + name().toLowerCase(Locale.ROOT));
    }

    public int index() {
        return allStats.indexOf(this);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof AStat) {
            AStat other = (AStat) o;
            return Mathf.clamp(index() - other.index(), -1, 1);
        }
        return 0;
    }
}
