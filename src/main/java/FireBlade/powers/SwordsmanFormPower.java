package FireBlade.powers;

import FireBlade.cards.Rares.SwordsmanForm;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SwordsmanFormPower extends TwoAmountPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    public static final String POWER_ID = "FireBladeMod:SwordsmanFormPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SwordsmanFormPower(AbstractCreature owner, int amount) {
        amount2 = SwordsmanForm.attacksToProc;
        ID = POWER_ID;
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/SwordsmanForm32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/SwordsmanForm84.png"), 0, 0, 84, 84);

        this.amount = amount;
        type = POWER_TYPE;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type != DamageInfo.DamageType.NORMAL)
            return;
        amount2--;
        if (amount2 <= 0) { // Should never be < 0
            amount2 = SwordsmanForm.attacksToProc;
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
        }
        updateDescription();
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + SwordsmanForm.attacksToProc + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + amount2 + DESCRIPTIONS[3];
    }
}
