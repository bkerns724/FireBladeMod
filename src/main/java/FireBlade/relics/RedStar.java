package FireBlade.relics;

import FireBlade.actions.DelayedRoarAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RedStar extends CustomRelic {
    public static final String ID = "FireBladeMod:RedStar";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/RedStar.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Star_outline.png";
    private static final AbstractRelic.RelicTier TIER = AbstractRelic.RelicTier.STARTER;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.SOLID;
    private static final int hpGain = 3;
    private int turnCounter;

    public RedStar() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) {
            this.pulse = true;
            beginPulse();
        }
        else {
            this.pulse = false;
        }
    }

    public void onVictory() {
        if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.pulse = false;
            AbstractDungeon.player.increaseMaxHp(hpGain, true);
        }
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + hpGain + this.DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new RedStar(); }
}
