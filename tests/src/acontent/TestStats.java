package acontent;

import acontent.world.meta.*;
import mindustry.world.meta.*;

class TestStats{
    public static final AStatCat amongusCat = AStatCat.get("amongusCat", Offset.after.calculateIndex(StatCat.general));
    public static final AStatCat amongusCat1 = AStatCat.get("amongusCat1", StatCat.general, Offset.after);

    public static final AStat amongus = AStat.get("amongus", StatCat.general, Offset.after.calculateIndex(Stat.buildTime));
    public static final AStat amongus1 = AStat.get("amongus1", StatCat.general, Stat.buildTime, Offset.after);
    public static final AStat amongus2 = AStat.get("amongus2", amongusCat, Offset.after.calculateIndex(Stat.buildTime));
    public static final AStat amongus3 = AStat.get("amongus3", amongusCat1, Stat.buildTime, Offset.after);
}
