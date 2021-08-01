package acontent.world.meta;

import arc.func.Prov;
import mindustry.world.meta.StatCat;

public class AStatCat {
    public static AStatCat general=new AStatCat(StatCat.general);
    private final Prov<String> nameProvider;
    public AStatCat(StatCat statCat) {
        nameProvider=()->statCat.name();
    }
    public AStatCat(String name) {
        nameProvider=()->name;
    }

    public static AStatCat fromExist(StatCat category) {
        return new AStatCat(category);
    }

    public String name() {
        return nameProvider.get();
    }
}
