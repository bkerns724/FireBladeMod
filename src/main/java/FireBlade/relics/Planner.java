package FireBlade.relics;

import FireBlade.cards.TheFireBladeCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Planner extends CustomRelic {
    public static final String ID = "FireBladeMod:Planner";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/Planner.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Planner_outline.png";
    private static final RelicTier TIER = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public Planner() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void atBattleStart() {
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.hasTag(TheFireBladeCardTags.ENDURANCE)) {
                card.baseBlock -= 2;
                card.selfRetain = true;
                card.rawDescription = "Retain. NL " + card.rawDescription;
                card.initializeDescription();
            }
        }
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }

    public AbstractRelic makeCopy() { return new Planner(); }
}
