package acontent.ui;

import acontent.world.meta.AStat;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import arc.util.*;
import acontent.world.meta.AStatCat;
import acontent.world.meta.AStats;
import mindustry.Vars;
import mindustry.ctype.UnlockableContent;
import mindustry.graphics.Pal;
//import mindustry.ui.Cicon;
import mindustry.ui.dialogs.ContentInfoDialog;
import mindustry.world.meta.*;

import static mindustry.Vars.iconXLarge;

public class AdvancedContentInfoDialog extends ContentInfoDialog {
    final         ContentInfoDialog parent;
    public static void init(){
        Vars.ui.content.remove();
        ContentInfoDialog parent = Vars.ui.content;
        Log.info("parent: @",parent.getClass());
        Vars.ui.content=null;
        Vars.ui.content=new AdvancedContentInfoDialog(parent);

    }
    private AdvancedContentInfoDialog(ContentInfoDialog parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void show(UnlockableContent content) {
        boolean useAStats = content.stats instanceof AStats;
        if (!useAStats) {
            parent.show(content);
            return;
        }
        cont.clear();

        Table table = new Table();
        table.margin(10);

        AStats stats = (AStats)content.stats;
        content.checkStats();

        table.table(title1 -> {
            title1.image(content.uiIcon).size(iconXLarge).scaling(Scaling.fit);
            title1.add("[accent]" + content.localizedName).padLeft(5);
        });

        table.row();

        if(content.description != null){
            boolean any = stats.toAMap().size > 0;

            if(any){
                table.add("@category.purpose").color(Pal.accent).fillX().padTop(10);
                table.row();
            }

            table.add("[lightgray]" + content.displayDescription()).wrap().fillX().padLeft(any ? 10 : 0).width(500f).padTop(any ? 0 : 10).left();
            table.row();

            if(!stats.useCategories && any){
                table.add("@category.general").fillX().color(Pal.accent);
                table.row();
            }
        }

        stats.display(table);

        if(content.details != null){
            table.add("[gray]" + content.details).pad(6).padTop(20).width(400f).wrap().fillX();
            table.row();
        }

        ScrollPane pane = new ScrollPane(table);
        cont.add(pane);
        show();
    }
}
