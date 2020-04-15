package FireBlade.actions;

import FireBlade.cards.Uncommons.WildSlashes;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class WildSlashesAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse;
    private boolean freeToPlayOnce;
    public DamageInfo damageInfo;

    public WildSlashesAction(AbstractPlayer p, DamageInfo damageInfo, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        this.energyOnUse = energyOnUse;
        this.damageInfo = damageInfo;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                if (WildSlashes.getMonsterCount() < 3)
                    addToTop(new DamageRandomEnemyAction(damageInfo, AttackEffect.SLASH_DIAGONAL));
                else
                    addToTop(new DamageRandomEnemyAction(damageInfo, AttackEffect.SLASH_HEAVY));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
                }
            }
        this.isDone = true;
    }
}