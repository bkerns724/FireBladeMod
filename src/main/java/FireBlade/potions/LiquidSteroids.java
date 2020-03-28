package FireBlade.potions;

import FireBlade.powers.BurningPower;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;

public class LiquidSteroids extends CustomPotion {

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FireBladeMod:LiquidSteroids");
    public static final String POTION_ID = "FireBladeMod:LiquidSteroids";
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.BOLT;
    public static final PotionColor COLOR = PotionColor.FEAR;

    public LiquidSteroids() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0];
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.labOutlineColor = CardHelper.getColor(246.0F, 154.0F, 45.0F);
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new DrawCardAction(p, this.getPotency()));
        addToBot(new GainEnergyAction(this.getPotency()));
    }

    public CustomPotion makeCopy() { return new LiquidSteroids();}

    public int getPotency(int ascensionLevel) { return 2; }
}
