package FireBlade.powers;

import FireBlade.FireBladeMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class LearnTellsPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final String POWER_NAME = "LearnTells";
    public static final String POWER_ID = FireBladeMod.getModID() + ":" + POWER_NAME + "Power";

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LearnTellsPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/LearnTells32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/LearnTells84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        name = NAME;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount), amount));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
