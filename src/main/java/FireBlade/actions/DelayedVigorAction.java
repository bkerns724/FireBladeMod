package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class DelayedVigorAction extends AbstractGameAction {
    private int amount;

    public DelayedVigorAction(int amount) {
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, amount), amount));
        isDone = true;
    }
}