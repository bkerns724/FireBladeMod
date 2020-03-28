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
    
    private AbstractCreature source;

    public BurningPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.ID = "FireBladeMod:BurningPower";
        this.owner = owner;
        this.source = source;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Burning32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Burning84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }

    public void atStartOfTurn() {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
            !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE));
        }
    }
    
    public int getHealthBarAmount() { return this.amount; }
    public Color getColor() { return Color.ORANGE; }
}
