package FireBlade.potions;

import FireBlade.FireBladeMod;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class VigorPotion extends CustomPotion {

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FireBladeMod:VigorPotion");
    public static final String POTION_ID = "FireBladeMod:VigorPotion";
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final PotionRarity RARITY = PotionRarity.RARE;
    public static final PotionSize SIZE = PotionSize.BOLT;
    public static final PotionColor COLOR = PotionColor.FEAR;

    public VigorPotion() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = false;
        targetRequired = false;
        tips.clear();
        tips.add(new PowerTip(name, description));
        labOutlineColor = FireBladeMod.FIREBLADE_EYE_COLOR;
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, getPotency()), getPotency()));
    }

    public CustomPotion makeCopy() { return new VigorPotion();}

    public int getPotency(int ascensionLevel) { return 10; }
}
