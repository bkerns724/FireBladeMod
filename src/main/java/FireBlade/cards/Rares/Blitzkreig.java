package FireBlade.cards.Rares;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import FireBlade.powers.BlitzkreigPower;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Blitzkreig extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Blitzkreig";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Blitzkreig.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;

    public Blitzkreig() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 1;
        baseMagicNumberTwo = magicNumberTwo = 0;
        isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(BlitzkreigPower.POWER_ID))
            addToBot(new ApplyPowerAction(p, p, new BlitzkreigPower(p, magicNumber, magicNumberTwo)));
        else {
            TwoAmountPower power = (TwoAmountPower) p.getPower(BlitzkreigPower.POWER_ID);
            power.amount += magicNumber;
            power.amount2 += magicNumberTwo;
        }
    }

    public AbstractCard makeCopy() { return new Blitzkreig(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumberTwo(1);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}