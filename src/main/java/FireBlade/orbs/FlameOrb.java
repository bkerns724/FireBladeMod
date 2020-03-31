package FireBlade.orbs;

import FireBlade.actions.FlameOrbAction;
import FireBlade.effects.FlameEvokeEffect;
import FireBlade.effects.FlameFlareEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.FtueTip;

public class FlameOrb extends AbstractOrb {
    public static final String ORB_ID = "FireBladeMod:Flame";
    private static boolean sfxToggle = true;
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    public FlameOrb() {
        this.ID = ORB_ID;
        this.img = new Texture("theFireBladeResources/images/orbs/Flame.png");
        this.name = orbString.NAME;

        this.evokeAmount = this.baseEvokeAmount = 5;
        this.passiveAmount = this.basePassiveAmount = 2;
        updateDescription();

        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
    }

    public void updateDescription() {
        applyFocus();
        this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
    }

    public void onEvoke() {
        AbstractDungeon.actionManager.addToTop(new FlameOrbAction(evokeAmount));
    }

    public void onEndOfTurn() {
        float speedTime = 0.6F / (float)AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlameFlareEffect(this), speedTime));
        AbstractDungeon.actionManager.addToBottom(new FlameOrbAction(passiveAmount));
    }

    public void triggerEvokeAnimation() {
        AbstractDungeon.effectsQueue.add(new FlameEvokeEffect(this.cX, this.cY));
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 120.0F;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        this.c.a /= 3.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale * 1.2F, this.angle / 1.2F, 0, 0, 96, 96, false, false);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.5F, this.scale * 1.5F, this.angle / 1.4F, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX() {
        if (sfxToggle) {
            CardCrawlGame.sound.play("GHOST_ORB_IGNITE_1", 0.1F);
            sfxToggle = false;
        }
        else {
            CardCrawlGame.sound.play("GHOST_ORB_IGNITE_2", 0.1F);
            sfxToggle = true;
        }
    }

    public AbstractOrb makeCopy() { return new FlameOrb(); }
}