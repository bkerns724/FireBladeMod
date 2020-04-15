package FireBlade.cards.Uncommons;

import FireBlade.actions.WildSlashesAction;
import FireBlade.enums.TheFireBladeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WildSlashes extends CustomCard {

    public static final String ID = "FireBladeMod:WildSlashes";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/WildSlashes.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = -1; // What you do for x type cards
    public int realBaseDamage = 4;

    public WildSlashes() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 2;
        baseDamage = realBaseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WildSlashesAction(p, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), freeToPlayOnce, energyOnUse));
    }

    public void applyPowers() {
        int monsterCount = getMonsterCount();
        baseDamage = realBaseDamage + magicNumber*monsterCount;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster m) {
        int monsterCount = getMonsterCount();
        baseDamage = realBaseDamage + magicNumber*monsterCount;
        super.calculateCardDamage(m);
        baseDamage = realBaseDamage;
        isDamageModified = (damage != baseDamage);
        initializeDescription();
    }

    public static int getMonsterCount() {
        int monsterCount = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                monsterCount++;
            }
        }
        return monsterCount;
    }

    public AbstractCard makeCopy() { return new WildSlashes(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage (1);
            realBaseDamage += 1;
            upgradeMagicNumber( 1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}