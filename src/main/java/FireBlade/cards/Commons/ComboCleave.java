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
import FireBlade.enums.TheFireBladeEnum;
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

    public ComboCleave() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.damage = this.baseDamage = realBaseDamage;
        this.magicNumberTwo = this.baseMagicNumberTwo = 4;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new WhirlwindEffect(), 0.1F));

        this.addToBot(new SFXAction("ATTACK_IRON_1"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        this.addToBot(new SFXAction("ATTACK_IRON_2"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.magicNumberTwo, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
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

    public void onMoveToDiscard() {
        isMagicNumberTwoModified = false;
        magicNumberTwo = baseMagicNumberTwo;
    }

    public AbstractCard makeCopy() { return new ComboCleave(); }

    public void upgrade() {
        if (!this.upgraded) {
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