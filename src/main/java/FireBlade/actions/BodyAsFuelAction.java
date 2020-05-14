package FireBlade.actions;

import FireBlade.cards.FireBladeCardTags;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

public class BodyAsFuelAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;

    private int hpCost;

    public BodyAsFuelAction(int hpCost) {
        actionType = ActionType.CARD_MANIPULATION;
        this.hpCost = hpCost;
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
                    if (c.costForTurn != -2)
                        tmp.addToRandomSpot(c);
                }
            }

            if (tmp.size() == 0)
                isDone = true;
            else if (tmp.size() == 1) {
                playAndPay(tmp.getBottomCard());
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
                playAndPay(AbstractDungeon.gridSelectScreen.selectedCards.get(0));

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();

                isDone = true;
            }
            else
                tickDuration();
        }
    }

    void playAndPay(AbstractCard card) {
        AbstractDungeon.player.drawPile.group.remove(card);
        AbstractDungeon.getCurrRoom().souls.remove(card);
        AbstractDungeon.player.limbo.group.add(card);
        card.current_y = -200.0F * Settings.scale;
        card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.scale;
        card.target_y = (float)Settings.HEIGHT / 2.0F;
        card.targetAngle = 0.0F;
        card.lighten(false);
        card.drawScale = 0.12F;
        card.targetDrawScale = 0.75F;
        card.applyPowers();
        if (card.cost == -1)        // X Costs
            addToTop(new LoseHPAction(player, player, EnergyPanel.totalCount*hpCost));
        else if (card.cost*hpCost > 0)
            addToTop(new LoseHPAction(player, player, card.cost*hpCost));
        this.addToTop(new NewQueueCardAction(card, true, false, true));
        this.addToTop(new UnlimboAction(card));
        if (!Settings.FAST_MODE) {
            this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        } else {
            this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("AdrenalineBoostAction").TEXT;
    }
}
