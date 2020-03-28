package FireBlade.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class IterationPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:IterationPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IterationPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:IterationPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Iteration32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Iteration84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.canUpgrade()) {
            card.upgrade();
            card.applyPowers();
        }
    }

    public void atEndOfRound() {
        flash();
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "FireBladeMod:IterationPower"));
            } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, "FireBladeMod:IterationPower", 1));
        }
    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
}
