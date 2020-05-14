package FireBlade.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BurningPower extends AbstractPower implements HealthBarRenderPower {
    public static AbstractPower.PowerType POWER_TYPE = AbstractPower.PowerType.DEBUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:BurningPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final String POWER_ID = "FireBladeMod:BurningPower";
    
    private AbstractCreature source;

    public BurningPower(AbstractCreature owner, AbstractCreature source, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        this.source = source;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Burning32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Burning84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    public void updateDescription() {
        int textAmount = getDamageAmount();
        if (owner == AbstractDungeon.player)
            description = DESCRIPTIONS[0] + textAmount + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[2] + textAmount + DESCRIPTIONS[1];
    }

    private int getDamageAmount() {
        int rendAmount = 0;
        if (owner.hasPower(SpiritRendPower.POWER_ID))
            rendAmount = owner.getPower(SpiritRendPower.POWER_ID).amount;
        return (int)Math.floor((1 + SpiritRendPower.BURN_MULT*rendAmount)*amount);
    }

    public void atStartOfTurn() {
        int damage = getDamageAmount();
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
            !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new LoseHPAction(owner, source, damage, AbstractGameAction.AttackEffect.FIRE));
        }
    }
    
    public int getHealthBarAmount() {
        updateDescription();
        return getDamageAmount();
    }
    public Color getColor() { return Color.YELLOW.cpy(); }
}
