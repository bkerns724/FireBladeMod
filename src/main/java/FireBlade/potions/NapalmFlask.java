package FireBlade.potions;

import FireBlade.powers.BurningPower;
import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class NapalmFlask extends CustomPotion {

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FireBladeMod:NapalmFlask");
    public static final String POTION_ID = "FireBladeMod:NapalmFlask";
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final AbstractPotion.PotionRarity RARITY = PotionRarity.COMMON;
    public static final AbstractPotion.PotionSize SIZE = PotionSize.BOTTLE;
    public static final AbstractPotion.PotionColor COLOR = PotionColor.FIRE;

    public NapalmFlask() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(BaseMod.getKeywordTitle("fireblademod:Burning"), BaseMod.getKeywordDescription("fireblademod:Burning")));
        this.labOutlineColor = CardHelper.getColor(246.0F, 154.0F, 45.0F);
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(target, p, new BurningPower(target, p, this.getPotency()), this.getPotency()));
    }

    public CustomPotion makeCopy() { return new NapalmFlask();}

    public int getPotency(int ascensionLevel) { return 5; }
}
