package FireBlade.cards.Uncommons;

import FireBlade.cards.FireBladeCardTags;
import FireBlade.enums.FireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class AdrenalineBoost extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:AdrenalineBoost";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/AdrenalineBoost.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 0;

    public AdrenalineBoost() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, FireBladeEnum.FIREBLADE_ORANGE, RARITY, TARGET);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        AbstractCard c;

        Iterator var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(FireBladeCardTags.SMASH)) {
                list.add(c);
            }
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(FireBladeCardTags.SMASH)) {
                list.add(c);
            }
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(FireBladeCardTags.SMASH)) {
                list.add(c);
            }
        }

        AbstractCard card = list.get(cardRandomRng.random(list.size() - 1));
        if (upgraded)
            card.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(card, 1, true, false));

        list.clear();

        var2 = srcCommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(FireBladeCardTags.ENDURANCE)) {
                list.add(c);
            }
        }

        var2 = srcUncommonCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(FireBladeCardTags.ENDURANCE)) {
                list.add(c);
            }
        }

        var2 = srcRareCardPool.group.iterator();
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (c.hasTag(FireBladeCardTags.ENDURANCE)) {
                list.add(c);
            }
        }

        card = list.get(cardRandomRng.random(list.size() - 1));
        if (upgraded)
            card.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(card, 1, true, false));
    }

    public AbstractCard makeCopy() { return new AdrenalineBoost(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
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