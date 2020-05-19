package FireBlade.powers;

import FireBlade.FireBladeMod;
import FireBlade.cards.FireBladeCardTags;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BlitzkreigPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final String POWER_NAME = "Blitzkreig";
    public static final String POWER_ID = FireBladeMod.getModID() + ":" + POWER_NAME + "Power";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    boolean triggeredThisTurn = false;

    public BlitzkreigPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Blitzkreig32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Blitzkreig84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        name = NAME;

        updateDescription();
    }

    public void onExhaust(AbstractCard card) {
        if(card.hasTag(FireBladeCardTags.SMASH) && !triggeredThisTurn) {
            this.flash();
            addToTop(new GainEnergyAction(amount));
            addToTop(new DrawCardAction(amount));
            triggeredThisTurn = true;
        }
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        triggeredThisTurn = false;
    }

    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
    }
}
