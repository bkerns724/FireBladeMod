package FireBlade.cards.Commons;

import FireBlade.actions.BurnAction;
import FireBlade.cards.TheFireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShootFlames extends CustomCard {

    public static final String ID = "FireBladeMod:ShootFlames";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ShootFlames.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    public ShootFlames() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
        this.tags.add(TheFireBladeCardTags.BURNER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurnAction(p, m, baseMagicNumber));
    }

    public void applyPowers() {
        magicNumber = FireBlade.actions.BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.applyPowers();
    }

    public void onMoveToDiscard() {
        this.magicNumber = this.baseMagicNumber;
        isMagicNumberModified = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = FireBlade.actions.BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.calculateCardDamage(mo);
    }

    public AbstractCard makeCopy() { return new ShootFlames(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}