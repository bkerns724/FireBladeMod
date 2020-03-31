 package FireBlade.actions;

 import FireBlade.powers.BurningPower;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;

 public class FlameOrbAction extends AbstractGameAction {
     private int burnAmount;
    
     public FlameOrbAction(int burnAmount) {
         this.burnAmount = burnAmount;
     }
    
     public void update() {
         AbstractMonster m = AbstractDungeon.getRandomMonster();
         AbstractPlayer p = AbstractDungeon.player;

         if (m != null) {
             addToTop(new ApplyPowerAction(m, p, new BurningPower(m, p, burnAmount), burnAmount));
             CardCrawlGame.sound.play("ATTACK_FIRE", 0.1F);
             addToTop(new WaitAction(0.1F));
         }

         this.isDone = true;
     }
 }