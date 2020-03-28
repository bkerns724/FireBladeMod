package FireBlade.cards.Uncommons;

import FireBlade.actions.FlailingAction;
import FireBlade.enums.TheFireBladeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IntenseFlailing extends CustomCard {

    public static final String ID = "FireBladeMod:IntenseFlailing";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/IntenseFlailing.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = -1; // What you do for x type cards

    public IntenseFlailing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.baseDamage = 4;
        this.baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FlailingAction(p, m, this.damage, this.block, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy() { return new IntenseFlailing(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage (2);
            upgradeBlock( 2);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}