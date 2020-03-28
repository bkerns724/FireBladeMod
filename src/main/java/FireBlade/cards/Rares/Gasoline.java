package FireBlade.cards.Rares;

import FireBlade.enums.TheFireBladeEnum;
import FireBlade.powers.GasolinePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Gasoline extends CustomCard {

    public static final String ID = "FireBladeMod:Gasoline";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/ConsumingFlame.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;

    public Gasoline() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean powerExists = false;
        for (AbstractPower pow : p.powers) {
            if (pow.ID.equals("FireBladeMod:GasolinePower")) {
                powerExists = true;
                break;
            }
        }

        if (!powerExists) {
            addToBot(new ApplyPowerAction(p, p, new GasolinePower(p)));
        }
    }

    public AbstractCard makeCopy() { return new Gasoline(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}