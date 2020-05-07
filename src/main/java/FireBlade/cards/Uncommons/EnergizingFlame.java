package FireBlade.cards.Uncommons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import FireBlade.powers.EnergizingFlamePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EnergizingFlame extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:EnergizingFlame";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/EnergizingFlame.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    public EnergizingFlame() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EnergizingFlamePower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() { return new EnergizingFlame(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}