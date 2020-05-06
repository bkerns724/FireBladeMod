package FireBlade.cards.Uncommons;

import FireBlade.cards.FireBladeCardHelper;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import FireBlade.cards.FireBladeCardTags;
import FireBlade.enums.FireBladeEnum;

public class ArmSmash extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:ArmSmash";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ArmSmash.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 2;

    public ArmSmash() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 17;
        magicNumber = baseMagicNumber = 3;
        tags.add(FireBladeCardTags.SMASH);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
        FireBladeCardHelper.checkForSmashTip();
    }

    public AbstractCard makeCopy() { return new ArmSmash(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(6);
            upgradeMagicNumber(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}