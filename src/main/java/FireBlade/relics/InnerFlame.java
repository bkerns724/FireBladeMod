package FireBlade.relics;

import FireBlade.cards.Other.Ember;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class InnerFlame extends CustomRelic {
    public static final String ID = "FireBladeMod:InnerFlame";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/InnerFlame.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/InnerFlame_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int emberCount = 5;

    public AbstractCard cardToPreview;

    public InnerFlame() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        cardToPreview = new Ember();
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

    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> cards = CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).group;
        for (AbstractCard c : cards) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))
                p.masterDeck.removeCard(c);
        }

        for(int i = 0; i < emberCount; ++i) {
            AbstractCard c = new Ember();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + emberCount + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new InnerFlame(); }
}
