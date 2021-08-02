package acontent.ui;

import acontent.world.meta.AStat;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import arc.struct.Seq;
import arc.util.Scaling;
import acontent.world.meta.AStatCat;
import acontent.world.meta.AStats;
import mindustry.Vars;
import mindustry.ctype.UnlockableContent;
import mindustry.graphics.Pal;
//import mindustry.ui.Cicon;
import mindustry.ui.dialogs.ContentInfoDialog;
import mindustry.world.meta.StatValue;

public class AdvancedContentInfoDialog extends ContentInfoDialog {
    public static void init(){
        Vars.ui.content.remove();
        Vars.ui.content=new AdvancedContentInfoDialog();

    }
    public AdvancedContentInfoDialog() {
        super();
    }

    @Override
    public void show(UnlockableContent content) {
        if (!(content.stats instanceof AStats)) {
            super.show(content);
            return;
        }
        this.cont.clear();
        Table table = new Table();
        table.margin(10.0F);
        content.checkStats();
        table.table((title1) -> {
            int size = 48;
            title1.image(content.fullIcon).size((float)size).scaling(Scaling.fit);
            title1.add("[accent]" + content.localizedName).padLeft(5.0F);
        });
        table.row();
        if (content.description != null) {
            boolean any = ((AStats) content.stats).toAMap().size > 0;
            if (any) {
                table.add("@category.purpose").color(Pal.accent).fillX().padTop(10.0F);
                table.row();
            }

            table.add("[lightgray]" + content.displayDescription()).wrap().fillX().padLeft(any ? 10.0F : 0.0F).width(500.0F).padTop(any ? 0.0F : 10.0F).left();
            table.row();
            if (!content.stats.useCategories && any) {
                table.add("@category.general").fillX().color(Pal.accent);
                table.row();
            }
        }

        AStats stats = (AStats)content.stats;
        ObjectMap.Keys<AStatCat> var4 = stats.toAMap().keys().iterator();

        while(true) {
            AStatCat cat;
            OrderedMap<AStat, Seq<StatValue>> map;
            do {
                if (!var4.hasNext()) {
                    if (content.details != null) {
                        table.add("[gray]" + content.details).pad(6.0F).padTop(20.0F).width(400.0F).wrap().fillX();
                        table.row();
                    }

                    ScrollPane pane = new ScrollPane(table);
                    this.cont.add(pane);
                    this.show();
                    return;
                }

                cat = var4.next();
                map=stats.toAMap().get(cat);
            } while(map.size == 0);

            if (stats.useCategories) {
                table.add("@category." + cat.name()).color(Pal.accent).fillX();
                table.row();
            }

            ObjectMap.Keys<AStat> var7 = map.keys().iterator();
            OrderedMap<AStat, Seq<StatValue>> mapCathe=map;
            while(var7.hasNext()) {
                AStat stat = var7.next();
                table.table((inset) -> {
                    inset.left();
                    inset.add("[lightgray]" + stat.localized() + ":[] ").left();
                    Seq<StatValue> arr = mapCathe.get(stat);

                    for (StatValue value : arr) {
                        value.display(inset);
                        inset.add().size(10.0F);
                    }

                }).fillX().padLeft(10.0F);
                table.row();
            }
        }
    }
}
