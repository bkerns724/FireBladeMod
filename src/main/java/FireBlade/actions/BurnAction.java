package FireBlade.actions;

import FireBlade.powers.BurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BurnAction extends AbstractGameAction {
    private AbstractCreature source;
    private AbstractCreature target;
    private int baseBurn;
    private int hellfire;
    private boolean ignoreFervor;
    private static final String RendID = "FireBladeMod:SpiritRendPower";

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn, int hellfire, boolean ignoreFervor) {
        this.source = source;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.SPECIAL;
        this.target = target;
        this.baseBurn = baseBurn;
        this.hellfire = hellfire;
        this.ignoreFervor = ignoreFervor;
    }

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn, int hellfire) {
        this(source, target, baseBurn, hellfire, false);
    }

    public BurnAction(AbstractCreature source, AbstractCreature target, int baseBurn) {
        this(source, target, baseBurn, 1, false);
    }

    public void update() {
        int burnAmount = GetEstimate(source, target, baseBurn, hellfire, ignoreFervor);

        if (burnAmount > 0) {
            addToTop(new ApplyPowerAction(target, source, new BurningPower(target, source, burnAmount), burnAmount));
            CardCrawlGame.sound.play("ATTACK_FIRE", 0.1F);

            if (target.hasPower(RendID)) {
                AbstractPower rendPower = target.getPower(RendID);
                if (rendPower.amount > 1) {
                    rendPower.reducePower(1);
                    rendPower.updateDescription();
                }
                else
                    addToTop(new RemoveSpecificPowerAction(target, source, RendID));
            }
        }

        this.isDone = true;
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber, int hell, boolean ignoreFervor) {
        int fireAmount = realMagicNumber;

        if (source.hasPower("FireBladeMod:FervorPower") && !ignoreFervor)
            fireAmount += hell*(source.getPower("FireBladeMod:FervorPower").amount);

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber, int hell, boolean ignoreFervor) {
        if (target == null)
            return GetEstimate(source, realMagicNumber, hell, ignoreFervor);

        int fireAmount = realMagicNumber;

        if (source.hasPower("FireBladeMod:FervorPower") && !ignoreFervor)
            fireAmount += hell*(source.getPower("FireBladeMod:FervorPower").amount);

        if (target.hasPower(RendID))
            fireAmount *= 2;

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber) {
        return GetEstimate(source, realMagicNumber, 1, false);
    }

    public static int GetEstimate(AbstractCreature source, int realMagicNumber, int hellfire) {
        return GetEstimate(source, realMagicNumber, hellfire, false);
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber) {
        return GetEstimate(source, target, realMagicNumber, 1, false);
    }

    public static int GetEstimate(AbstractCreature source, AbstractCreature target, int realMagicNumber, int hellfire) {
        return GetEstimate(source, target, realMagicNumber, hellfire, false);
    }
}