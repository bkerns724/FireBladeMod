package FireBlade.powers;

import FireBlade.FireBladeMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class VitalityPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final String POWER_NAME = "Vitality";
    public static final String POWER_ID = FireBladeMod.getModID() + ":" + POWER_NAME + "Power";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VitalityPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Vitality32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Vitality84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        name = NAME;

        updateDescription();
    }

    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.baseBlock > 0)
            return blockAmount + amount;
        else
            return blockAmount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.baseBlock > 0) {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
