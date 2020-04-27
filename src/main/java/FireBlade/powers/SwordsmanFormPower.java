package FireBlade.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class SwordsmanFormPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:SwordsmanFormPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final String POWER_ID = "FireBladeMod:SwordsmanFormPower";

    public SwordsmanFormPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/SwordsmanForm32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/SwordsmanForm84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, amount), amount));
    }

    public void updateDescription() { description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }
}
