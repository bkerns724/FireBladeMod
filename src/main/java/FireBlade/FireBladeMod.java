package FireBlade;

import FireBlade.other.FireBladeSettings;
import FireBlade.other.FireBladeTipTracker;
import FireBlade.potions.LiquidSteroids;
import FireBlade.potions.NapalmFlask;
import FireBlade.potions.ProteinShake;
import FireBlade.variables.MagicNumberTwo;
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
import FireBlade.cards.Basics.*;
import FireBlade.cards.Commons.*;
import FireBlade.cards.Uncommons.*;
import FireBlade.cards.Rares.*;
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
        PostInitializeSubscriber{

    public static final Logger logger = LogManager.getLogger(FireBladeMod.class.getName());
    private static String modID;

    private static final Color CUSTOM_COLOR = CardHelper.getColor(246.0F, 154.0F, 45.0F);

    private static TheFireBlade theFireBladeCharacter;

    // Character assets
    private static final String THE_FIREBLADE_BUTTON = "theFireBladeResources/images/charSelect/FireBladeCharacterButton.png";
    private static final String THE_FIREBLADE_PORTRAIT = "theFireBladeResources/images/charSelect/FireBladeCharacterPortrait.png";

    public FireBladeMod() {
        BaseMod.subscribe(this);

        logger.info("Adding mod settings");
        FireBladeSettings.initialize();
        FireBladeTipTracker.initialize();
        logger.info("Done adding mod settings");

        BaseMod.addColor(TheFireBladeEnum.THE_FIREBLADE_ORANGE, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR, CUSTOM_COLOR,

                "theFireBladeResources/images/cardBackgrounds512/bg_attack_orange.png",
                "theFireBladeResources/images/cardBackgrounds512/bg_skill_orange.png",
                "theFireBladeResources/images/cardBackgrounds512/bg_power_orange.png",
                "theFireBladeResources/images/cardBackgrounds512/card_orange_orb.png",
                "theFireBladeResources/images/cardBackgrounds1024/bg_attack_orange.png",
                "theFireBladeResources/images/cardBackgrounds1024/bg_skill_orange.png",
                "theFireBladeResources/images/cardBackgrounds1024/bg_power_orange.png",
                "theFireBladeResources/images/cardBackgrounds1024/card_orange_orb.png",
                "theFireBladeResources/images/cardBackgrounds512/card_small_orb.png");

        setModID("theFireBlade");
    }

    public static void setModID(String ID) {
        modID = ID;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        logger.info("========================= Initializing FireBlade Mod. Hi. =========================");
        FireBladeMod fireBladeMod = new FireBladeMod();
        logger.info("========================= /FireBlade Mod Initialized. Hello World./ =========================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to add FireBlade character");

        theFireBladeCharacter = new TheFireBlade("The FireBlade");

        BaseMod.addCharacter(theFireBladeCharacter,
                THE_FIREBLADE_BUTTON,
                THE_FIREBLADE_PORTRAIT, TheFireBladeEnum.THE_FIREBLADE);

        logger.info("Added FireBlade character.");
    }

    public void receiveEditCards() {
        logger.info("Beginning to add FireBlade cards");

        BaseMod.addDynamicVariable(new MagicNumberTwo());

        logger.info("Starter");

        BaseMod.addCard(new StrikeFireBlade());
        BaseMod.addCard(new DefendFireBlade());
        BaseMod.addCard(new SpreadFire());
        BaseMod.addCard(new IntricateCombo());

        logger.info("Common");

        BaseMod.addCard(new ArmorStrike());
        BaseMod.addCard(new BasicSmash());
        BaseMod.addCard(new ButtHeads());
        BaseMod.addCard(new ComplexAttack());
        BaseMod.addCard(new Flailing());
        BaseMod.addCard(new FlameStrike());
        BaseMod.addCard(new NerveStrike());
        BaseMod.addCard(new SimpleBlow());
        BaseMod.addCard(new SneakAttack());
        BaseMod.addCard(new SpinningSmash());
        BaseMod.addCard(new SwordCleave());
        BaseMod.addCard(new WaveOfIron());

        BaseMod.addCard(new BackShrug());
        BaseMod.addCard(new ComplexDefense());
        BaseMod.addCard(new DefensiveFlailing());
        BaseMod.addCard(new IronEndurance());
        BaseMod.addCard(new ShootFlames());
        BaseMod.addCard(new SimpleBlock());
        BaseMod.addCard(new SteadyEndurance());
        BaseMod.addCard(new WellPrepared());

        logger.info("Uncommon");

        BaseMod.addCard(new ArmSmash());
        BaseMod.addCard(new FlamingCleave());
        BaseMod.addCard(new FlameTrail());
        BaseMod.addCard(new GasolineStrike());
        BaseMod.addCard(new IntenseFlailing());
        BaseMod.addCard(new QuickAttack());
        BaseMod.addCard(new SneakFlurry());
        BaseMod.addCard(new SolidAttack());
        BaseMod.addCard(new TorsoSmash());

        BaseMod.addCard(new BarrierOfFlames());
        BaseMod.addCard(new DefensiveFocus());
        BaseMod.addCard(new FeedTheFlame());
        BaseMod.addCard(new Fireball());
        BaseMod.addCard(new FireWave());
        BaseMod.addCard(new PyromanticFocus());
        BaseMod.addCard(new Feint());
        BaseMod.addCard(new OffensiveFocus());
        BaseMod.addCard(new Punish());
        BaseMod.addCard(new QuickParry());
        BaseMod.addCard(new RampingBlock());
        BaseMod.addCard(new RegenerativeEndurance());
        BaseMod.addCard(new Reposition());
        BaseMod.addCard(new SolidBlock());
        BaseMod.addCard(new SteelEndurance());
        BaseMod.addCard(new Unbalance());

        BaseMod.addCard(new DefensiveFlames());
        BaseMod.addCard(new FireBladeBerserk());
        BaseMod.addCard(new Flexibility());
        BaseMod.addCard(new GetPumped());
        BaseMod.addCard(new Dodge());
        BaseMod.addCard(new ThirdDegree());

        logger.info("Rare");

        BaseMod.addCard(new DashityDash());
        BaseMod.addCard(new FinalSmash());
        BaseMod.addCard(new GlassGreatsword());
        BaseMod.addCard(new ThirstForBlood());
        BaseMod.addCard(new TickleTorture());

        BaseMod.addCard(new CitrusTablets());
        BaseMod.addCard(new EternalEndurance());
        BaseMod.addCard(new HellFire());
        BaseMod.addCard(new Convert());
        BaseMod.addCard(new Improvise());
        BaseMod.addCard(new LayeredDefense());
        BaseMod.addCard(new Steroids());
        BaseMod.addCard(new Tempo());
        BaseMod.addCard(new ClingingFlames());

        BaseMod.addCard(new AfterBurn());
        BaseMod.addCard(new FlameForm());
        BaseMod.addCard(new Reserves());
        BaseMod.addCard(new PrettyGoodPlans());
        BaseMod.addCard(new TungstenSkin());
        BaseMod.addCard(new UnexpectedStrikes());

        logger.info("Added FireBlade cards");
    }

    public void receiveEditRelics() {
        logger.info("Beginning to add FireBlade relics");
        BaseMod.addRelicToCustomPool(new RedStar(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new CrimsonStar(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new Matches(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new BronzeKnuckles(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new WheyBottle(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new FirePoi(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        if (!FireBladeSettings.isGoldenStarGlobal())
            BaseMod.addRelicToCustomPool(new GoldenStar(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        else
            BaseMod.addRelic(new GoldenStar(), RelicType.SHARED);
        BaseMod.addRelicToCustomPool(new GymTowel(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        if (!FireBladeSettings.isInnerFlameGlobal())
            BaseMod.addRelicToCustomPool(new InnerFlame(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        else
            BaseMod.addRelic(new InnerFlame(), RelicType.SHARED);
        BaseMod.addRelicToCustomPool(new TigerClaw(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        BaseMod.addRelicToCustomPool(new Planner(), TheFireBladeEnum.THE_FIREBLADE_ORANGE);
        logger.info("Added FireBlade relics");
    }

    public void receiveEditStrings() {
        logger.info("Beginning to add FireBlade strings");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.RelicStrings.class, "theFireBladeResources/localization/eng/RelicStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CardStrings.class, "theFireBladeResources/localization/eng/CardStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CharacterStrings.class, "theFireBladeResources/localization/eng/CharacterStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.PowerStrings.class, "theFireBladeResources/localization/eng/PowerStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.PotionStrings.class, "theFireBladeResources/localization/eng/PotionStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.OrbStrings.class, "theFireBladeResources/localization/eng/OrbStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.UIStrings.class, "theFireBladeResources/localization/eng/UiStrings.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.TutorialStrings.class, "theFireBladeResources/localization/eng/TutorialStrings.json");
        logger.info("Added FireBlade strings");
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("PartyHorn", "theFireBladeResources/audio/PartyHorn.ogg");
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
        BaseMod.addPotion(NapalmFlask.class, Color.RED.cpy(), null, Color.ORANGE.cpy(), NapalmFlask.POTION_ID, TheFireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(ProteinShake.class, Color.TAN.cpy(), Color.BROWN.cpy(), null, ProteinShake.POTION_ID, TheFireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(LiquidSteroids.class, Color.RED.cpy(), Color.BLACK.cpy(), null, LiquidSteroids.POTION_ID, TheFireBladeEnum.THE_FIREBLADE);

        logger.info("Load Badge Image and make settings panel");
        Texture badgeTexture = new Texture("theFireBladeResources/images/Badge.png");
        BaseMod.registerModBadge(badgeTexture, "The FireBlade", "Bryan", "This mod adds a new character, the FireBlade.",
                FireBladeSettings.createSettingsPanel());
        logger.info("Done loading badge Image");
    }
}