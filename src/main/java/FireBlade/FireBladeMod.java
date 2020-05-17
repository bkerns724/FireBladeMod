package FireBlade;

import FireBlade.cards.Other.Swipe;
import FireBlade.ui.FireBladeSettings;
import FireBlade.ui.FireBladeTipTracker;
import FireBlade.potions.VigorPotion;
import FireBlade.potions.NapalmFlask;
import FireBlade.potions.FervorPotion;
import FireBlade.variables.MagicNumberTwo;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
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
    private static final String modID = "FireBladeMod";

    public static final Color FIREBLADE_EYE_COLOR = CardHelper.getColor(246.0F, 154.0F, 45.0F);

    private static TheFireBlade theFireBladeCharacter;

    // Character assets
    public static final String THE_FIREBLADE_BUTTON = "theFireBladeResources/images/charSelect/FireBladeCharacterButton.png";
    public static final String THE_FIREBLADE_PORTRAIT = "theFireBladeResources/images/charSelect/FireBladeCharacterPortrait.png";

    public FireBladeMod() {
        BaseMod.subscribe(this);

        logger.info("Adding mod settings");
        FireBladeSettings.initialize();
        FireBladeTipTracker.initialize();
        logger.info("Done adding mod settings");

        BaseMod.addColor(FireBladeEnum.FIREBLADE_ORANGE, FIREBLADE_EYE_COLOR,

                "theFireBladeResources/images/cardBackgrounds512/bg_attack_orange.png",
                "theFireBladeResources/images/cardBackgrounds512/bg_skill_orange.png",
                "theFireBladeResources/images/cardBackgrounds512/bg_power_orange.png",
                "theFireBladeResources/images/cardBackgrounds512/card_orange_orb.png",
                "theFireBladeResources/images/cardBackgrounds1024/bg_attack_orange.png",
                "theFireBladeResources/images/cardBackgrounds1024/bg_skill_orange.png",
                "theFireBladeResources/images/cardBackgrounds1024/bg_power_orange.png",
                "theFireBladeResources/images/cardBackgrounds1024/card_orange_orb.png",
                "theFireBladeResources/images/cardBackgrounds512/card_small_orb.png");
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        logger.info("========================= Initializing FireBlade Mod. =========================");
        FireBladeMod fireBladeMod = new FireBladeMod();
        logger.info("========================= /FireBlade Mod Initialized./ =========================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to add FireBlade character");

        theFireBladeCharacter = new TheFireBlade("The FireBlade");

        BaseMod.addCharacter(theFireBladeCharacter, THE_FIREBLADE_BUTTON,
                THE_FIREBLADE_PORTRAIT, FireBladeEnum.THE_FIREBLADE);

        logger.info("Added FireBlade character.");
    }

    public void receiveEditCards() {
        logger.info("Beginning to add FireBlade cards");

        BaseMod.addDynamicVariable(new MagicNumberTwo());
        (new AutoAdd("FireBladeMod")).packageFilter("FireBlade.cards.Basics").setDefaultSeen(true).cards();
        (new AutoAdd("FireBladeMod")).packageFilter("FireBlade.cards.Commons").setDefaultSeen(true).cards();
        (new AutoAdd("FireBladeMod")).packageFilter("FireBlade.cards.Uncommons").setDefaultSeen(true).cards();
        (new AutoAdd("FireBladeMod")).packageFilter("FireBlade.cards.Rares").setDefaultSeen(true).cards();
        BaseMod.addCard(new Swipe());

        logger.info("Added FireBlade cards");
    }

    public void receiveEditRelics() {
        logger.info("Beginning to add FireBlade relics");
        BaseMod.addRelicToCustomPool(new EternalTorch(), FireBladeEnum.FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new SoulTorch(), FireBladeEnum.FIREBLADE_ORANGE);
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

        if (!FireBladeSettings.isGoldenStarGlobal())
            BaseMod.addRelicToCustomPool(new GoldenStar(), FireBladeEnum.FIREBLADE_ORANGE);
        else
            BaseMod.addRelic(new GoldenStar(), RelicType.SHARED);

        if (!FireBladeSettings.isCrimsonStarGlobal())
            BaseMod.addRelicToCustomPool(new CrimsonStar(), FireBladeEnum.FIREBLADE_ORANGE);
        else
            BaseMod.addRelic(new CrimsonStar(), RelicType.SHARED);

        logger.info("Added FireBlade relics");
    }

    public void receiveEditStrings() {
        logger.info("Beginning to add FireBlade strings");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.RelicStrings.class, "theFireBladeResources/localization/eng/RelicStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CardStrings.class, "theFireBladeResources/localization/eng/CardStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CharacterStrings.class, "theFireBladeResources/localization/eng/CharacterStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.PowerStrings.class, "theFireBladeResources/localization/eng/PowerStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.PotionStrings.class, "theFireBladeResources/localization/eng/PotionStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.UIStrings.class, "theFireBladeResources/localization/eng/UiStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.TutorialStrings.class, "theFireBladeResources/localization/eng/TutorialStrings.json");
        logger.info("Added FireBlade strings");
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("FireBladeMod:PartyHorn", "theFireBladeResources/audio/PartyHorn.ogg");
        BaseMod.addAudio("FireBladeMod:CounterRawr", "theFireBladeResources/audio/FireBladeRawr.ogg");
    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();

        logger.info("begin editing strings");
        String json = Gdx.files.internal("theFireBladeResources/localization/eng/KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords)
                BaseMod.addKeyword("fireblademod", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addPotion(NapalmFlask.class, Color.RED.cpy(), null, Color.ORANGE.cpy(), NapalmFlask.POTION_ID, FireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(FervorPotion.class, Color.PURPLE, null, Color.CYAN, FervorPotion.POTION_ID, FireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(VigorPotion.class, Color.RED.cpy(), Color.BLACK.cpy(), null, VigorPotion.POTION_ID, FireBladeEnum.THE_FIREBLADE);

        logger.info("Load Badge Image and make settings panel");
        Texture badgeTexture = new Texture("theFireBladeResources/images/Badge.png");
        BaseMod.registerModBadge(badgeTexture, "The FireBlade", "Bryan", "This mod adds a new character, the FireBlade.",
                FireBladeSettings.createSettingsPanel());
        logger.info("Done loading badge Image");
    }
}