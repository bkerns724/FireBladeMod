package FireBlade.relics;

import FireBlade.cards.Other.Ember;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class InnerFlame extends CardPreviewRelic {
    public static final String ID = "FireBladeMod:InnerFlame";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/InnerFlame.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/InnerFlame_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int EMBER_COUNT = 5;

    public InnerFlame() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        cardToPreview = new Ember();
    }

    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> cards = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).group;
        for (AbstractCard c : cards) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))
                p.masterDeck.removeCard(c);
        }

        for(int i = 0; i < EMBER_COUNT; ++i) {
            AbstractCard c = new Ember();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + EMBER_COUNT + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new InnerFlame(); }
}
