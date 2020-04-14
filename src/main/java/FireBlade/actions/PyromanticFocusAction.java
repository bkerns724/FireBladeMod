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
    private boolean upgraded;

    public PyromanticFocusAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.POWER;
        this.upgraded = upgraded;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }

        if (upgraded)
            effect += 1;

        if (effect > 0) {
            addToTop(new ApplyPowerAction(p, p, new FervorPower(p, effect), effect));

            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
                }
            }
        isDone = true;
    }
}