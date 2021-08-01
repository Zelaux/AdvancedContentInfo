package acontent;

import ModVars.GasVars;
import acontent.ui.AdvancedContentInfoDialog;
import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.ctype.Content;
import mindustry.ctype.MappableContent;
import mindustry.ctype.UnlockableContent;
import mindustry.mod.Mod;

import static ModVars.modFunc.*;
import static ModVars.GasVars.*;
import static mindustry.Vars.*;

public class ACILibMod extends Mod {


    public ACILibMod() {
        modInfo = Vars.mods.getMod(getClass());
        GasVars.load();
    }

    public void init() {
        if (!loaded) return;
        AdvancedContentInfoDialog.init();
    }

    public void loadContent() {
        modInfo = Vars.mods.getMod(this.getClass());
        loaded = true;
    }
}
