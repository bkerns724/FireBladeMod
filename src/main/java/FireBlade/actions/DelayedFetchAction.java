package FireBlade.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;

import java.util.function.Predicate;

public class DelayedFetchAction extends AbstractGameAction {
    private int number;
    private CardGroup source;
    private Predicate<AbstractCard> predicate;

    public DelayedFetchAction(CardGroup source, Predicate<AbstractCard> predicate, int number) {
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.CARD_MANIPULATION;
        this.number = number;
        this.predicate = predicate;
        this.source = source;
    }

    public void update() {
        addToBot(new FetchAction(source, predicate, number));
        isDone = true;
    }
}