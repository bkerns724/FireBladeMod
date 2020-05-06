package FireBlade.cards.Commons;

import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import FireBlade.enums.FireBladeEnum;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class ComboCleave extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:ComboCleave";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ComboCleave.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = 1;
    private int realBaseDamage = 3;
    public int[] multiDamage2;

    public ComboCleave() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = realBaseDamage;
        magicNumberTwo = baseMagicNumberTwo = 4;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new WhirlwindEffect(), 0.1F));

        addToBot(new SFXAction("ATTACK_IRON_1"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        addToBot(new SFXAction("ATTACK_IRON_2"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage2, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
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
        multiDamage2 = multiDamage;
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

    public AbstractCard makeCopy() { return new ComboCleave(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            realBaseDamage += 1;
            upgradeMagicNumberTwo(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}