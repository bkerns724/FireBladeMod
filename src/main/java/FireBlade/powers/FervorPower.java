package FireBlade.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FervorPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:FervorPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String POWER_ID = "FireBladeMod:FervorPower";
    
    public FervorPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Fervor32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Fervor84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        canGoNegative = true;

        updateDescription();
    }

    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, "FireBladeMod:FervorPower"));
        }
    }

    public void reducePower(int reduceAmount) {
        fontScale = 8.0F;
        amount -= reduceAmount;
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, NAME));
        }

        if (amount >= 999) {
            amount = 999;
        }

        if (amount <= -999) {
            amount = -999;
        }
    }

    public void updateDescription() {
        if (amount > 0) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
            type = AbstractPower.PowerType.BUFF;
        } else {
            description = DESCRIPTIONS[2] + -amount + DESCRIPTIONS[3];
            type = AbstractPower.PowerType.DEBUFF;
        }
    }
}
