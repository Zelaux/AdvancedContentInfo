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
    public final AStatCat category;
    private final Prov<String> nameProvider;

    private AStat(String name, AStatCat category) {
        nameProvider = () -> name;
        this.category = category;
        allStats.add(this);
    }

    public static AStat get(String name, AStatCat category) {
        return statMap.get(name, () -> new AStat(name, category));
    }

    public static AStat get(String name, String category) {
        return get(name, AStatCat.get(category));
    }

    public static AStat get(Stat stat) {
        return get(stat.name(), stat.category);
    }

    public static AStat get(String name, StatCat category) {
        return get(name, category.name());
    }

    public String name() {
        return nameProvider.get();
    }

    public String localized() {
        return Core.bundle.get("stat." + name().toLowerCase(Locale.ROOT));
    }

    protected int index() {
        return allStats.indexOf(this);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof AStatCat) {
            AStatCat other = (AStatCat) o;
            return Mathf.clamp(index() - other.index(), -1, 1);
        }
        return 0;
    }
}
