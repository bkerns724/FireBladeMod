package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class TickleAction extends AbstractGameAction {
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse;
    private boolean freeToPlayOnce;
    private int damage;
    private boolean upgraded;

    public TickleAction(AbstractPlayer p, AbstractMonster m, int damage, boolean upgraded, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
        this.freeToPlayOnce = false;
        this.energyOnUse = -1;
        this.damageType = damageType;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.damage = damage;
        this.upgraded = upgraded;
        this.m = m;
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

        if (upgraded)
            effect += 1;

        if (effect > 0) {
            for (int i = 0; i < 2*effect; i++) {
                addToBot(new DamageAction(this.m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.BLUNT_LIGHT));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
                }
            }
        this.isDone = true;
    }
}