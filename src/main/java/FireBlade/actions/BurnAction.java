package FireBlade.actions;

import FireBlade.powers.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BurnAction extends AbstractGameAction {
    private AbstractCreature source;
    private AbstractCreature target;
    private int baseBurn;
    private int hellfire;
    private boolean ignoreFervor;
    private boolean ignorePyroForm;

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn, int hellfire, boolean ignoreFervor, boolean ignorePyroForm) {
        this.source = source;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DEBUFF;
        this.target = target;
        this.baseBurn = baseBurn;
        this.hellfire = hellfire;
        this.ignoreFervor = ignoreFervor;
        this.ignorePyroForm = ignorePyroForm;
    }

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn, int hellfire) {
        this(source, target, baseBurn, hellfire, false, false);
    }

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn) {
        this(source, target, baseBurn, 1, false, false);
    }

    public void update() {
        if (target == null || target.isDeadOrEscaped()) {
            this.isDone = true;
            return;
        }

        int burnAmount = GetEstimate(source, target, baseBurn, hellfire, ignoreFervor, ignorePyroForm);

        if (burnAmount > 0) {
            addToTop(new ApplyPowerAction(target, source, new BurningPower(target, source, burnAmount), burnAmount));
            CardCrawlGame.sound.play("ATTACK_FIRE", 0.1F);
        }

        this.isDone = true;
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber, int hell, boolean ignoreFervor, boolean ignorePyroForm) {
        int fireAmount = realMagicNumber;

        if (source.hasPower(FervorPower.POWER_ID) && !ignoreFervor)
            fireAmount += hell*(source.getPower(FervorPower.POWER_ID).amount);

        if (!ignoreFervor && source.hasPower(BattleMagePower.POWER_ID) && source.hasPower(StrengthPower.POWER_ID))
            fireAmount += hell*(source.getPower(StrengthPower.POWER_ID).amount);

        if (source.hasPower(PyromancerFormPower.POWER_ID) && !ignorePyroForm)
            fireAmount *= (1 + source.getPower(PyromancerFormPower.POWER_ID).amount/100F);

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber, int hell, boolean ignoreFervor, boolean ignorePyroForm) {
        if (target == null)
            return GetEstimate(source, realMagicNumber, hell, ignoreFervor, ignorePyroForm);

        float fireAmount = realMagicNumber;

        if (source.hasPower(FervorPower.POWER_ID) && !ignoreFervor)
            fireAmount += hell*(source.getPower(FervorPower.POWER_ID).amount);

        if (!ignoreFervor && source.hasPower(BattleMagePower.POWER_ID) && source.hasPower(StrengthPower.POWER_ID))
            fireAmount += hell*(source.getPower(StrengthPower.POWER_ID).amount);

        if (source.hasPower(PyromancerFormPower.POWER_ID) && !ignorePyroForm)
            fireAmount *= (1 + source.getPower(PyromancerFormPower.POWER_ID).amount/100F);

        if (target.hasPower(SpiritRendPower.POWER_ID))
            fireAmount *= SpiritRendPower.BURN_MULT;

        int returnAmount = (int)Math.floor(fireAmount);

        if (returnAmount < 0)
            returnAmount = 0;

        return returnAmount;
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber) {
        return GetEstimate(source, realMagicNumber, 1, false, false);
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber, int hellfire) {
        return GetEstimate(source, realMagicNumber, hellfire, false, false);
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber) {
        return GetEstimate(source, target, realMagicNumber, 1, false, false);
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber, int hellfire) {
        return GetEstimate(source, target, realMagicNumber, hellfire, false, false);
    }
}