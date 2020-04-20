package FireBlade.powers;

import FireBlade.actions.ExhaustCardInHandAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HealthyPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:HealthyPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HealthyPower(AbstractCreature owner, int amount) {
        ID = "FireBladeMod:HealthyPower";
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Healthy32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Healthy84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        AbstractPlayer p = AbstractDungeon.player;
        if (card.type == AbstractCard.CardType.STATUS && amount > 0) {
            addToBot(new ExhaustCardInHandAction(card));
            addToBot(new DrawCardAction(1));
            amount--;
        }
        if (amount == 0)
            addToBot(new RemoveSpecificPowerAction(p, p, ID));
    }

    public void updateDescription() {
        if (amount == 0)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
