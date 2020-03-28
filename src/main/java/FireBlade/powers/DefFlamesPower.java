package FireBlade.powers;

import FireBlade.FireBladeMod;
import FireBlade.cards.TheFireBladeCardTags;
import FireBlade.enums.TheFireBladeEnum;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DefFlamesPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:DefFlamesPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DefFlamesPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:DefFlamesPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/DefFlames32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/DefFlames84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(TheFireBladeCardTags.BURNER)) {
            if (Settings.FAST_MODE) {
                addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.amount, true));
            } else {
                addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));
            }
            flash();
        }
    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
}
