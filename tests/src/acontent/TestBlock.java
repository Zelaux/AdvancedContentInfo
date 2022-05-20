package acontent;


import acontent.world.meta.*;
import mindustry.world.*;
import mindustry.world.meta.*;

public class TestBlock extends Block{
    public AStats aStats = new AStats();

    public TestBlock(String name){
        super(name);
        stats = aStats.copy(stats);
    }

    @Override
    public void setStats(){
        super.setStats();
//        StatValue your_statValue = StatValues.string("your statValue");
        for(Stat stat : Stat.all){
            aStats.add(stat, StatValues.string(stat.name));
        }
//        aStats.add(AStat.get("your_stat","your_category"), your_statValue);
//        aStats.add(AStat.get("your_stat1", AStatCat.get("your_category2")), your_statValue);
//        aStats.add(AStat.get("your_stat2", StatCat.function), your_statValue);
//        aStats.add(Stat.health, your_statValue);
    }
}