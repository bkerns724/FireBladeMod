package FireBlade.cards.Rares;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.enums.FireBladeEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LightningCombo extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:LightningCombo";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/LightningCombo.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    public LightningCombo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 3;
        magicNumber = baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractGameAction.AttackEffect atkEffect;
            int randomInt = (int)(3 * Math.random());
            if (i == magicNumber - 1)
                atkEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
            else if (randomInt == 0)
                atkEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
            else if (randomInt == 1)
                atkEffect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
            else
                atkEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), atkEffect));
        }
    }


    public AbstractCard makeCopy() { return new LightningCombo(); }

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