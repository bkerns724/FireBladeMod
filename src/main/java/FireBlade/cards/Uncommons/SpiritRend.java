package FireBlade.cards.Uncommons;

import FireBlade.enums.TheFireBladeEnum;
import FireBlade.powers.SpiritRendPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpiritRend extends CustomCard {

    public static final String ID = "FireBladeMod:SpiritRend";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/SpiritRend.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 2;

    public SpiritRend() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(m, p, new SpiritRendPower(m, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() { return new SpiritRend(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}