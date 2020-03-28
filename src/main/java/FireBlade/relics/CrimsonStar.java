package FireBlade.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class CrimsonStar extends CustomRelic {
    public static final String ID = "FireBladeMod:CrimsonStar";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/CrimsonStar.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/CrimsonStar_outline.png";
    private static final AbstractRelic.RelicTier TIER = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;

    public CrimsonStar() {
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
            AbstractDungeon.player.increaseMaxHp(7, true);
        }
    }

    public void obtain() {
        // Below statement should always be true if this relic is being obtained.
        if (AbstractDungeon.player.hasRelic("FireBladeMod:RedStar")) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if (((AbstractRelic)AbstractDungeon.player.relics.get(i)).relicId.equals("FireBladeMod:RedStar")) {
                    instantObtain(AbstractDungeon.player, i,true);
                    break;
                }
            }
        }
        else {
                super.obtain();
        }
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }

    public AbstractRelic makeCopy() { return new CrimsonStar(); }

    public boolean canSpawn() { return AbstractDungeon.player.hasRelic("FireBladeMod:RedStar"); }
}
