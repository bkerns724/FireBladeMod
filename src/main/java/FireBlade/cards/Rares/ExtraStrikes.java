package FireBlade.cards.Rares;

import FireBlade.enums.TheFireBladeEnum;
import FireBlade.powers.ExtraStrikesPlusPower;
import FireBlade.powers.ExtraStrikesPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExtraStrikes extends CustomCard {

    public static final String ID = "FireBladeMod:ExtraStrikes";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ExtraStrikes.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    public ExtraStrikes() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.baseDamage = 2;
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded)
            addToBot(new ApplyPowerAction(p, p, new ExtraStrikesPower(p, this.magicNumber), this.magicNumber));
        else
            addToBot(new ApplyPowerAction(p, p, new ExtraStrikesPlusPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() { return new ExtraStrikes(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
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