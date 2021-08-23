package acontent.world.meta;

import arc.Core;
import arc.struct.ObjectMap;
import arc.util.*;
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
    private final StatUnit statUnit;


    private AStatUnit(String name,boolean space){
        this.space = space;
        this.name=name;
        statUnit= Structs.find(StatUnit.values(),other->other.name().equals(name()));
    }
    public static AStatUnit get(StatUnit category) {
        return get(category.name(),category.space);
    }
    public static AStatUnit get(String name,boolean space) {
        return staUnitMap.get(name.toLowerCase(Locale.ROOT), () -> new AStatUnit(name,space));
    }
    public static AStatUnit get(String name) {
        return get(name,true);
    }


    public String name() {
        return name;
    }
    public String localized(){
        if(name().equals(StatUnit.none.name())) return "";
        return Core.bundle.get("unit." + name().toLowerCase(Locale.ROOT));
    }

    /**
     * @return statUnit with same name in enum StatUnit or null
     */
    public StatUnit toStatUnit(){
        return statUnit;
    }
    @Override
    public String toString() {
        return name();
    }
}
