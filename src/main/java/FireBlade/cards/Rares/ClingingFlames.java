package FireBlade.cards.Rares;

import FireBlade.actions.FireShieldAction;
import FireBlade.cards.CustomFireBladeCard;
import FireBlade.cards.TheFireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import FireBlade.powers.ClingingFlamesPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClingingFlames extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:ClingingFlames";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ClingingFlames.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;

    public ClingingFlames() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.magicNumberTwo = this.baseMagicNumberTwo = 2;
        this.tags.add(TheFireBladeCardTags.FIRESHIELD);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FireShieldAction(p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ClingingFlamesPower(p, this.magicNumberTwo), this.magicNumberTwo));
    }

    public AbstractCard makeCopy() { return new ClingingFlames(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeMagicNumberTwo(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}