package FireBlade.cards.Commons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.TheFireBladeEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ProbingAttack extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:ProbingAttack";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ProbingAttack.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    private int realBaseDamage = 3;

    public ProbingAttack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 3;
        baseBlock = 6;
        magicNumber = baseMagicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean blockAction = false;
        if (m != null)
            if (m.getIntentBaseDmg() > 0)
                blockAction = true;

        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (blockAction)
            addToBot(new GainBlockAction(p, block));
        else
            addToBot(new DamageAction(m, new DamageInfo(p, magicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void applyPowers() {
        baseDamage = baseMagicNumber;
        super.applyPowers();
        magicNumber = damage;
        if (magicNumber != baseMagicNumber)
            isMagicNumberModified = true;
        baseDamage = realBaseDamage;
        super.applyPowers();
    }

    public void calculateCardDamage(AbstractMonster m) {
        baseDamage = baseMagicNumber;
        super.calculateCardDamage(m);
        magicNumber = damage;
        if (magicNumber != baseMagicNumber)
            isMagicNumberModified = true;
        baseDamage = realBaseDamage;
        super.calculateCardDamage(m);
    }

    public void onMoveToDiscard() {
        isMagicNumberModified = false;
        magicNumber = baseMagicNumber;
    }

    public AbstractCard makeCopy() { return new ProbingAttack(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
            upgradeBlock(3);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}