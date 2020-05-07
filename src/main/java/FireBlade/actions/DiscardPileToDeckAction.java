package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.Iterator;

public class DiscardPileToDeckAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;

    public DiscardPileToDeckAction(AbstractCreature source) {
        p = AbstractDungeon.player;
        setValues(null, source, 1);
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            isDone = true;
        } else {
            if (duration == Settings.ACTION_DUR_FASTER) {
                if (p.discardPile.isEmpty()) {
                    isDone = true;
                    return;
                }

                if (p.discardPile.size() == 1) {
                    AbstractCard tmp = p.discardPile.getTopCard();
                    p.discardPile.removeCard(tmp);
                    p.discardPile.moveToDeck(tmp, true);
                }

                if (p.discardPile.group.size() > 1) {
                    AbstractDungeon.gridSelectScreen.open(p.discardPile, 1, TEXT[0], false, false, false, false);
                    tickDuration();
                    return;
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var3.hasNext()) {
                    AbstractCard c = (AbstractCard)var3.next();
                    p.discardPile.removeCard(c);
                    p.hand.moveToDeck(c, true);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToDeckAction");
        TEXT = uiStrings.TEXT;
    }
}