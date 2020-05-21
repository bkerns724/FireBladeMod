package FireBlade.relics;

import FireBlade.powers.VitalityPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DuelistLocket extends CustomRelic {
    public static final String ID = "FireBladeMod:DuelistLocket";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/DuelistLocket.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Locket_outline.png";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int VV_AMOUNT = 1;

    public DuelistLocket() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, VV_AMOUNT), VV_AMOUNT));
        addToBot(new ApplyPowerAction(p, p, new VitalityPower(p, VV_AMOUNT), VV_AMOUNT));
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + VV_AMOUNT + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new DuelistLocket(); }
}
