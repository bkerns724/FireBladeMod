package FireBlade.cards.Uncommons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.TheFireBladeEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class FlashBang extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:FlashBang";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/FlashBang.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 0;

    public FlashBang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 1;
        magicNumberTwo = baseMagicNumberTwo = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumberTwo), -magicNumberTwo));
        if (!m.hasPower("Artifact"))
            addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, magicNumberTwo), magicNumberTwo));
    }

    public AbstractCard makeCopy() { return new FlashBang(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumberTwo(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}