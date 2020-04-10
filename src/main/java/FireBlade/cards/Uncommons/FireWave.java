package FireBlade.cards.Uncommons;

import FireBlade.actions.BurnAction;
import FireBlade.actions.FireShieldAction;
import FireBlade.cards.CustomFireBladeCard;
import FireBlade.cards.FireBladeCardHelper;
import FireBlade.cards.TheFireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FireBlade.cards.TheFireBladeCardTags.FIRESHIELD;

public class FireWave extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:FireWave";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/FireWave.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    public FireWave() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.magicNumberTwo = this.baseMagicNumberTwo = 2;
        tags.add(TheFireBladeCardTags.FLAME);
        tags.add(FIRESHIELD);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurnAction(p, m, baseMagicNumber));
        addToBot(new FireShieldAction(p, baseMagicNumberTwo));
        FireBladeCardHelper.checkForBurnerTip();
    }

    public void applyPowers() {
        magicNumber = FireBlade.actions.BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        magicNumberTwo = FireShieldAction.GetEstimate(AbstractDungeon.player, baseMagicNumberTwo);
        isMagicNumberTwoModified = magicNumberTwo != baseMagicNumberTwo;

        super.applyPowers();
    }

    public void onMoveToDiscard() {
        this.magicNumber = this.baseMagicNumber;
        this.magicNumberTwo = this.baseMagicNumberTwo;
        isMagicNumberModified = false;
        isMagicNumberTwoModified = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = FireBlade.actions.BurnAction.GetEstimate(AbstractDungeon.player, mo, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.calculateCardDamage(mo);
    }

    public AbstractCard makeCopy() { return new FireWave(); }

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