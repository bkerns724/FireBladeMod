package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustCardInHandAction extends AbstractGameAction {
    public AbstractCard card;

    public ExhaustCardInHandAction(AbstractCard card) {
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
    }

    public void update() {
        AbstractDungeon.player.hand.moveToExhaustPile(card);
        isDone = true;
    }
}