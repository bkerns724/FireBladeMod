package FireBlade.ui;

import FireBlade.FireBladeMod;
import FireBlade.enums.FireBladeEnum;
import FireBlade.relics.CrimsonStar;
import FireBlade.relics.GoldenStar;
import basemod.*;
import basemod.helpers.RelicType;
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
    private static final String CrimsonStarString = "CrimsonStarShared";
    private static final String MOD_SETTINGS_FILE = "FireBlade_config";
    private static SpireConfig config;

    static  {
        DEFAULT_SETTINGS.setProperty(GoldenStarString, "false");
        DEFAULT_SETTINGS.setProperty(CrimsonStarString, "false");
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
    public static boolean isCrimsonStarGlobal() { return config.getBool(CrimsonStarString); }

    public static void onGoldenStarToggle(ModToggleButton toggle) {
        try {
            config.setBool(GoldenStarString, toggle.enabled);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onCrimsonStarToggle(ModToggleButton toggle) {
        try {
            config.setBool(CrimsonStarString, toggle.enabled);
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

        ModButton resetTipsButton = new ModButton(370.0F, 610.0F, settingsPanel, b -> {
            FireBladeTipTracker.reset();
            messageLabel.text = ModSettingsText[3];
        });
        settingsPanel.addUIElement(resetTipsButton);

        ModLabel resetTipsLabel = new ModLabel(ModSettingsText[2], 500.0F, 635.0F, Color.WHITE, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(resetTipsLabel);

        ModButton unlockA20Button = new ModButton(370.0F, 470.0F, settingsPanel, b -> {
            unlockA20(FireBladeEnum.THE_FIREBLADE);
            messageLabel.text = ModSettingsText[5];
        });
        settingsPanel.addUIElement(unlockA20Button);

        ModLabel unlockA20Label = new ModLabel(ModSettingsText[4], 500.0F, 525.0F, Color.WHITE, FontHelper.tipHeaderFont, settingsPanel, label -> { });
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