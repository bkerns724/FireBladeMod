package FireBlade.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RollWithTheBlowPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:RollWithTheBlowPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean triggeredThisTurn = false;

    public RollWithTheBlowPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:RollWithTheBlowPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/RollWithTheBlow32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/RollWithTheBlow84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        triggeredThisTurn = false;
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        super.wasHPLost(info, damageAmount);
        if (!triggeredThisTurn) {
            flash();
            triggeredThisTurn = true;
            addToTop(new DrawCardAction(amount));
        }
    }

    public void updateDescription() {
        if (amount == 1)
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
    }
}
