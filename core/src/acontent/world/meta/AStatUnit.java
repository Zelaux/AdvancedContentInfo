package acontent.world.meta;

import arc.Core;
import arc.struct.ObjectMap;
import arc.util.*;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;

import java.util.Locale;
/** @deprecated use {@link mindustry.world.meta.StatUnit}*/
@Deprecated
public class AStatUnit extends StatUnit {
    @Deprecated
    public AStatUnit(String name, boolean space){
        super(name, space);
    }

    public AStatUnit(String name){
        super(name);
    }
}
