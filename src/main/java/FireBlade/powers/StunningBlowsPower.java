package FireBlade.powers;

import FireBlade.cards.TheFireBladeCardTags;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class StunningBlowsPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:StunningBlowsPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public StunningBlowsPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:StunningBlowsPower";
        this.owner = owner;
        this.amount = amount;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/StunningBlows32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/StunningBlows84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        if (card.hasTag(TheFireBladeCardTags.SMASH)){
            if (card.target == AbstractCard.CardTarget.ALL_ENEMY)
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                    addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, amount, false), amount));
            else if (action.target != null)
                addToBot(new ApplyPowerAction(action.target, p, new VulnerablePower(action.target, amount, false), amount));
        }
    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
}
