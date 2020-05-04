 package FireBlade.patches;
 
 import FireBlade.cards.CustomFireBladeCard;
 import com.evacipated.cardcrawl.modthespire.lib.ByRef;
 import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
 import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import java.util.ArrayList;
 import java.util.Iterator;

 // Code borrowed from Laugic's Vacant

 @SpirePatch(clz = CardGroup.class, method = "initializeDeck")
 public class UnnatePatch
 {
   @SpireInsertPatch(rloc = 6, localvars = {"copy"})
   public static void Insert(@ByRef CardGroup[] copy) {
     ArrayList<AbstractCard> placeOnBot = new ArrayList<AbstractCard>();
     Iterator iter = (copy[0]).group.iterator();

     while (iter.hasNext()) {
       AbstractCard newCard = (AbstractCard)iter.next();
       if (newCard instanceof CustomFireBladeCard &&
               ((CustomFireBladeCard)newCard).isUnnate) {
         placeOnBot.add(newCard);
       }
     }
     if (placeOnBot.size() > 0) {
       for (AbstractCard abstractCard : placeOnBot) {
         copy[0].removeCard(abstractCard);
         copy[0].addToBottom(abstractCard);
       }
     }
   }
 }