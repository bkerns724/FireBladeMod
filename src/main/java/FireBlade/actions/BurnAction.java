package FireBlade.actions;

import FireBlade.powers.BurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;

public class BurnAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCreature m;
    private int baseBurn;
    private int hellfire;
    private boolean ignoreFervor;

    public BurnAction(AbstractPlayer p, AbstractCreature m, int baseBurn, int hellfire, boolean ignoreFervor) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.m = m;
        this.baseBurn = baseBurn;
        this.hellfire = hellfire;
        this.ignoreFervor = ignoreFervor;
    }

    public BurnAction(AbstractPlayer p, AbstractCreature m, int baseBurn, int hellfire) {
        this(p, m, baseBurn, hellfire, false);
    }

    public BurnAction(AbstractPlayer p, AbstractCreature m, int baseBurn) {
        this(p, m, baseBurn, 1, false);
    }

    public void update() {
        int burnAmount = GetEstimate(p, m, baseBurn, hellfire, ignoreFervor);

        if (burnAmount > 0) {
            addToTop(new ApplyPowerAction(m, p, new BurningPower(m, p, burnAmount), burnAmount));
            CardCrawlGame.sound.play("ATTACK_FIRE", 0.1F);
        }

        this.isDone = true;
    }

    public static int GetEstimate(AbstractPlayer p, int realMagicNumber, int hell, boolean ignoreFervor) {
        int fireAmount = realMagicNumber;

        if (p.hasPower("FireBladeMod:FervorPower") && !ignoreFervor)
            fireAmount += hell*(p.getPower("FireBladeMod:FervorPower").amount);

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractPlayer p, AbstractCreature m, int realMagicNumber, int hell, boolean ignoreFervor) {
        if (m == null)
            return GetEstimate(p, realMagicNumber, hell, ignoreFervor);

        int fireAmount = realMagicNumber;

        if (p.hasPower("FireBladeMod:FervorPower") && !ignoreFervor)
            fireAmount += hell*(p.getPower("FireBladeMod:FervorPower").amount);

        if (m.hasPower("FireBladeMod:SpiritRendPower")) {
            int rentAmount = m.getPower("FireBladeMod:SpiritRendPower").amount;
            float burnMult = 1F + rentAmount*0.25F;
            fireAmount *= burnMult;
        }

        if (fireAmount < 0)
            fireAmount = 0;

        return fireAmount;
    }

    public static int GetEstimate(AbstractPlayer p, int realMagicNumber) {
        return GetEstimate(p, realMagicNumber, 1, false);
    }

    public static int GetEstimate(AbstractPlayer p, int realMagicNumber, int hellfire) {
        return GetEstimate(p, realMagicNumber, hellfire, false);
    }

    public static int GetEstimate(AbstractPlayer p, AbstractCreature m, int realMagicNumber) {
        return GetEstimate(p, m, realMagicNumber, 1, false);
    }

    public static int GetEstimate(AbstractPlayer p, AbstractCreature m, int realMagicNumber, int hellfire) {
        return GetEstimate(p, m, realMagicNumber, hellfire, false);
    }
}