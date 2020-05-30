package FireBlade.relics;

import FireBlade.powers.FervorPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Matches extends CustomRelic {
    public static final String ID = "FireBladeMod:Matches";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/Matches.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Matches_outline.png";
    private static final RelicTier TIER = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int fervorAmount = 1;

    public Matches() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void atBattleStart() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new FervorPower(p, fervorAmount), fervorAmount));
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + fervorAmount + this.DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new Matches(); }
}
