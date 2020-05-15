package FireBlade.powers;

import FireBlade.FireBladeMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class SoulSwordPower extends TwoAmountPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final String POWER_NAME = "SoulSword";
    public static final String POWER_ID = FireBladeMod.getModID() + ":" + POWER_NAME + "Power";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int ATTACKS_TO_TRIGGER = 4;
    
    public SoulSwordPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/SoulSword32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/SoulSword84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        amount2 = ATTACKS_TO_TRIGGER;
        name = NAME;

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == owner && info.type == DamageInfo.DamageType.NORMAL) {
            --amount2;
            if (amount2 == 0) {
                flash();
                amount2 = ATTACKS_TO_TRIGGER;
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new ApplyPowerAction(target, p, new SpiritRendPower(target, amount), amount));
            }
        }

        this.updateDescription();
    }

    public void updateDescription() {
        if (amount2 == 1)
            description = DESCRIPTIONS[0] + ATTACKS_TO_TRIGGER + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + amount2 + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + ATTACKS_TO_TRIGGER + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + amount2 + DESCRIPTIONS[4];
    }
}
