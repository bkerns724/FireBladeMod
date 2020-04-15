package FireBlade.relics;

import FireBlade.FireBladeMod;
import FireBlade.cards.Other.Swipe;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TigerClaw extends CustomRelic {
    public static final String ID = "FireBladeMod:TigerClaw";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/TigerClaw.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/TigerClaw_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int swipeCount = 5;

    private static final Logger logger = LogManager.getLogger(FireBladeMod.class.getName());

    public AbstractCard cardToPreview;

    public TigerClaw() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        cardToPreview = new Swipe();
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0]; }

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

    @Override
    public void renderTip(SpriteBatch sb)
    {
        super.renderTip(sb);
        renderCardPreview(sb);
    }

    public void renderCardPreview(SpriteBatch sb)
    {
        if (cardToPreview != null)
        {
            if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.RELIC_VIEW)
            {
                cardToPreview.current_x = Settings.WIDTH - 380 * Settings.scale;
                cardToPreview.current_y = Settings.HEIGHT * 0.65F - cardToPreview.hb.width/2.0F;
            }
            else
            {
                if (InputHelper.mX >= 1400.0F * Settings.scale)
                {
                    cardToPreview.current_x = InputHelper.mX - 420 * Settings.scale - cardToPreview.hb.width/2.0F;
                    cardToPreview.current_y = InputHelper.mY - 80*Settings.scale - cardToPreview.hb.height/2.0F;
                }
                else if (InputHelper.mX < 1100.0F * Settings.scale)
                {
                    cardToPreview.current_x = InputHelper.mX + 450 * Settings.scale + cardToPreview.hb.width/2.0F;
                    cardToPreview.current_y = InputHelper.mY - 60*Settings.scale - cardToPreview.hb.height/2.0F;
                }
                else
                {
                    cardToPreview.current_x = InputHelper.mX - 50 * Settings.scale - cardToPreview.hb.width/2.0F;
                    cardToPreview.current_y = InputHelper.mY - 60*Settings.scale - cardToPreview.hb.height/2.0F;
                }
            }
            cardToPreview.drawScale = 1;
            cardToPreview.render(sb);
        }
    }

    @Override
    public void renderBossTip(SpriteBatch sb)
    {
        super.renderBossTip(sb);
        renderCardPreview(sb);
    }

    public AbstractRelic makeCopy() { return new TigerClaw(); }
}
