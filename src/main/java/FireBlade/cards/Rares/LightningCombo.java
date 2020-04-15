package FireBlade.cards.Rares;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.TheFireBladeEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LightningCombo extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:LightningCombo";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/LightningCombo.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = 4;
    private int realBaseDamage = 4;

    public LightningCombo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = realBaseDamage;
        magicNumber = baseMagicNumber = 6;
        magicNumberTwo = baseMagicNumberTwo = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractGameAction.AttackEffect atkEffect;
            int randomInt = (int)(3 * Math.random());
            if (randomInt == 0)
                atkEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
            else if (randomInt == 1)
                atkEffect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
            else
                atkEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
            addToBot(new AttackDamageRandomEnemyAction(this, atkEffect));
        }
        addToBot(new DamageAllEnemiesAction(p, magicNumberTwo, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public void applyPowers() {
        baseDamage = baseMagicNumberTwo;
        super.applyPowers();
        magicNumberTwo = damage;
        if (magicNumberTwo != baseMagicNumberTwo)
            isMagicNumberTwoModified = true;
        baseDamage = realBaseDamage;
        super.applyPowers();
    }

    public void calculateCardDamage(AbstractMonster m) {
        baseDamage = baseMagicNumberTwo;
        super.calculateCardDamage(m);
        magicNumberTwo = damage;
        if (magicNumberTwo != baseMagicNumberTwo)
            isMagicNumberTwoModified = true;
        baseDamage = realBaseDamage;
        super.calculateCardDamage(m);
    }

    public void onMoveToDiscard() {
        isMagicNumberTwoModified = false;
        magicNumberTwo = baseMagicNumberTwo;
    }

    public AbstractCard makeCopy() { return new LightningCombo(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            realBaseDamage += 1;
            upgradeMagicNumber(2);
            upgradeMagicNumberTwo(8);
            upgradeBaseCost(5);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}