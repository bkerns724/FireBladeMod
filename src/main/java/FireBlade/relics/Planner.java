package FireBlade.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Planner extends CustomRelic {
    public static final String ID = "FireBladeMod:Planner";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/Planner.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Planner_outline.png";
    private static final RelicTier TIER = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int dexLoss = 2;

    public Planner() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hand.isEmpty() && !p.hasRelic("Runic Pyramid") && !p.hasPower("Equilibrium")) {
            this.addToBot(new RetainCardsAction(p, 1));
        }
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }

    public AbstractRelic makeCopy() { return new Planner(); }
}
