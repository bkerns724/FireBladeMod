package FireBlade.powers;

import FireBlade.actions.BurnAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BurningRetortPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:BurningRetortPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BurningRetortPower(AbstractCreature owner, int amount) {
        ID = "FireBladeMod:BurningRetortPower";
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/BurningRetort32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/BurningRetort84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            if (owner.hasPower(ID)) {
                owner.getPower(ID).flash();
                this.addToBot(new BurnAction(owner, info.owner, amount, 1, true));
            }

            this.flash();
            this.addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
        }
        return damageAmount;
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }


    public void updateDescription() { description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }
}
