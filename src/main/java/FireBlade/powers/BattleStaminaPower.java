package FireBlade.powers;

import FireBlade.cards.FireBladeCardTags;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BattleStaminaPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    public static final String POWER_ID = "FireBladeMod:BattleStaminaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BattleStaminaPower(AbstractCreature owner, int amount) {
        ID = POWER_ID;
        this.owner = owner;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/BattleStamina32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/BattleStamina84.png"), 0, 0, 84, 84);

        this.amount = amount;
        type = POWER_TYPE;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        AbstractPlayer p = AbstractDungeon.player;
        int stacks = getEnduranceInExhaust()*amount;
        if (stacks > 0)
            addToBot(new ApplyPowerAction(p, p, new VitalityPower(p, stacks), stacks));
    }

    private int getEnduranceInExhaust() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(FireBladeCardTags.ENDURANCE))
                count++;
        }
        return count;
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
