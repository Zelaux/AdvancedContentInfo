package acontent.world.meta;

import acontent.annotations.ACIAnnotations;
import arc.Core;
import arc.func.Prov;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

import java.util.Locale;

@ACIAnnotations.CustomStat
public class CustomStat {
    public final AStatCat category;
    CustomStat maxConnections = new CustomStat("maxConnections",StatCat.function),
            recipes = new CustomStat("recipes",StatCat.function),
            gasCapacity = new CustomStat("gasCapacity","gasses");
    private final Prov<String> nameProvider;
    public CustomStat(String name,AStatCat category) {
        nameProvider=()->name;
        this.category = category;
    }
    public CustomStat(String name,StatCat category) {
        this(name,new AStatCat(category));
    }
    public CustomStat(String name,String category) {
        this(name,new AStatCat(category));
    }

    public CustomStat(String name) {
        nameProvider=()->name;
        this.category = AStatCat.general;
    }

    public CustomStat(Stat stat) {
        nameProvider=stat::name;
        category=new AStatCat(stat.category);
    }

    public static CustomStat fromExist(Stat stat) {
        return new CustomStat(stat);
    }

    public String localized() {
        return Core.bundle.get("stat." + category.name().toLowerCase(Locale.ROOT));
    }
}
