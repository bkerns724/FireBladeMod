package FireBlade;

import FireBlade.cards.Other.Swipe;
import FireBlade.other.FireBladeSettings;
import FireBlade.other.FireBladeTipTracker;
import FireBlade.potions.VigorPotion;
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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
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
        PostInitializeSubscriber,
        OnPowersModifiedSubscriber {

    private static final Logger logger = LogManager.getLogger(FireBladeMod.class.getName());
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
        logger.info("========================= Initializing FireBlade Mod. =========================");
        FireBladeMod fireBladeMod = new FireBladeMod();
        logger.info("========================= /FireBlade Mod Initialized./ =========================");
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

        logger.info("Other");

        BaseMod.addCard(new Swipe());

        logger.info("Starter");

        BaseMod.addCard(new DefendFireBlade());
        BaseMod.addCard(new Ember());
        BaseMod.addCard(new IntricateCombo());
        BaseMod.addCard(new StrikeFireBlade());

        logger.info("Common");

        BaseMod.addCard(new ArmorStrike());
        BaseMod.addCard(new BackhandSwing());
        BaseMod.addCard(new BasicSmash());
        BaseMod.addCard(new ComboCleave());
        BaseMod.addCard(new ComboSlashes());
        BaseMod.addCard(new ComplexAttack());
        BaseMod.addCard(new SpiritBurn());
        BaseMod.addCard(new ComplexDefense());
        BaseMod.addCard(new FireBarrier());
        BaseMod.addCard(new Flames());
        BaseMod.addCard(new IronEndurance());
        BaseMod.addCard(new NerveStrike());
        BaseMod.addCard(new ScorchingStrike());
        BaseMod.addCard(new Shove());
        BaseMod.addCard(new SolidBlock());
        BaseMod.addCard(new SpinningSmash());
        BaseMod.addCard(new SteadyEndurance());
        BaseMod.addCard(new Turtle());
        BaseMod.addCard(new UnstoppableThrust());
        BaseMod.addCard(new WellPrepared());

        logger.info("Uncommon");

        BaseMod.addCard(new AbsorbEnergy());
        BaseMod.addCard(new ArmSmash());
        BaseMod.addCard(new Arson());
        BaseMod.addCard(new WildSlashes());
        BaseMod.addCard(new Blitzkreig());
        BaseMod.addCard(new BuildUp());
        BaseMod.addCard(new CleansingFlame());
        BaseMod.addCard(new AdrenalineBoost());
        BaseMod.addCard(new EruptionSlash());
        BaseMod.addCard(new Feint());
        BaseMod.addCard(new Fireball());
        BaseMod.addCard(new FireWave());
        BaseMod.addCard(new FlashPoint());
        BaseMod.addCard(new ProbingAttack());
        BaseMod.addCard(new Accelerant());
        BaseMod.addCard(new QuickJabs());
        BaseMod.addCard(new QuickCombo());
        BaseMod.addCard(new RampingDefense());
        BaseMod.addCard(new RapidDodges());
        BaseMod.addCard(new RegenerativeEndurance());
        BaseMod.addCard(new Reposition());
        BaseMod.addCard(new ScorchingCleave());
        BaseMod.addCard(new SmokeScreen());
        BaseMod.addCard(new SmokySwing());
        BaseMod.addCard(new SolidAttack());
        BaseMod.addCard(new Speed());
        BaseMod.addCard(new SpiritRend());
        BaseMod.addCard(new SteelEndurance());
        BaseMod.addCard(new ThirdDegree());
        BaseMod.addCard(new TorsoSmash());
        BaseMod.addCard(new Vitality());

        logger.info("Rare");


        BaseMod.addCard(new AfterBurn());
        BaseMod.addCard(new DebilitatingBlow());
        BaseMod.addCard(new EternalEndurance());
        BaseMod.addCard(new EternalFlame());
        BaseMod.addCard(new ExtraStrikes());
        BaseMod.addCard(new FinalSmash());
        BaseMod.addCard(new FlameForm());
        BaseMod.addCard(new Armageddon());
        BaseMod.addCard(new GatherPower());
        BaseMod.addCard(new GlassGreatsword());
        BaseMod.addCard(new HellFire());
        BaseMod.addCard(new Improvise());
        BaseMod.addCard(new LayeredDefense());
        BaseMod.addCard(new LightningCombo());
        BaseMod.addCard(new Reserves());
        BaseMod.addCard(new SelfConsumption());
        BaseMod.addCard(new Shell());
        BaseMod.addCard(new Tempo());
        BaseMod.addCard(new ThirstForBlood());
        BaseMod.addCard(new Unbalance());

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
        BaseMod.addPotion(NapalmFlask.class, Color.RED.cpy(), null, Color.ORANGE.cpy(), NapalmFlask.POTION_ID, TheFireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(ProteinShake.class, Color.TAN.cpy(), Color.BROWN.cpy(), null, ProteinShake.POTION_ID, TheFireBladeEnum.THE_FIREBLADE);
        BaseMod.addPotion(VigorPotion.class, Color.RED.cpy(), Color.BLACK.cpy(), null, VigorPotion.POTION_ID, TheFireBladeEnum.THE_FIREBLADE);

        logger.info("Load Badge Image and make settings panel");
        Texture badgeTexture = new Texture("theFireBladeResources/images/Badge.png");
        BaseMod.registerModBadge(badgeTexture, "The FireBlade", "Bryan", "This mod adds a new character, the FireBlade.",
                FireBladeSettings.createSettingsPanel());
        logger.info("Done loading badge Image");
    }

    public void receivePowersModified() {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnPowersModifiedSubscriber) {
                    ((OnPowersModifiedSubscriber)p).receivePowersModified();
                }
            }
        }
    }
}