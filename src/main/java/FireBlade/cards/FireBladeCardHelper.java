package FireBlade.cards;

import FireBlade.ui.FireBladeTipTracker;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

public class FireBladeCardHelper {
    private static final TutorialStrings ENDURANCE_STRINGS =
            CardCrawlGame.languagePack.getTutorialString("FireBladeMod:EnduranceTip");

    private static final TutorialStrings SMASH_STRINGS =
            CardCrawlGame.languagePack.getTutorialString("FireBladeMod:SmashTip");

    private static final TutorialStrings FLAME_STRINGS =
            CardCrawlGame.languagePack.getTutorialString("FireBladeMod:BurnerTip");

    public static void checkForEnduranceTip() {
        if (FireBladeTipTracker.shouldShow(FireBladeTipTracker.TipKey.EnduranceTip)) {
            AbstractDungeon.ftue = new FtueTip(ENDURANCE_STRINGS.LABEL[0], ENDURANCE_STRINGS.TEXT[0]+ ENDURANCE_STRINGS.TEXT[1],
                    Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.NO_FTUE);
            FireBladeTipTracker.neverShowAgain(FireBladeTipTracker.TipKey.EnduranceTip);
        }
    }

    public static void checkForSmashTip() {
        if (FireBladeTipTracker.shouldShow(FireBladeTipTracker.TipKey.SmashTip)) {
            AbstractDungeon.ftue = new FtueTip(SMASH_STRINGS.LABEL[0], SMASH_STRINGS.TEXT[0]+ SMASH_STRINGS.TEXT[1],
                    Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.NO_FTUE);
            FireBladeTipTracker.neverShowAgain(FireBladeTipTracker.TipKey.SmashTip);
        }
    }

    public static void checkForBurnerTip() {
        if (FireBladeTipTracker.shouldShow(FireBladeTipTracker.TipKey.BurnerTip)) {
            AbstractDungeon.ftue = new FtueTip(FLAME_STRINGS.LABEL[0], FLAME_STRINGS.TEXT[0]+ FLAME_STRINGS.TEXT[1],
                    Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.NO_FTUE);
            FireBladeTipTracker.neverShowAgain(FireBladeTipTracker.TipKey.BurnerTip);
        }
    }
}
