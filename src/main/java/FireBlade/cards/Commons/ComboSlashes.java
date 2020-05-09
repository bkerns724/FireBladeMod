package FireBlade.cards.Commons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComboSlashes extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:ComboSlashes";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ComboSlashes.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    public int realBaseDamage = 2;

    public ComboSlashes() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = realBaseDamage;
        magicNumberTwo = baseMagicNumberTwo = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, magicNumberTwo, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (upgraded)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, magicNumberTwo, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void applyPowers() {
        baseDamage = baseMagicNumberTwo;
        super.applyPowers();
        magicNumberTwo = damage;
        isMagicNumberTwoModified = magicNumberTwo != baseMagicNumberTwo;
        baseDamage = realBaseDamage;
        super.applyPowers();
    }

    public void calculateCardDamage(AbstractMonster m) {
        baseDamage = baseMagicNumberTwo;
        super.calculateCardDamage(m);
        magicNumberTwo = damage;
        isMagicNumberTwoModified = magicNumberTwo != baseMagicNumberTwo;
        baseDamage = realBaseDamage;
        super.calculateCardDamage(m);
    }

    public void onMoveToDiscard() {
        isMagicNumberTwoModified = false;
        magicNumberTwo = baseMagicNumberTwo;
    }

    public AbstractCard makeCopy() { return new ComboSlashes(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}