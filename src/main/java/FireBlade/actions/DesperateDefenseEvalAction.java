package FireBlade.actions;

import FireBlade.powers.DesperateDefensePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.DexterityPower;


public class DesperateDefenseEvalAction extends AbstractGameAction {
    private AbstractCreature owner;

    public DesperateDefenseEvalAction(AbstractCreature owner) {
        actionType = ActionType.SPECIAL;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.owner = owner;
    }

    public void update() {
        if (owner.hasPower(DesperateDefensePower.POWER_ID)) { // This should always be true
            DesperateDefensePower ddPower = (DesperateDefensePower) owner.getPower(DesperateDefensePower.POWER_ID);
            if (ddPower.triggered && !owner.isBloodied) {
                addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -ddPower.amount), -ddPower.amount));
                ddPower.triggered = false;
            }
            else if (!ddPower.triggered && owner.isBloodied) {
                addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, ddPower.amount), ddPower.amount));
                ddPower.triggered = true;
            }
        }

        isDone = true;
    }
}
