package FireBlade.powers;

import FireBlade.FireBladeMod;
import FireBlade.actions.BurnAction;
import FireBlade.cards.FireBladeCardHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlingFirePower extends TwoAmountPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final String POWER_NAME = "FlingFire";
    public static final String POWER_ID = FireBladeMod.getModID() + ":" + POWER_NAME + "Power";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int ATTACKS_TO_TRIGGER = 5;
    
    public FlingFirePower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/FlingFire32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/FlingFire84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        amount2 = ATTACKS_TO_TRIGGER;
        name = NAME;

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        --amount2;
        if (amount2 == 0) {
            flash();
            amount2 = ATTACKS_TO_TRIGGER;
            FireBladeCardHelper.checkForBurnerTip();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                addToBot(new BurnAction(owner, m, amount, 1, false, true));
        }

        this.updateDescription();
    }

    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + BurnAction.GetEstimate(owner, amount) + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + BurnAction.GetEstimate(owner, amount) + DESCRIPTIONS[3];
    }
}
