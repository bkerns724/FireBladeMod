package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class FireAndFuryAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse;
    private boolean freeToPlayOnce;
    private DamageInfo damageInfo;
    private int burnPerEnergy;

    public FireAndFuryAction(AbstractPlayer p, AbstractMonster m, DamageInfo damageInfo, int burnPerEnergy, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.m = m;
        this.freeToPlayOnce = freeToPlayOnce;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        this.energyOnUse = energyOnUse;
        this.damageInfo = damageInfo;
        this.burnPerEnergy = burnPerEnergy;
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
            for (int i = 0; i < effect; i++) {
                addToBot(new DamageAction(m, damageInfo, AttackEffect.SLASH_DIAGONAL));
            }
            addToBot(new BurnAction(p, m, burnPerEnergy*effect));

            if (!freeToPlayOnce)
                p.energy.use(EnergyPanel.totalCount);
        }
        isDone = true;
    }
}