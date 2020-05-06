package FireBlade.powers;

import FireBlade.FireBladeMod;
import FireBlade.actions.DelayedVigorAction;
import FireBlade.cards.FireBladeCardTags;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EnergizingFlamePower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final String POWER_NAME = "EnergizingFlame";
    public static final String POWER_ID = FireBladeMod.getModID() + ":" + POWER_NAME + "Power";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    
    public EnergizingFlamePower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/EnergizingFlame32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/EnergizingFlame84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        name = NAME;

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(FireBladeCardTags.FLAME))
            addToBot(new DelayedVigorAction(amount));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
