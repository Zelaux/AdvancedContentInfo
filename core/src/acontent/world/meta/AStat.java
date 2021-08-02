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

    public AStat(String name, AStatCat category) {
        nameProvider = () -> name;
        this.category = category;
    }

    public AStat(String name, StatCat category) {
        this(name, AStatCat.get(category));
    }

    public AStat(String name, String category) {
        this(name, AStatCat.get(category));
    }

    public AStat(String name) {
        nameProvider = () -> name;
        this.category = AStatCat.general;
    }

    public AStat(Stat stat) {
        nameProvider = stat::name;
        category = AStatCat.get(stat.category);
    }

    public static AStat fromExist(Stat stat) {
        return new AStat(stat);
    }

    public String localized() {
        return Core.bundle.get("stat." + category.name().toLowerCase(Locale.ROOT));
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
