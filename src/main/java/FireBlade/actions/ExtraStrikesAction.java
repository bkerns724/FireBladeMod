package FireBlade.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Random;


public class ExtraStrikesAction extends AbstractGameAction {
    private AbstractCreature owner;
    private AbstractCard card;

    public ExtraStrikesAction(AbstractCreature owner, AbstractCard card) {
        actionType = ActionType.DAMAGE;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        this.owner = owner;
        this.card = card;
    }

    public void update() {
        ArrayList<AbstractMonster> potentialMonsters = new ArrayList<AbstractMonster>();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && !m.halfDead)
                potentialMonsters.add(m);
        }
        if (potentialMonsters.size() > 0) {
            Random rand = new Random();
            int i = rand.nextInt(potentialMonsters.size());
            AbstractMonster mo = potentialMonsters.get(i);
            card.calculateCardDamage(mo);
            addToTop(new DamageAction(mo, new DamageInfo(owner, card.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

        isDone = true;
    }
}
