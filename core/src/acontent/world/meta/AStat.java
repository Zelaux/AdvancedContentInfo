package acontent.world.meta;

import arc.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import mindustry.world.meta.*;

import java.util.*;

public class AStat implements Comparable{
    private static final ObjectMap<String, AStat> statMap = new ObjectMap<>();
    private static final Seq<AStat> allStats = new Seq<>();

    static{
        for(Stat value : Stat.values()){
            get(value);
        }
    }

    public final AStatCat category;
    public final String name;
    public final Stat stat;

    private AStat(String name, AStatCat category, int index){
        this.name = name;
        this.category = category;
        if(index < 0 || index > allStats.size){
            allStats.add(this);
        }else{
            allStats.insert(index, this);
        }
        stat = Structs.find(Stat.values(), other -> other.name().equals(name()));
    }

    public static Seq<AStat> getAllStats(){
        return allStats.copy();
    }

    public static AStat get(String name, AStatCat category, int index){
        return statMap.get(name.toLowerCase(Locale.ROOT), () -> new AStat(name, category, index));
    }


    public static AStat get(String name, AStatCat category, Stat nearby){
        return get(name, category, nearby, Offset.defaultOffset);
    }

    public static AStat get(String name, AStatCat category, Stat nearby, Offset offset){
        return get(name, category, get(nearby), offset);
    }

    public static AStat get(String name, AStatCat category, AStat nearby, Offset offset){
        return get(name, category, offset.calculateIndex(nearby.index()));
    }

    public static AStat get(String name, AStatCat category){
        return get(name, category, -1);
    }

    public static AStat get(String name, String category){
        return get(name, AStatCat.get(category));
    }

    public static AStat get(String name, String category, int index){
        return get(name, AStatCat.get(category), index);
    }

    public static AStat get(Stat stat){
        return get(stat.name(), stat.category, stat.ordinal());
    }

    public static AStat get(String name, StatCat category){
        return get(name, category.name());
    }

    public static AStat get(String name, StatCat category, int index){
        return get(name, category.name(), index);
    }

    public static AStat get(String name, StatCat category, Stat nearby){
        return get(name, category, get(nearby));
    }

    public static AStat get(String name, StatCat category, AStat nearby){
        return get(name, category, nearby, Offset.defaultOffset);
    }

    public static AStat get(String name, StatCat category, Stat nearby, Offset offset){
        return get(name, category, get(nearby), offset);
    }

    public static AStat get(String name, StatCat category, AStat nearby, Offset offset){
        return get(name, category, offset.calculateIndex(nearby.index()));
    }

    public String name(){
        return name;
    }

    public String localized(){
        return Core.bundle.get("stat." + name().toLowerCase(Locale.ROOT));
    }

    public int index(){
        return allStats.indexOf(this);
    }


    /**
     * @return stat with same name in enum Stat or null
     */
    public Stat toStat(){
        return stat;
    }

    @Override
    public int compareTo(Object o){
        if(o instanceof AStat){
            AStat other = (AStat)o;
            return Mathf.clamp(index() - other.index(), -1, 1);
        }
        return 0;
    }

    @Override
    public String toString(){
        return name();
    }
}
