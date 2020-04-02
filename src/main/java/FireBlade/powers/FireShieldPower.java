package FireBlade.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FireShieldPower extends AbstractPower {
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:FireShieldPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String ClingPowerID = "FireBladeMod:ClingingFlamesPower";

    public FireShieldPower(AbstractCreature owner, int thornsDamage) {
        this.name = NAME;
        this.ID = "FireBladeMod:FireShieldPower";

        this.owner = owner;
        this.amount = thornsDamage;

        this.updateDescription();
        this.loadRegion("flameBarrier");
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageType.THORNS && info.type != DamageType.HP_LOSS && info.owner != this.owner) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.hasPower(ClingPowerID)) {
                int burnAmount = p.getPower(ClingPowerID).amount;
                p.getPower(ClingPowerID).flash();
                this.addToTop(new ApplyPowerAction(info.owner, p, new BurningPower(info.owner, p, burnAmount), burnAmount));
            }

            this.flash();
            this.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageType.THORNS), AttackEffect.FIRE));
        }

        return damageAmount;
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}