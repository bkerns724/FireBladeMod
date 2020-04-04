package FireBlade.actions;

import FireBlade.powers.FireShieldPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;

public class FireShieldAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int baseFireShield = 0;

    public FireShieldAction(AbstractPlayer p, int baseFireShield) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.baseFireShield = baseFireShield;
    }

    public void update() {
        int fireAmount = GetEstimate(this.p, baseFireShield);

        if (fireAmount > 0) {
            addToTop(new ApplyPowerAction(this.p, this.p, new FireShieldPower(this.p, fireAmount), fireAmount));
            CardCrawlGame.sound.play("ATTACK_FIRE", 0.1F);
        }

        this.isDone = true;
    }

    public static int GetEstimate(AbstractPlayer p, int realMagicNumber) {
        int fireAmount = realMagicNumber;

        if (p.hasPower("FireBladeMod:PyroPower"))
            fireAmount += p.getPower("FireBladeMod:PyroPower").amount;

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }
}