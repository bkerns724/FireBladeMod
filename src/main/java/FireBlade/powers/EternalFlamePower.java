package FireBlade.powers;

import FireBlade.cards.Basics.Ember;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EternalFlamePower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:EternalFlamePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EternalFlamePower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:EternalFlamePower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/EternalFlame32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/EternalFlame84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    public void updateDescription() {
        if (amount == 1)
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }

    public void atStartOfTurn() {
        addToBot(new MakeTempCardInHandAction(new Ember(), amount));
    }
}
