package FireBlade.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import FireBlade.cards.CustomFireBladeCard;

public class MagicNumberTwo extends DynamicVariable {
    public String key() { return "FireBladeMod:M2"; }
    
    public boolean isModified(AbstractCard card) { return ((CustomFireBladeCard)card).isMagicNumberTwoModified; }

    public int value(AbstractCard card) { return ((CustomFireBladeCard)card).magicNumberTwo; }

    public int baseValue(AbstractCard card) { return ((CustomFireBladeCard)card).baseMagicNumberTwo; }

    public boolean upgraded(AbstractCard card) { return ((CustomFireBladeCard)card).upgradedMagicNumberTwo; }
}