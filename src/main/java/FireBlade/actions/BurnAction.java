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

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn, int hellfire) {
        this.source = source;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DEBUFF;
        this.target = target;
        this.baseBurn = baseBurn;
        this.hellfire = hellfire;
    }

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn) {
        this(source, target, baseBurn, 1);
    }

    public void update() {
        if (target == null || target.isDeadOrEscaped()) {
            this.isDone = true;
            return;
        }

        int burnAmount = GetEstimate(source, target, baseBurn, hellfire);

        if (burnAmount > 0) {
            addToTop(new ApplyPowerAction(target, source, new BurningPower(target, source, burnAmount), burnAmount));
            CardCrawlGame.sound.play("ATTACK_FIRE", 0.1F);
        }

        if (source.hasPower(PyromancerFormPower.POWER_ID)) {
            int pyroAmount = source.getPower(PyromancerFormPower.POWER_ID).amount;
            addToTop(new ApplyPowerAction(target, source, new SpiritRendPower(target, pyroAmount), pyroAmount));
        }

        this.isDone = true;
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber, int hell) {
        int fireAmount = realMagicNumber;

        if (source.hasPower(FervorPower.POWER_ID))
            fireAmount += hell*(source.getPower(FervorPower.POWER_ID).amount);

        if (source.hasPower(BattleMagePower.POWER_ID) && source.hasPower(StrengthPower.POWER_ID))
            fireAmount += hell*(source.getPower(StrengthPower.POWER_ID).amount);

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber, int hell) {
        if (target == null)
            return GetEstimate(source, realMagicNumber, hell);

        float fireAmount = realMagicNumber;

        if (source.hasPower(FervorPower.POWER_ID))
            fireAmount += hell*(source.getPower(FervorPower.POWER_ID).amount);

        if (source.hasPower(BattleMagePower.POWER_ID) && source.hasPower(StrengthPower.POWER_ID))
            fireAmount += hell*(source.getPower(StrengthPower.POWER_ID).amount);

        int returnAmount = (int)Math.floor(fireAmount);

        if (returnAmount < 0)
            returnAmount = 0;

        return returnAmount;
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber) {
        return GetEstimate(source, realMagicNumber, 1);
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber) {
        return GetEstimate(source, target, realMagicNumber, 1);
    }
}