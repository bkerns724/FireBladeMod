package FireBlade.relics;

import FireBlade.enums.FireBladeEnum;
import FireBlade.ui.FireBladeSettings;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

public class GoldenStar extends CustomRelic {
    public static final String ID = "FireBladeMod:GoldenStar";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/GoldenStar.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Star_outline.png";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;
    private static final int goldAmount = 75;

    public GoldenStar() {
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
            AbstractDungeon.player.gainGold(goldAmount);
            AbstractDungeon.effectList.add(new RainingGoldEffect(goldAmount));
        }
    }

    public boolean canSpawn() {
        return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + goldAmount + this.DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new GoldenStar(); }
}
