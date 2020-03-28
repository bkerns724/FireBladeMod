package FireBlade.actions;

import FireBlade.powers.BurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BurnAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int baseBurn = 0;
    private int hellfire = 1;

    public BurnAction(AbstractPlayer p, AbstractMonster m, int baseBurn, int hellfire) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.m = m;
        this.baseBurn = baseBurn;
        this.hellfire = hellfire;
    }

    public BurnAction(AbstractPlayer p, AbstractMonster m, int baseBurn) {
        this(p, m, baseBurn, 1);
    }

    public void update() {
        int burnAmount = GetEstimate(this.p, baseBurn, hellfire);

        if (burnAmount > 0) {
            addToBot(new ApplyPowerAction(this.m, this.p, new BurningPower(this.m, this.p, burnAmount), burnAmount, AttackEffect.FIRE));
        }

        this.isDone = true;
    }

    public static int GetEstimate(AbstractPlayer p, int realMagicNumber, int hell) {
        int fireAmount = realMagicNumber;

        if (p.hasPower("FireBladeMod:PyroPower"))
            fireAmount += hell*(p.getPower("FireBladeMod:PyroPower").amount);

        if (p.hasPower("FireBladeMod:GasolinePower"))
            fireAmount *= 2;

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractPlayer p, int realMagicNumber) {
        return GetEstimate(p, realMagicNumber, 1);
    }
}