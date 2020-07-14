package FireBlade.characters;

import FireBlade.actions.DelayedRoarAction;
import FireBlade.cards.Basics.DefendFireBlade;
import FireBlade.cards.Basics.ScorchingStrike;
import FireBlade.cards.Commons.FireBarrier;
import FireBlade.cards.Commons.FlamingSword;
import FireBlade.cards.Basics.StrikeFireBlade;
import FireBlade.relics.DuelistLocket;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.daily.mods.BlueCards;
import com.megacrit.cardcrawl.daily.mods.Chimera;
import com.megacrit.cardcrawl.daily.mods.Diverse;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import FireBlade.enums.*;
import FireBlade.FireBladeMod;

public class TheFireBlade extends CustomPlayer {

    private static final Logger logger = FireBladeMod.logger;

    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("FireBladeMod:FireBladeCharacter");
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    private CharStat charStat;

    private static final String[] ORB_TEXTURES = {
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

    private static final String[] VICTORY_PANELS = {
            "theFireBladeResources/images/fireBladeCharacter/victory/panel_1.png",
            "theFireBladeResources/images/fireBladeCharacter/victory/panel_2.png",
            "theFireBladeResources/images/fireBladeCharacter/victory/panel_3.png"
    };

    private static final String VICTORY_BACKGROUND = "theFireBladeResources/images/fireBladeCharacter/victory/background.png";

    private static final String ORB_VFX = "theFireBladeResources/images/fireBladeCharacter/orb/vfx.png";
    private static final String SHOULDER_IMG_1 = "theFireBladeResources/images/fireBladeCharacter/shoulder.png";
    private static final String SHOULDER_IMG_2 = "theFireBladeResources/images/fireBladeCharacter/shoulder2.png";
    private static final String CORPSE_IMG = "theFireBladeResources/images/fireBladeCharacter/corpse.png";

    private static final String SKELETON_ATLAS = "theFireBladeResources/images/fireBladeCharacter/idle/skeleton.atlas";
    private static final String SKELETON_JSON = "theFireBladeResources/images/fireBladeCharacter/idle/skeleton.json";

    private static final int MAX_HP = 70;
    private static final int STARTING_GOLD = 99;
    private static final int ORB_SLOTS = 0;
    private static final int CARD_DRAW = 5;
    private static final int ASC_MAX_HP_LOSS = MAX_HP/20;

    public TheFireBlade(String playerName) {
        super(playerName, FireBladeEnum.THE_FIREBLADE, ORB_TEXTURES, ORB_VFX, null, (String) null);
        logger.info("Start TheFireBlade constructor");
        charStat = new CharStat(this);

        dialogX = drawX + 0.0F * Settings.scale;
        dialogY = drawY + 220.0F * Settings.scale;

        logger.info("Start TheFireBlade initializeClass");
        initializeClass(null, SHOULDER_IMG_2,
                SHOULDER_IMG_1,
                CORPSE_IMG,
                getLoadout(), -4.0F, -16.0F, 220.0F, 290.0F, new EnergyManager(3));

        logger.info("Start TheFireBlade animation");
        loadAnimation(SKELETON_ATLAS, SKELETON_JSON, 1.0F);
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(0.6F);

        logger.info("Start TheFireBlade modHelper");
        if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled(Diverse.ID) || ModHelper.isModEnabled(Chimera.ID) ||
                ModHelper.isModEnabled(BlueCards.ID))) {
            masterMaxOrbs = 1;
        }

        logger.info("End TheFireBlade constructor");
    }

    public ArrayList<String> getStartingDeck() {
        logger.info("Start TheFireBlade getStartingDeck");

        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            retVal.add(StrikeFireBlade.ID);
        for (int i = 0; i < 4; i++)
            retVal.add(DefendFireBlade.ID);
        retVal.add(ScorchingStrike.ID);
        retVal.add(ScorchingStrike.ID);

        logger.info("End TheFireBlade getStartingDeck");

        return retVal;
    }

    public AbstractCard getStartCardForEvent() {
        return new ScorchingStrike();
    }

    public ArrayList<String> getStartingRelics() {
        logger.info("Start TheFireBlade getStartingRelics");

        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(DuelistLocket.ID);

        logger.info("End TheFireBlade getStartingRelics");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], MAX_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    public AbstractCard.CardColor getCardColor() {
        return FireBladeEnum.FIREBLADE_ORANGE;
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
        return ASC_MAX_HP_LOSS;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        energyOrb.renderOrb(sb, enabled, current_x, current_y);
    }

    public void updateOrb(int orbCount) {
        energyOrb.updateOrb(orbCount);
    }

    public CharStat getCharStat() {
        return charStat;
    }

    public boolean saveFileExists() {
        return SaveAndContinue.saveExistsAndNotCorrupted(this);
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.8F);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_FIRE";
    }

    public CharacterStrings getCharacterString() {
        return characterStrings;
    }

    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    public void refreshCharStat() {
        charStat = new CharStat(this);
    }

    public AbstractPlayer newInstance() {
        return new TheFireBlade(name);
    }

    public TextureAtlas.AtlasRegion getOrb() {
        return AbstractCard.orb_red;
    }

    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - currentBlock > 0) {
            AnimationState.TrackEntry e = state.setAnimation(0, "Hit", false);
            state.addAnimation(0, "Idle", true, 0.0F);
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
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel(VICTORY_PANELS[0]));
        panels.add(new CutscenePanel(VICTORY_PANELS[1]));
        panels.add(new CutscenePanel(VICTORY_PANELS[2], FireBladeMod.PARTY_HORN_KEY));
        return panels;
    }

    public Texture getCutsceneBg() {
        return ImageMaster.loadImage(VICTORY_BACKGROUND);
    }

    @Override
    public void applyStartOfTurnPostDrawRelics() {
        super.applyStartOfTurnPostDrawRelics();
        if (GameActionManager.turn == 2) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (m.id.equals(GremlinNob.ID)) {
                    AbstractDungeon.actionManager.addToBottom(new DelayedRoarAction());
                    return;
                }
            }
        }
    }
}