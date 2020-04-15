package FireBlade.cards.Uncommons;

import FireBlade.actions.BurnAction;
import FireBlade.cards.FireBladeCardHelper;
import FireBlade.cards.TheFireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fireball extends CustomCard {

    public static final String ID = "FireBladeMod:Fireball";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Fireball.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = 2;

    public Fireball() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = this.baseMagicNumber = 7;
        this.tags.add(TheFireBladeCardTags.FLAME);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new BurnAction(p, mo, baseMagicNumber));
        }
        FireBladeCardHelper.checkForBurnerTip();
    }

    public void applyPowers() {
        magicNumber = BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.applyPowers();
    }

    public void onMoveToDiscard() {
        this.magicNumber = this.baseMagicNumber;
        isMagicNumberModified = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = BurnAction.GetEstimate(AbstractDungeon.player, mo, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.calculateCardDamage(mo);
    }

    public AbstractCard makeCopy() { return new Fireball(); }

    public void upgrade() {
        if (!this.upgraded) {
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