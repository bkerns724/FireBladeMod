package FireBlade;

import FireBlade.cards.Other.Ember;
import FireBlade.cards.Other.Necronomisword;
import FireBlade.cards.Other.Swipe;
import FireBlade.ui.FireBladeSettings;
import FireBlade.ui.FireBladeTipTracker;
import FireBlade.potions.VigorPotion;
import FireBlade.potions.NapalmFlask;
import FireBlade.potions.FervorPotion;
import FireBlade.variables.MagicNumberTwo;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import FireBlade.characters.TheFireBlade;
import FireBlade.enums.*;
import FireBlade.relics.*;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class FireBladeMod implements
        EditCharactersSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
        AddAudioSubscriber,
        PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(FireBladeMod.class.getName());
    private static final String MOD_ID = "FireBladeMod";

    public static final Color FIREBLADE_EYE_COLOR = CardHelper.getColor(246.0F, 154.0F, 45.0F);

    private static final String RELIC_STRINGS = "theFireBladeResources/localization/eng/RelicStrings.json";
    private static final String CARD_STRINGS = "theFireBladeResources/localization/eng/CardStrings.json";
    private static final String CHARACTER_STRINGS = "theFireBladeResources/localization/eng/CharacterStrings.json";
    private static final String POWER_STRINGS = "theFireBladeResources/localization/eng/PowerStrings.json";
    private static final String POTION_STRINGS = "theFireBladeResources/localization/eng/PotionStrings.json";
    private static final String UI_STRINGS = "theFireBladeResources/localization/eng/UiStrings.json";
    private static final String TUTORIAL_STRINGS = "theFireBladeResources/localization/eng/TutorialStrings.json";
    private static final String KEYWORD_STRINGS = "theFireBladeResources/localization/eng/KeywordStrings.json";

    // Character assets
    private static final String THE_FIREBLADE_BUTTON = "theFireBladeResources/images/charSelect/FireBladeCharacterButton.png";
    private static final String THE_FIREBLADE_PORTRAIT = "theFireBladeResources/images/charSelect/FireBladeCharacterPortrait.png";

    public static final String PARTY_HORN_KEY = "FireBladeMod:PartyHorn";
    private static final String PARTY_HORN_OGG = "theFireBladeResources/audio/PartyHorn.ogg";
    public static final String ROAR_KEY = "FireBladeMod:CounterRawr";
    private static final String ROAR_OGG = "theFireBladeResources/audio/FireBladeRawr.ogg";

    private static final String ATTACK_BACKGROUND = "theFireBladeResources/images/cardBackgrounds512/bg_attack_orange.png";
    private static final String SKILL_BACKGROUND = "theFireBladeResources/images/cardBackgrounds512/bg_skill_orange.png";
    private static final String POWER_BACKGROUND = "theFireBladeResources/images/cardBackgrounds512/bg_power_orange.png";
    private static final String ATTACK_BACKGROUND_PORTRAIT = "theFireBladeResources/images/cardBackgrounds1024/bg_attack_orange.png";
    private static final String SKILL_BACKGROUND_PORTRAIT = "theFireBladeResources/images/cardBackgrounds1024/bg_skill_orange.png";
    private static final String POWER_BACKGROUND_PORTRAIT = "theFireBladeResources/images/cardBackgrounds1024/bg_power_orange.png";
    private static final String ORB_IMG = "theFireBladeResources/images/cardBackgrounds512/card_orange_orb.png";
    private static final String ORB_PORTRAIT = "theFireBladeResources/images/cardBackgrounds1024/card_orange_orb.png";
    private static final String ORB_SMALL_IMG = "theFireBladeResources/images/cardBackgrounds512/card_small_orb.png";

    private static final String BADGE_IMG = "theFireBladeResources/images/Badge.png";
    private static final String[] REGISTRATION_STRINGS = {
            "The FireBlade", "Bryan", "This mod adds a new character, the FireBlade."
    };

    private static final String[] CARD_PACKAGES = {
        "FireBlade.cards.Basics", "FireBlade.cards.Commons", "FireBlade.cards.Uncommons", "FireBlade.cards.Rares"
        };

    public FireBladeMod() {
        BaseMod.subscribe(this);

        logger.info("Adding mod settings");
        FireBladeTipTracker.initialize();
        logger.info("Done adding mod settings");

        BaseMod.addColor(FireBladeEnum.FIREBLADE_ORANGE, FIREBLADE_EYE_COLOR,

                ATTACK_BACKGROUND,
                SKILL_BACKGROUND,
                POWER_BACKGROUND,
                ORB_IMG,
                ATTACK_BACKGROUND_PORTRAIT,
                SKILL_BACKGROUND_PORTRAIT,
                POWER_BACKGROUND_PORTRAIT,
                ORB_PORTRAIT,
                ORB_SMALL_IMG
                );
    }

    public static String getModID() {
        return MOD_ID;
    }

    public static void initialize() {
        logger.info("========================= Initializing FireBlade Mod. =========================");
        FireBladeMod fireBladeMod = new FireBladeMod();
        logger.info("========================= /FireBlade Mod Initialized./ =========================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to add FireBlade character");

        TheFireBlade theFireBladeCharacter = new TheFireBlade("The FireBlade");

        BaseMod.addCharacter(theFireBladeCharacter, THE_FIREBLADE_BUTTON,
                THE_FIREBLADE_PORTRAIT, FireBladeEnum.THE_FIREBLADE);

        logger.info("Added FireBlade character.");
    }

    public void receiveEditCards() {
        logger.info("Beginning to add FireBlade cards");

        BaseMod.addDynamicVariable(new MagicNumberTwo());
        for (String cardPackage : CARD_PACKAGES)
            (new AutoAdd(MOD_ID)).packageFilter(cardPackage).setDefaultSeen(true).cards();

        BaseMod.addCard(new Swipe());
        BaseMod.addCard(new Ember());
        BaseMod.addCard(new Necronomisword());

        logger.info("Added FireBlade cards");
    }

    public void receiveEditRelics() {
        logger.info("Beginning to add FireBlade relics");

        BaseMod.addRelicToCustomPool(new DuelistLocket(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new BattleMasterLocket(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new Matches(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new BronzeKnuckles(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new WheyBottle(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new FirePoi(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new LegBrace(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new GymTowel(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new TigerClaw(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new Chakram(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new InnerFlame(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new SearingSword(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new FlameCatalyst(), FireBladeEnum.FIREBLADE_ORANGE);

        logger.info("Added FireBlade relics");
    }

    public void receiveEditStrings() {
        logger.info("Beginning to add FireBlade strings");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.RelicStrings.class, RELIC_STRINGS);
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CardStrings.class, CARD_STRINGS);
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CharacterStrings.class, CHARACTER_STRINGS);
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.PowerStrings.class, POWER_STRINGS);
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.PotionStrings.class, POTION_STRINGS);
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.UIStrings.class, UI_STRINGS);
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.TutorialStrings.class, TUTORIAL_STRINGS);
        logger.info("Added FireBlade strings");
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(PARTY_HORN_KEY, PARTY_HORN_OGG);
        BaseMod.addAudio(ROAR_KEY, ROAR_OGG);
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();

        logger.info("begin editing strings");
        String json = Gdx.files.internal(KEYWORD_STRINGS).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords)
                BaseMod.addKeyword(MOD_ID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addPotion(NapalmFlask.class, Color.RED.cpy(), null, Color.ORANGE.cpy(), NapalmFlask.POTION_ID, FireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(FervorPotion.class, Color.PURPLE, null, Color.CYAN, FervorPotion.POTION_ID, FireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(VigorPotion.class, Color.RED.cpy(), Color.BLACK.cpy(), null, VigorPotion.POTION_ID, FireBladeEnum.THE_FIREBLADE);

        logger.info("Load Badge Image and make settings panel");
        Texture badgeTexture = new Texture(BADGE_IMG);
        BaseMod.registerModBadge(badgeTexture, REGISTRATION_STRINGS[0], REGISTRATION_STRINGS[1], REGISTRATION_STRINGS[2],
                FireBladeSettings.createSettingsPanel());
        logger.info("Done loading badge Image");
    }
}