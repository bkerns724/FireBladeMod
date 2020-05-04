package FireBlade.relics;

import FireBlade.powers.BurningPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class InnerFlame extends CustomRelic {
    public static final String ID = "FireBladeMod:InnerFlame";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/InnerFlame.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/InnerFlame_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int burnAmount = 1;
    private static final int startTurn = 4;

    public InnerFlame() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onEquip() { AbstractDungeon.player.energy.energyMaster++; }

    public void onUnequip() { AbstractDungeon.player.energy.energyMaster--; }

    public void atBattleStart() { this.counter = 0; }

    public void atTurnStart() {
        this.counter++;
        if (this.counter == startTurn) {
            beginLongPulse();
        }
    }

    public void onPlayerEndTurn() {
        if (this.counter >= startTurn) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new BurningPower(p, p, burnAmount), burnAmount));
        }
    }

    public void onVictory()
    {
        stopPulse();
        this.counter = -1;
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + startTurn + this.DESCRIPTIONS[1] + burnAmount + this.DESCRIPTIONS[2]; }

    public AbstractRelic makeCopy() { return new InnerFlame(); }
}
