package FireBlade.cards.Rares;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import FireBlade.powers.LearnTellsPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LearnTells extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:LearnTells";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/LearnTells.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;

    public LearnTells() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LearnTellsPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() { return new LearnTells(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isEthereal = false;
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