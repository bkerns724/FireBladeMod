package FireBlade.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import FireBlade.cards.Basics.SpreadFire;

import FireBlade.enums.*;
import FireBlade.FireBladeMod;

public class TheFireBlade extends CustomPlayer {

    public static final Logger logger = LogManager.getLogger(FireBladeMod.class.getName());

    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("FireBladeMod:FireBladeCharacter");
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;

    private CharStat charStat;

    public static final String[] orbTextures = {
            "theFireBladeResources/images/fireBladeCharacter/orb/layer1.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer2.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer3.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer4.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer5.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer6.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer1d.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer2d.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer3d.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer4d.png",
            "theFireBladeResources/images/fireBladeCharacter/orb/layer5d.png",};

    public TheFireBlade(String playerName) {
        super(playerName, TheFireBladeEnum.THE_FIREBLADE, orbTextures, "theFireBladeResources/images/fireBladeCharacter/orb/vfx.png", (String) null, (String) null);
        logger.info("Start TheFireBlade constructor");
        this.charStat = new CharStat(this);

        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        logger.info("Start TheFireBlade initializeClass");
        initializeClass(null, "theFireBladeResources/images/fireBladeCharacter/shoulder2.png",
                "theFireBladeResources/images/fireBladeCharacter/shoulder.png",
                "theFireBladeResources/images/fireBladeCharacter/corpse.png",
                getLoadout(), -4.0F, -16.0F, 220.0F, 290.0F, new EnergyManager(3));

        logger.info("Start TheFireBlade animation");
        loadAnimation("theFireBladeResources/images/fireBladeCharacter/idle/skeleton.atlas", "theFireBladeResources/images/fireBladeCharacter/idle/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(0.6F);

        logger.info("Start TheFireBlade modHelper");
        if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera") ||
                ModHelper.isModEnabled("Blue Cards"))) {
            this.masterMaxOrbs = 1;
        }

        logger.info("End TheFireBlade constructor");
    }

    public ArrayList<String> getStartingDeck() {
        logger.info("Start TheFireBlade getStartingDeck");

        ArrayList<String> retVal = new ArrayList<String>();
        retVal.add("FireBladeMod:StrikeFireBlade");
        retVal.add("FireBladeMod:StrikeFireBlade");
        retVal.add("FireBladeMod:StrikeFireBlade");
        retVal.add("FireBladeMod:StrikeFireBlade");
        retVal.add("FireBladeMod:DefendFireBlade");
        retVal.add("FireBladeMod:DefendFireBlade");
        retVal.add("FireBladeMod:DefendFireBlade");
        retVal.add("FireBladeMod:DefendFireBlade");
        retVal.add("FireBladeMod:IntricateCombo");
        retVal.add("FireBladeMod:SpreadFire");

        logger.info("End TheFireBlade getStartingDeck");

        return retVal;
    }

    public AbstractCard getStartCardForEvent() {
        return new SpreadFire();
    }

    public ArrayList<String> getStartingRelics() {
        logger.info("Start TheFireBlade getStartingRelics");

        ArrayList<String> retVal = new ArrayList<String>();
        retVal.add("FireBladeMod:RedStar");

        logger.info("End TheFireBlade getStartingRelics");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], 70, 70, 0, 99, 5, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    public AbstractCard.CardColor getCardColor() {
        return TheFireBladeEnum.THE_FIREBLADE_ORANGE;
    }

    public Color getCardRenderColor() {
        return Color.ORANGE.cpy();
    }

    public Color getCardTrailColor() {
        return Color.ORANGE.cpy();
    }

    public Texture getEnergyImage() {
        return ImageMaster.RED_ORB_FLASH_VFX;
    }

    public int getAscensionMaxHPLoss() {
        return 5;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
    }

    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    public CharStat getCharStat() {
        return this.charStat;
    }

    public boolean saveFileExists() {
        return SaveAndContinue.saveExistsAndNotCorrupted(this);
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    public Texture getCustomModeCharacterButtonImage() {
        return ImageMaster.FILTER_IRONCLAD;
    }

    public CharacterStrings getCharacterString() {
        return CardCrawlGame.languagePack.getCharacterString("FireBlade");
    }

    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    public void refreshCharStat() {
        this.charStat = new CharStat(this);
    }

    public AbstractPlayer newInstance() {
        return new TheFireBlade(this.name);
    }

    public TextureAtlas.AtlasRegion getOrb() {
        return AbstractCard.orb_red;
    }

    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
            e.setTimeScale(0.6F);
        }

        super.damage(info);
    }

    public String getSpireHeartText() {
        return TEXT[1];
    }

    public Color getSlashAttackColor() {
        return Color.ORANGE.cpy();
    }

    public String getVampireText() {
        return TEXT[2];
    }

    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<CutscenePanel>();
        panels.add(new CutscenePanel("theFireBladeResources/images/fireBladeCharacter/victory/panel_1.png"));
        panels.add(new CutscenePanel("theFireBladeResources/images/fireBladeCharacter/victory/panel_2.png"));
        panels.add(new CutscenePanel("theFireBladeResources/images/fireBladeCharacter/victory/panel_3.png", "PartyHorn"));
        return panels;
    }

    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("theFireBladeResources/images/fireBladeCharacter/victory/background.png");
    }
}