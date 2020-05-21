package FireBlade.relics;

import FireBlade.powers.VitalityPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BladeMasterLocket extends CustomRelic {
    public static final String ID = "FireBladeMod:BladeMasterLocket";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/BladeMasterLocket.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Locket_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int VV_AMOUNT = 3;

    public BladeMasterLocket() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void obtain() {
        // Below statement should always be true if this relic is being obtained.
        if (AbstractDungeon.player.hasRelic(DuelistLocket.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if ((AbstractDungeon.player.relics.get(i)).relicId.equals(DuelistLocket.ID)) {
                    instantObtain(AbstractDungeon.player, i,true);
                    break;
                }
            }
        }
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, VV_AMOUNT), VV_AMOUNT));
        addToBot(new ApplyPowerAction(p, p, new VitalityPower(p, VV_AMOUNT), VV_AMOUNT));
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + VV_AMOUNT + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new BladeMasterLocket(); }

    public boolean canSpawn() { return AbstractDungeon.player.hasRelic(DuelistLocket.ID); }
}
