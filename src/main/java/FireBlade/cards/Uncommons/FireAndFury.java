package FireBlade.cards.Uncommons;

import FireBlade.actions.FireAndFuryAction;
import FireBlade.cards.FireBladeCardHelper;
import FireBlade.cards.FireBladeCardTags;
import FireBlade.enums.FireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FireAndFury extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:FireAndFury";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/FireAndFury.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = -1;  // X cost

    public FireAndFury() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 3;
        magicNumber = baseMagicNumber = 2;
        tags.add(FireBladeCardTags.FLAME);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FireAndFuryAction(p, m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), magicNumber, freeToPlayOnce, energyOnUse));
        FireBladeCardHelper.checkForBurnerTip();
    }

    public AbstractCard makeCopy() { return new FireAndFury(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage (2);
            upgradeMagicNumber( 1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}