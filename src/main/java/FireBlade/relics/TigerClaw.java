package FireBlade.relics;

import FireBlade.cards.Other.Swipe;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class TigerClaw extends CustomRelic {
    public static final String ID = "FireBladeMod:TigerClaw";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/TigerClaw.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/TigerClaw_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;

    private static final int swipeCount = 5;

    public TigerClaw() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }

    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        for (int i =  p.masterDeck.group.size() - 1; i >= 0; --i) {
            AbstractCard card = p.masterDeck.group.get(i);
            if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE))
                p.masterDeck.removeCard(card);
        }

        for(int i = 0; i < swipeCount; ++i) {
            AbstractCard c = new Swipe();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }

    public AbstractRelic makeCopy() { return new TigerClaw(); }
}
