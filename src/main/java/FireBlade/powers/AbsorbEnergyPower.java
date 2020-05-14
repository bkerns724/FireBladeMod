package FireBlade.powers;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbsorbEnergyPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:AbsorbEnergyPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean triggeredThisTurn = false;

    public AbsorbEnergyPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:AbsorbEnergyPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/AbsorbEnergy32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/AbsorbEnergy84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        triggeredThisTurn = false;
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        super.wasHPLost(info, damageAmount);
        if (!triggeredThisTurn) {
            flash();
            triggeredThisTurn = true;
            AbstractPlayer p = AbstractDungeon.player;
            addToTop(new ApplyPowerAction(p, p, new EnergizedFireBladePower(p, amount), amount));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
