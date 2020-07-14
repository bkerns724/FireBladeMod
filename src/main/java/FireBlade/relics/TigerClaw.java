package FireBlade.relics;

import FireBlade.cards.Other.Swipe;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class TigerClaw extends CardPreviewRelic {
    public static final String ID = "FireBladeMod:TigerClaw";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/TigerClaw.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/TigerClaw_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int SWIPE_COUNT = 5;
    private static final String KEYWORD_FOR_TIP = "soulbound";

    public TigerClaw() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        tips.add(new PowerTip(BaseMod.getKeywordTitle(KEYWORD_FOR_TIP), BaseMod.getKeywordDescription(KEYWORD_FOR_TIP)));
        initializeTips();
        cardToPreview = new Swipe();
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + SWIPE_COUNT + DESCRIPTIONS[1]; }

    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> cards = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).group;
        for (AbstractCard c : cards) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))
                p.masterDeck.removeCard(c);
        }

        for(int i = 0; i < SWIPE_COUNT; ++i) {
            AbstractCard c = new Swipe();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }

    public AbstractRelic makeCopy() { return new TigerClaw(); }
}
