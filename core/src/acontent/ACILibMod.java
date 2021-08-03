package acontent;

import acontent.ui.AdvancedContentInfoDialog;
import acontent.world.meta.AStat;
import acontent.world.meta.AStatCat;
import acontent.world.meta.AStats;
import mindustry.Vars;
import mindustry.mod.Mod;
import mindustry.world.Block;
import mindustry.world.meta.*;

import static acontent.ACIVars.*;

public class ACILibMod extends Mod {


    public ACILibMod() {
        aciInfo = Vars.mods.getMod(getClass());
    }

    public void init() {
        if (!loaded) return;
        AdvancedContentInfoDialog.init();
    }

    public void loadContent() {
        aciInfo = Vars.mods.getMod(this.getClass());
        loaded = true;
        if (false) {
            new Block("test-block"){
                AStats aStats=new AStats();
                {
                    stats=aStats.copy(stats);
                    destructible=true;
                    update=true;
                    localizedName="Example Block";
                    buildVisibility= BuildVisibility.sandboxOnly;
                }

                @Override
                public void setStats() {
                    super.setStats();
                    StatValue yourStatValue = StatValues.number(-1, StatUnit.none);

                    aStats.add(AStat.get("with_index", StatCat.general, Stat.health.ordinal() + 1), yourStatValue);
                    aStats.add(AStat.get("without_index", StatCat.general), yourStatValue);
                    //StatCat.optional is last StatCat
                    AStatCat preGeneral = AStatCat.get("preGeneral.with_index", StatCat.general.ordinal());
                    AStatCat afterOptional = AStatCat.get("afterOptional.without_index");
                    aStats.add(AStat.get("preGeneral.stat", preGeneral, Stat.health.ordinal() + 1), yourStatValue);
                    aStats.add(AStat.get("afterOptional.stat", afterOptional), yourStatValue);
                }
            };
        }
    }
}
