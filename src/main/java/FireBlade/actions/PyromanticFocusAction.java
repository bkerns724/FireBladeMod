package FireBlade.actions;

import FireBlade.powers.FervorPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class PyromanticFocusAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse;
    private boolean freeToPlayOnce;

    public PyromanticFocusAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse) {
        this.freeToPlayOnce = false;
        this.energyOnUse = -1;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.DAMAGE;
        this.energyOnUse = energyOnUse;
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
            addToTop(new ApplyPowerAction(p, p, new FervorPower(p, effect), effect));

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
                }
            }
        this.isDone = true;
    }
}