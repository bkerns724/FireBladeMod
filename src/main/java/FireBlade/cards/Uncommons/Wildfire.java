package FireBlade.cards.Uncommons;

import FireBlade.actions.BurnAction;
import FireBlade.cards.CustomFireBladeCard;
import FireBlade.cards.FireBladeCardHelper;
import FireBlade.cards.FireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;

public class Wildfire extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Wildfire";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Wildfire.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 2;
    private static int burnAmount = 1;

    public Wildfire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 10;
        tags.add(FireBladeCardTags.FLAME);
        cardsToPreview = new Burn();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int effectSize = BurnAction.GetEstimate(p, m, baseMagicNumber);
            addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, effectSize)));
        addToBot(new BurnAction(p, m, baseMagicNumber));
        addToBot(new MakeTempCardInDiscardAction(new Burn(), burnAmount));
        FireBladeCardHelper.checkForBurnerTip();
    }

    public void applyPowers() {
        magicNumber = BurnAction.GetEstimate(AbstractDungeon.player, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.applyPowers();
    }

    public void onMoveToDiscard() {
        magicNumber = baseMagicNumber;
        isMagicNumberModified = false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = BurnAction.GetEstimate(AbstractDungeon.player, mo, baseMagicNumber);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.calculateCardDamage(mo);
    }

    public AbstractCard makeCopy() { return new Wildfire(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}