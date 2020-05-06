package FireBlade.cards.Uncommons;

import FireBlade.cards.FireBladeCardTags;
import FireBlade.enums.FireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Refreshment extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Refreshment";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Refreshment.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 2;

    public Refreshment() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
        isEthereal = true;
        isUnnate = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int enduranceCount = 0;
        for (AbstractCard c : p.exhaustPile.group)
            if (c.hasTag(FireBladeCardTags.ENDURANCE))
                enduranceCount++;
        for (int i = 0; i < enduranceCount; i++)
            addToBot(new HealAction(p, p, magicNumber));
    }

    public AbstractCard makeCopy() { return new Refreshment(); }

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