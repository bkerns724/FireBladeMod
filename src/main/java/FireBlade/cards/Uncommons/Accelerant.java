package FireBlade.cards.Uncommons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.TheFireBladeEnum;
import FireBlade.powers.AccelerantPower;
import FireBlade.powers.FervorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Accelerant extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Accelerant";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Accelerant.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    public Accelerant() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 1;
        magicNumberTwo = baseMagicNumberTwo = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new AccelerantPower(m, p, magicNumber), magicNumber));
        if (upgraded)
            addToBot(new ApplyPowerAction(p, p, new FervorPower(p, magicNumberTwo), magicNumberTwo));
    }

    public AbstractCard makeCopy() { return new Accelerant(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
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