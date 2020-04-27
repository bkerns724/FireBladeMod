package FireBlade.cards.Uncommons;

import FireBlade.cards.FireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FinishingBarrage extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:FinishingBarrage";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/FinishingBarrage.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    public FinishingBarrage() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        baseDamage = 7;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int smashCount = 0;
        for (AbstractCard c : p.exhaustPile.group) {
            if (c.hasTag(FireBladeCardTags.SMASH))
                smashCount++;
        }

        if (smashCount == 0)
            return;

        for (int i = 1; i < smashCount; i++)
            addToBot(new PummelDamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_HEAVY));
    }

    public AbstractCard makeCopy() { return new FinishingBarrage(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}