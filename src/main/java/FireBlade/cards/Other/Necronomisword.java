package FireBlade.cards.Other;

import FireBlade.cards.CustomFireBladeCard;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Necronomisword extends CustomFireBladeCard {
    public static final String ID = "FireBladeMod:Necronomisword";
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Necronomisword.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static int COST = 2;

    private Predicate<AbstractCard> necroPredicate = (card) -> card == this;
    private static final String TIP_TITLE = "No Escape";
    private static final String TIP_DESCRIPTION = "There is no escape from this sword.";

    public Necronomisword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
        baseDamage = 15;
        magicNumber = baseMagicNumber = 3;
        selfRetain = true;
        exhaust = true;
        purgeOnUse = true;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        TooltipInfo tip = new TooltipInfo(TIP_TITLE, TIP_DESCRIPTION);
        return new ArrayList<TooltipInfo>() {{ add(tip); }};
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractCard tempCard = new Necronomisword();
        if (upgraded)
            tempCard.upgrade();
        addToBot(new MakeTempCardInHandAction(tempCard));
    }

    @Override
    public boolean hasEnoughEnergy() {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            cantUseMessage = TEXT[9];
            return false;
        } else {
            if (EnergyPanel.totalCount < this.costForTurn && !freeToPlay() && !isInAutoplay) {
                cantUseMessage = TEXT[11];
                return false;
            }
            return true;
        }
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard tempCard = new Necronomisword();
        if (upgraded)
            tempCard.upgrade();
        addToBot(new MakeTempCardInHandAction(tempCard));
    }

    @Override
    public void triggerOnManualDiscard() {
        super.triggerOnManualDiscard();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new FetchAction(p.discardPile, necroPredicate, 1));
    }

    public void upgrade() {
        upgradeName();
        upgradeDamage(5);
    }
}
