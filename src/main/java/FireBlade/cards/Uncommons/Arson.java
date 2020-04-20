package FireBlade.cards.Uncommons;

import FireBlade.cards.TheFireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class Arson extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:Arson";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/Arson.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;
    private static final int costLimit = 3;

    public Arson() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        magicNumber = baseMagicNumber = costLimit;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        AbstractCard c;

        Iterator var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(TheFireBladeCardTags.FLAME) && c.cost <= costLimit)
                list.add(c);
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(TheFireBladeCardTags.FLAME) && c.cost <= costLimit)
                list.add(c);
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(TheFireBladeCardTags.FLAME) && c.cost <= costLimit)
                list.add(c);
        }

        AbstractCard card = list.get(cardRandomRng.random(list.size() - 1));
        card.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(card, true));
    }

    public AbstractCard makeCopy() { return new Arson(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}