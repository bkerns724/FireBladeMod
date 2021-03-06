package FireBlade.cards.Rares;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import FireBlade.powers.IgnitionPower;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ignition extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Ignition";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Ignition.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 0;

    public Ignition() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        exhaust = true;
        magicNumber = baseMagicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new IgnitionPower(m, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() { return new Ignition(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}