package FireBlade.powers;

import FireBlade.actions.DesperateDefenseEvalAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class DesperateDefensePower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    public static final String POWER_ID = "FireBladeMod:DesperateDefensePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public boolean triggered;

    public DesperateDefensePower(AbstractCreature owner, int amount, boolean firstApply) {
        ID = POWER_ID;
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/DesperateDefense32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/DesperateDefense84.png"), 0, 0, 84, 84);

        this.amount = amount;
        type = POWER_TYPE;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        triggered = false;
        if (owner.isBloodied && firstApply) {
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
            triggered = true;
        }

        updateDescription();
    }

    public void reducePower(int reduceAmount) {
        if (triggered)
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -reduceAmount), -reduceAmount));
        super.reducePower(reduceAmount);
    }

    public void stackPower(int stackAmount) {
        if (triggered)
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, stackAmount), stackAmount));
        super.stackPower(stackAmount);
    }

    public int onHeal(int healAmount) {
        addToBot(new DesperateDefenseEvalAction(owner));
        return healAmount;
    }

    @Override
    public int onLoseHp(int damageAmount) {
        addToBot(new DesperateDefenseEvalAction(owner));
        return damageAmount;
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
