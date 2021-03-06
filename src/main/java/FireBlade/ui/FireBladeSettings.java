package FireBlade.ui;

import FireBlade.enums.FireBladeEnum;
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
    private static final String UI_ID = "FireBladeMod:ModSettingsPanel";

    public static ModPanel createSettingsPanel() {
        String[] ModSettingsText = (CardCrawlGame.languagePack.getUIString(UI_ID).TEXT);

        ModPanel settingsPanel = new ModPanel();

        ModLabel messageLabel = new ModLabel("", 400.0F, 220.0F, Color.GREEN, FontHelper.tipHeaderFont, settingsPanel, label -> { });
        settingsPanel.addUIElement(messageLabel);

        ModButton resetTipsButton = new ModButton(370.0F, 610.0F, settingsPanel, b -> {
            FireBladeTipTracker.reset();
            messageLabel.text = ModSettingsText[3];
        });
        settingsPanel.addUIElement(resetTipsButton);

        ModLabel resetTipsLabel = new ModLabel(ModSettingsText[2], 500.0F, 665.0F, Color.WHITE, FontHelper.tipHeaderFont, settingsPanel, label -> { });
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