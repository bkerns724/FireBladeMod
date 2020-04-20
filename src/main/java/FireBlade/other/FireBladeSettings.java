package FireBlade.other;

import FireBlade.enums.TheFireBladeEnum;
import basemod.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import java.util.Properties;

public class FireBladeSettings
{
    private static Properties DEFAULT_SETTINGS = new Properties();
    private static final String GoldenStarString = "GoldenStarShared";
    private static final String InnerFlameString = "InnerFlameShared";
    private static final String PlannerString = "PlannerShared";
    private static final String MOD_SETTINGS_FILE = "FireBlade_config";
    private static SpireConfig config;

    static  {
        DEFAULT_SETTINGS.setProperty(GoldenStarString, "false");
        DEFAULT_SETTINGS.setProperty(InnerFlameString, "false");
        DEFAULT_SETTINGS.setProperty(PlannerString, "false");
    }


    public static void initialize() {
        try {
            config = new SpireConfig("The FireBlade", MOD_SETTINGS_FILE, DEFAULT_SETTINGS);
            config.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isGoldenStarGlobal() { return config.getBool(GoldenStarString); }
    public static boolean isInnerFlameGlobal() { return config.getBool(InnerFlameString); }
    public static boolean isPlannerGlobal() { return config.getBool(PlannerString); }

    public static void onGoldenStarToggle(ModToggleButton toggle) {
        try {
            config.setBool(GoldenStarString, toggle.enabled);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onInnerFlameToggle(ModToggleButton toggle) {
        try {
            config.setBool(InnerFlameString, toggle.enabled);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onPlannerToggle(ModToggleButton toggle) {
        try {
            config.setBool(PlannerString, toggle.enabled);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ModPanel createSettingsPanel() {
        String[] ModSettingsText = (CardCrawlGame.languagePack.getUIString("FireBladeMod:ModSettingsPanel").TEXT);

        ModPanel settingsPanel = new ModPanel();

        ModLabel messageLabel = new ModLabel("", 400.0F, 220.0F, Color.GREEN, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(messageLabel);

        ModLabeledToggleButton goldenStarToggle = new ModLabeledToggleButton(ModSettingsText[0], 400.0F, 700.0F, Color.WHITE,
                FontHelper.tipHeaderFont, isGoldenStarGlobal(), settingsPanel, label -> {  }, FireBladeSettings::onGoldenStarToggle);
        settingsPanel.addUIElement(goldenStarToggle);

        ModLabeledToggleButton innerFlameToggle = new ModLabeledToggleButton(ModSettingsText[1], 400.0F, 620.0F, Color.WHITE,
                FontHelper.tipHeaderFont, isInnerFlameGlobal(), settingsPanel, label -> {  }, FireBladeSettings::onInnerFlameToggle);
        settingsPanel.addUIElement(innerFlameToggle);

        ModLabeledToggleButton plannerToggle = new ModLabeledToggleButton(ModSettingsText[2], 400.0F, 540.0F, Color.WHITE,
                FontHelper.tipHeaderFont, isPlannerGlobal(), settingsPanel, label -> {  }, FireBladeSettings::onPlannerToggle);
        settingsPanel.addUIElement(plannerToggle);

        ModButton resetTipsButton = new ModButton(370.0F, 370.0F, settingsPanel, b -> {
            FireBladeTipTracker.reset();
            messageLabel.text = ModSettingsText[4];
        });
        settingsPanel.addUIElement(resetTipsButton);

        ModLabel resetTipsLabel = new ModLabel(ModSettingsText[3], 500.0F, 425.0F, Color.WHITE, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(resetTipsLabel);

        ModButton unlockA20Button = new ModButton(370.0F, 260.0F, settingsPanel, b -> {
            unlockA20(TheFireBladeEnum.THE_FIREBLADE);
            messageLabel.text = ModSettingsText[6];
        });
        settingsPanel.addUIElement(unlockA20Button);

        ModLabel unlockA20Label = new ModLabel(ModSettingsText[5], 500.0F, 315.0F, Color.WHITE, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(unlockA20Label);

        return settingsPanel;
    }

    private static void unlockA20(AbstractPlayer.PlayerClass clz) {
        Prefs prefs = CardCrawlGame.characterManager.getCharacter(clz).getPrefs();
        if (prefs.getInteger("WIN_COUNT", 0) == 0) {
            prefs.putInteger("WIN_COUNT", 1);
        }
        prefs.putInteger("LAST_ASCENSION_LEVEL", 20);
        prefs.putInteger("ASCENSION_LEVEL", 20);
        prefs.flush();
    }
}