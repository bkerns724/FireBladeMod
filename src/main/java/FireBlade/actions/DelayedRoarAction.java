package FireBlade.actions;

import FireBlade.FireBladeMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

public class DelayedRoarAction extends AbstractGameAction {
    private static final String message = "RRRAAAAWWWWRRR!!";

    public DelayedRoarAction() {
        duration = Settings.ACTION_DUR_MED;
        actionType = ActionType.TEXT;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY,message, true));
        addToTop(new SFXAction(FireBladeMod.ROAR_KEY));
        isDone = true;
    }
}