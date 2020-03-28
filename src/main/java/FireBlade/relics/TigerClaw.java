package FireBlade.relics;

import FireBlade.powers.JabsPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TigerClaw extends CustomRelic {
    public static final String ID = "FireBladeMod:TigerClaw";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/TigerClaw.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/TigerClaw_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int jabsAmount = 3;

    public TigerClaw() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new JabsPower(p, jabsAmount), jabsAmount));
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }

    public AbstractRelic makeCopy() { return new TigerClaw(); }
}
