package FireBlade.cards.Rares;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.TheFireBladeEnum;
import FireBlade.powers.BattleMageFormPower;
import FireBlade.powers.FervorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BattleMageForm extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:BattleMageForm";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/BattleMageForm.png";
    private static final CardStrings cardStrings;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final int COST = 3;

    public BattleMageForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(BattleMageFormPower.POWER_ID))
            addToBot(new ApplyPowerAction(p, p, new BattleMageFormPower(p), magicNumber));
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
            addToBot(new ApplyPowerAction(p, p, new FervorPower(p, magicNumber), magicNumber));
        }
    }

    public AbstractCard makeCopy() { return new BattleMageForm(); }

    public void upgrade() {
        if (!upgraded) {
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