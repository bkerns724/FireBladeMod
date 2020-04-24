package FireBlade.actions;

import FireBlade.cards.FireBladeCardTags;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class AdrenalineBoostAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;

    public AdrenalineBoostAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        player = AbstractDungeon.player;
    }

    public void update() {

        if (duration == startDuration) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            if (!player.drawPile.isEmpty()) {
                AbstractCard c;
                Iterator iter1 = player.drawPile.group.iterator();
                while (iter1.hasNext()) {
                    c = (AbstractCard) iter1.next();
                    if (c.hasTag(FireBladeCardTags.ENDURANCE) || c.hasTag(FireBladeCardTags.SMASH))
                        tmp.addToRandomSpot(c);
                }
            }

            if (tmp.size() == 0)
                isDone = true;
            else if (tmp.size() == 1) {
                AbstractCard c = tmp.getBottomCard();
                if (player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                    player.drawPile.moveToDiscardPile(c);
                    player.createHandIsFullDialog();
                } else {
                    player.drawPile.moveToHand(c, player.drawPile);
                }
                isDone = true;
            }
            else {
                tmp.sortAlphabetically(true);
                tmp.sortByRarityPlusStatusCardType(false);
                AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[0], false);

                tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                if (player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                    player.drawPile.moveToDiscardPile(c);
                    player.createHandIsFullDialog();
                } else {
                    player.drawPile.moveToHand(c, player.drawPile);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();

                isDone = true;
            }
            else
                tickDuration();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("AdrenalineBoostAction").TEXT;
    }
}
