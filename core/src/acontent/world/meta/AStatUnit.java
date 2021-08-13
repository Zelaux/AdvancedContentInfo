package acontent.world.meta;

import arc.Core;
import arc.struct.ObjectMap;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;

import java.util.Locale;

public class AStatUnit {
    private static final ObjectMap<String, AStatUnit> staUnitMap = new ObjectMap<>();
    static {
        for (StatUnit value : StatUnit.values()) {
            get(value);
        }
    }
    public final boolean space;
    private final String name;

    public String name() {
        return name;
    }

    public static AStatUnit get(StatUnit category) {
        return get(category.name());
    }
    public static AStatUnit get(String name) {
        return staUnitMap.get(name.toLowerCase(Locale.ROOT), () -> new AStatUnit(name));
    }
    private AStatUnit(String name,boolean space){
        this.space = space;
        this.name=name;
    }

    private  AStatUnit(String name){
        this(name,true);
    }

    public String localized(){
        if(name().equals(StatUnit.none.name())) return "";
        return Core.bundle.get("unit." + name().toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString() {
        return name();
    }
}
