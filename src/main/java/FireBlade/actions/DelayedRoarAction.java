package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class DelayedRoarAction extends AbstractGameAction {

    public DelayedRoarAction() {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.TEXT;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY,"RRRAAAAWWWWRRR!!", true));
        addToTop(new SFXAction("FireBladeMod:CounterRawr"));
        this.isDone = true;
    }
}