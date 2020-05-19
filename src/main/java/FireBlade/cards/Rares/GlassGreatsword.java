package FireBlade.cards.Rares;

import FireBlade.enums.FireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GlassGreatsword extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:GlassGreatsword";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/GlassGreatsword.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = 1;
    private static final String redCode = "[#FF0000]";

    public GlassGreatsword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 15;
        magicNumber = baseMagicNumber = 5;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ModifyDamageAction(uuid, -magicNumber));
    }

    public AbstractCard makeCopy() { return new GlassGreatsword(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();

        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + redCode + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}