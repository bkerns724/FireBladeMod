package FireBlade.cards.Uncommons;

import FireBlade.enums.FireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import FireBlade.powers.VitalityPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StunningBlow extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:StunningBlow";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/StunningBlow.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 2;

    public StunningBlow() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 13;
        magicNumber = baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(p, p, new VitalityPower(p, magicNumber), magicNumber));
    }

    public AbstractCard makeCopy() { return new StunningBlow(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(2);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}