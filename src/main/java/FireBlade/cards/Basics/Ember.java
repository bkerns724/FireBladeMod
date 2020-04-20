package FireBlade.cards.Basics;

import FireBlade.actions.BurnAction;
import FireBlade.cards.CustomFireBladeCard;
import FireBlade.cards.FireBladeCardHelper;
import FireBlade.cards.TheFireBladeCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import FireBlade.enums.TheFireBladeEnum;

public class Ember extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Ember";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Ember.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 0;

    public Ember() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 2;
        tags.add(TheFireBladeCardTags.FLAME);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurnAction(p, m, baseMagicNumber));
        FireBladeCardHelper.checkForBurnerTip();
    }

    public void applyPowers() {
        magicNumber = BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.applyPowers();
    }

    public void onMoveToDiscard() {
        magicNumber = baseMagicNumber;
        isMagicNumberModified = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        magicNumber = BurnAction.GetEstimate(AbstractDungeon.player, mo, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
    }

    public AbstractCard makeCopy() { return new Ember(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}