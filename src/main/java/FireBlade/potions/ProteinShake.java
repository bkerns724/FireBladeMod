package FireBlade.potions;

import FireBlade.powers.PyroPower;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ProteinShake extends CustomPotion {

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("FireBladeMod:ProteinShake");
    public static final String POTION_ID = "FireBladeMod:ProteinShake";
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final PotionRarity RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize SIZE = PotionSize.HEART;
    public static final PotionColor COLOR = PotionColor.GREEN;

    public ProteinShake() {
        super(NAME, POTION_ID, RARITY, SIZE, COLOR);
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(BaseMod.getKeywordTitle("fireblademod:Fervor"), BaseMod.getKeywordDescription("fireblademod:Fervor")));
        this.labOutlineColor = CardHelper.getColor(246.0F, 154.0F, 45.0F);
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.getPotency()), this.getPotency()));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.getPotency()), this.getPotency()));
        addToBot(new ApplyPowerAction(p, p, new PyroPower(p, this.getPotency()), this.getPotency()));
    }

    public CustomPotion makeCopy() { return new ProteinShake();}

    public int getPotency(int ascensionLevel) { return 1; }
}
