package FireBlade.cards.Uncommons;

import FireBlade.enums.TheFireBladeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;

public class Flexibility extends CustomCard {

    public static final String ID = "FireBladeMod:Flexibility";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Flexibility.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    public Flexibility() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DrawPower(p, 1), 1));
    }

    public AbstractCard makeCopy() { return new Flexibility(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}