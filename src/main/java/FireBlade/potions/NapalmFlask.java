package FireBlade.potions;

import FireBlade.FireBladeMod;
import FireBlade.powers.BurningPower;
import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class NapalmFlask extends CustomPotion {

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FireBladeMod:NapalmFlask");
    public static final String POTION_ID = "FireBladeMod:NapalmFlask";
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final AbstractPotion.PotionRarity RARITY = PotionRarity.COMMON;
    public static final AbstractPotion.PotionSize SIZE = PotionSize.M;
    public static final AbstractPotion.PotionColor COLOR = PotionColor.FIRE;
    private static final String KEYWORD_FOR_TIP = "fireblademod:Burning";

    public NapalmFlask() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = true;
        targetRequired = true;
        tips.clear();
        tips.add(new PowerTip(name, description));
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_FOR_TIP), BaseMod.getKeywordDescription(KEYWORD_FOR_TIP)));
        labOutlineColor = FireBladeMod.FIREBLADE_EYE_COLOR;
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(target, p, new BurningPower(target, p, getPotency()), getPotency()));
    }

    public CustomPotion makeCopy() { return new NapalmFlask();}

    public int getPotency(int ascensionLevel) { return 4; }
}
