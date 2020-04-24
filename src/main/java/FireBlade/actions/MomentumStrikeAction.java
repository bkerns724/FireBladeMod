package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class MomentumStrikeAction extends AbstractGameAction {

    private DamageInfo info;
    private AbstractCreature target;
    private static int cardAmount = 1;
    private static int energyAmount =1;

    public MomentumStrikeAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.target = target;
        actionType = ActionType.DAMAGE;
        duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_MED && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SLASH_DIAGONAL));
            target.damage(info);
            if ( (target.isDying || target.currentHealth <= 0) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                addToBot(new GainEnergyAction(energyAmount));
                addToBot(new DrawCardAction(cardAmount));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            isDone = true;
        }

        tickDuration();
    }
}