package FireBlade.cards.Commons;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WildSlashes extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:WildSlashes";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/WildSlashes.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = 1;
    private static final int DAMAGE_PER_ENEMY = 2;

    public WildSlashes() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 2;
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public AbstractCard makeCopy() { return new WildSlashes(); }

    public void calculateCardDamage(AbstractMonster mo) {
        int monsterCount = 0;
        for (AbstractMonster m2:AbstractDungeon.getMonsters().monsters) {
            if (!m2.isDeadOrEscaped() && !m2.halfDead)
                monsterCount++;
        }
        baseDamage = DAMAGE_PER_ENEMY*monsterCount;
        super.calculateCardDamage(mo);
        baseDamage = DAMAGE_PER_ENEMY;
    }

    public void applyPowers() {
        int monsterCount = 0;
        for (AbstractMonster m2:AbstractDungeon.getMonsters().monsters) {
            if (!m2.isDeadOrEscaped() && !m2.halfDead)
                monsterCount++;
        }
        baseDamage = DAMAGE_PER_ENEMY*monsterCount;
        super.applyPowers();
    }

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