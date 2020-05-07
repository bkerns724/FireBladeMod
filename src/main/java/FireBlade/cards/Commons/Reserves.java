package FireBlade.cards.Commons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.cards.Other.MiracleFireBlade;
import FireBlade.enums.FireBladeEnum;
import FireBlade.powers.ReservesPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reserves extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Reserves";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Reserves.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    public Reserves() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseBlock = 5;
        magicNumber = baseMagicNumber = 1;
        cardsToPreview = new MiracleFireBlade();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new ReservesPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() { return new Reserves(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}