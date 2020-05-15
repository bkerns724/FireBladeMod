package FireBlade.cards.Other;

import FireBlade.cards.CustomFireBladeCard;
import FireBlade.powers.BlitzkreigSmashPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChooseSmash extends CustomFireBladeCard {
    public static final String ID = "FireBladeMod:ChooseSmash";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int POWER_AMOUNT = 1;
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "theFireBladeResources/images/cardImages/BasicSmash.png";
    private static final int COST = -2;

    public ChooseSmash() {
        super(ID, NAME, IMG_PATH, COST, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player; 
        this.addToBot(new ApplyPowerAction(p, p, new BlitzkreigSmashPower(p, POWER_AMOUNT), POWER_AMOUNT));
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new ChooseSmash();
    }
}