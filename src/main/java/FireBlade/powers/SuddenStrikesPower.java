package FireBlade.powers;

import FireBlade.cards.Other.SuddenStrikesHelper;
import basemod.interfaces.OnPowersModifiedSubscriber;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SuddenStrikesPower extends AbstractPower implements OnPowersModifiedSubscriber {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:SuddenStrikesPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private SuddenStrikesHelper helperCard;

    public SuddenStrikesPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:SuddenStrikesPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Strikes32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Strikes84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        this.helperCard = new SuddenStrikesHelper();

        updateDescription();
    }

    public void receivePowersModified() { updateDescription(); }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type != AbstractCard.CardType.ATTACK)
            return;

        AbstractPlayer p = AbstractDungeon.player;
        for (int i = 0; i < this.amount; i++) {
            AbstractMonster mo = AbstractDungeon.getRandomMonster();
            helperCard.calculateCardDamage(mo);
            addToBot(new DamageAction(mo, new DamageInfo(p, helperCard.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
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

        if (this.amount == 1)
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + colorString + helperCard.damage + DESCRIPTIONS[3];
        else
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + colorString + helperCard.damage + DESCRIPTIONS[3];
    }
}
