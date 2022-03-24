package acontent;

import acontent.ui.*;
import acontent.world.meta.*;
import arc.util.*;
import mindustry.mod.*;
import mindustry.type.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.meta.*;

public class TestMod extends Mod{
    @Override
    public void init(){
        super.init();
        Log.info("aci-test.init()");
        AdvancedContentInfoDialog.init();
    }

    public TestMod(){
        Log.info("aci-test.TEST_MOD");
    }

    @Override
    public void loadContent(){
        Log.info("aci-test.loadContent()");
        new TestBlock("debug-block"){
            {
                requirements(Category.distribution, ItemStack.empty, true);
                size = 2;
            }

            @Override
            public void setStats(){
                super.setStats();
                aStats.add(TestStats.amongus, 0);
                aStats.add(TestStats.amongus1, 1);
                aStats.add(TestStats.amongus2, 2);
                aStats.add(TestStats.amongus3, 3);
            }
        };
    }
}
