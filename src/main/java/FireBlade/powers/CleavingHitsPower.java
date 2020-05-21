package FireBlade.powers;

import FireBlade.actions.ComboHitsAction;
import FireBlade.cards.Other.ComboHitsHelper;
import basemod.BaseMod;
import basemod.interfaces.OnPowersModifiedSubscriber;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CleavingHitsPower extends AbstractPower implements OnPowersModifiedSubscriber {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:CleavingHitsPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private ComboHitsHelper helperCard;
    private boolean triggeredThisTurn;

    public CleavingHitsPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:CleavingHitsPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/CleavingHits32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/CleavingHits84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        this.helperCard = new ComboHitsHelper();
        triggeredThisTurn = false;

        BaseMod.subscribe(this);

        updateDescription();
    }

    public void receivePowersModified() { updateDescription(); }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        triggeredThisTurn = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type != AbstractCard.CardType.ATTACK || triggeredThisTurn)
            return;

        triggeredThisTurn = true;
        AbstractPlayer p = AbstractDungeon.player;
        for (int i = 0; i < this.amount; i++) {
            addToBot(new ComboHitsAction(p, helperCard, card.hasTag(AbstractCard.CardTags.STRIKE)));
        }
    }

    public void updateDescription() {
        helperCard.applyPowers();
        String colorString;
        if (helperCard.damage == helperCard.baseDamage)
            colorString = "#b";
        else if (helperCard.damage > helperCard.baseDamage)
            colorString = "#g";
        else
            colorString = "#r";

        if (amount == 1)
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + colorString + helperCard.damage + DESCRIPTIONS[3];
        else
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2] + colorString + helperCard.damage + DESCRIPTIONS[3];
    }
}
