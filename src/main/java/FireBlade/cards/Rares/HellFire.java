package FireBlade.cards.Rares;

import FireBlade.actions.BurnAction;
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

public class HellFire extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:HellFire";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/HellFire.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    public HellFire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = this.baseMagicNumber = 3;
        magicNumberTwo = baseMagicNumberTwo = 2;
        this.tags.add(TheFireBladeCardTags.FLAME);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurnAction(p, m, baseMagicNumber, baseMagicNumberTwo));
        FireBladeCardHelper.checkForBurnerTip();
    }

    public void applyPowers() {
        magicNumber = FireBlade.actions.BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber, baseMagicNumberTwo);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.applyPowers();
    }

    public void onMoveToDiscard() {
        this.magicNumber = this.baseMagicNumber;
        isMagicNumberModified = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = FireBlade.actions.BurnAction.GetEstimate(AbstractDungeon.player, mo, baseMagicNumber, baseMagicNumberTwo);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.calculateCardDamage(mo);
    }

    public AbstractCard makeCopy() { return new HellFire(); }

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