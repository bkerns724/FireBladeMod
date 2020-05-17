package FireBlade.enums;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class FireBladeEnum {
    @SpireEnum
    public static AbstractPlayer.PlayerClass THE_FIREBLADE;
    @SpireEnum(name = "FIREBLADE_ORANGE") // These two HAVE to have the same absolutely identical name.
    public static AbstractCard.CardColor FIREBLADE_ORANGE;
    @SpireEnum(name = "FIREBLADE_ORANGE") @SuppressWarnings("unused")
    public static CardLibrary.LibraryType LIBRARY_COLOR;
}