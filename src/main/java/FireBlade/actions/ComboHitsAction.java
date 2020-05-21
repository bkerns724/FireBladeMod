package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.StrikeDummy;

import java.util.ArrayList;

public class ComboHitsAction extends AbstractGameAction {
    private AbstractCreature owner;
    private AbstractCard card;
    private boolean strike;

    public ComboHitsAction(AbstractCreature owner, AbstractCard card, boolean strike) {
        actionType = ActionType.DAMAGE;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.owner = owner;
        this.card = card;
        this.strike = strike;
    }

    public void update() {
        if (strike && AbstractDungeon.player.hasRelic(StrikeDummy.ID))
            card.baseDamage += 3;

        ArrayList<AbstractMonster> potentialMonsters = new ArrayList<AbstractMonster>();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && !m.halfDead) {
                card.calculateCardDamage(m);
                addToTop(new DamageAction(m, new DamageInfo(owner, card.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }

        if (strike && AbstractDungeon.player.hasRelic(StrikeDummy.ID))
            card.baseDamage -= 3;

        isDone = true;
    }
}
