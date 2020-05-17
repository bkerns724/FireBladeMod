package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class OverChargeAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int energyOnUse;
    private boolean freeToPlayOnce;
    private int vigorPerEnergy;

    public OverChargeAction(AbstractPlayer p, int vigorPerEnergy, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        this.energyOnUse = energyOnUse;
        this.vigorPerEnergy = vigorPerEnergy;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            int vigorAmount = effect*vigorPerEnergy;

            addToBot(new ApplyPowerAction(p, p, new VigorPower(p, vigorAmount), vigorAmount));

            if (!freeToPlayOnce)
                p.energy.use(EnergyPanel.totalCount);
        }
        isDone = true;
    }
}