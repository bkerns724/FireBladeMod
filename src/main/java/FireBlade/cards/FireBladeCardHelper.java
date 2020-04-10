package FireBlade.cards;

import FireBlade.other.FireBladeTipTracker;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

public class FireBladeCardHelper {
    private static final TutorialStrings enduranceStrings =
            CardCrawlGame.languagePack.getTutorialString("FireBladeMod:EnduranceTip");

    private static final TutorialStrings smashStrings =
            CardCrawlGame.languagePack.getTutorialString("FireBladeMod:SmashTip");

    private static final TutorialStrings flameStrings =
            CardCrawlGame.languagePack.getTutorialString("FireBladeMod:BurnerTip");

    public static void checkForEnduranceTip() {
        if (FireBladeTipTracker.shouldShow(FireBladeTipTracker.TipKey.EnduranceTip)) {
            AbstractDungeon.ftue = new FtueTip(enduranceStrings.LABEL[0], enduranceStrings.TEXT[0]+enduranceStrings.TEXT[1],
                    Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.NO_FTUE);
            FireBladeTipTracker.neverShowAgain(FireBladeTipTracker.TipKey.EnduranceTip);
        }
    }

    public static void checkForSmashTip() {
        if (FireBladeTipTracker.shouldShow(FireBladeTipTracker.TipKey.SmashTip)) {
            AbstractDungeon.ftue = new FtueTip(smashStrings.LABEL[0], smashStrings.TEXT[0]+smashStrings.TEXT[1],
                    Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.NO_FTUE);
            FireBladeTipTracker.neverShowAgain(FireBladeTipTracker.TipKey.SmashTip);
        }
    }

    public static void checkForBurnerTip() {
        if (FireBladeTipTracker.shouldShow(FireBladeTipTracker.TipKey.BurnerTip)) {
            AbstractDungeon.ftue = new FtueTip(flameStrings.LABEL[0], flameStrings.TEXT[0]+flameStrings.TEXT[1],
                    Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.NO_FTUE);
            FireBladeTipTracker.neverShowAgain(FireBladeTipTracker.TipKey.BurnerTip);
        }
    }
}
