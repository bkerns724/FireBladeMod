// Keeping this unneeded code in case I remove the x cost card.
/*
package FireBlade.patches;

import FireBlade.enums.FireBladeEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "initializeRelicList"
)

public class ChemicalExPatch {
    public static void Prefix(AbstractDungeon __instance) {
        if (AbstractDungeon.player.chosenClass == FireBladeEnum.THE_FIREBLADE) {
            AbstractDungeon.relicsToRemoveOnStart.add(ChemicalX.ID);
        }
    }
}
*/