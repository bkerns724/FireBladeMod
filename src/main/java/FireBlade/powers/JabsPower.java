package FireBlade.powers;

import FireBlade.cards.Other.OneDamageStrike;
import basemod.interfaces.OnPowersModifiedSubscriber;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JabsPower extends AbstractPower implements OnPowersModifiedSubscriber {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:JabsPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public final OneDamageStrike dummyCard;

    public JabsPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:JabsPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Strikes32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Strikes84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        this.dummyCard = new OneDamageStrike();

        updateDescription();
    }

    public void receivePowersModified() {
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        for (int i = 0; i < this.amount; i++) {
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster( null, true, AbstractDungeon.cardRandomRng);
            if (target != null) {
                dummyCard.calculateCardDamage(target);
                this.addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, dummyCard.damage, dummyCard.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }

    public void updateDescription() {
        dummyCard.applyPowers();
        if (!dummyCard.isDamageModified)
            this.description = this.DESCRIPTIONS[0] + "#b" + dummyCard.damage + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        else if (dummyCard.damage > dummyCard.baseDamage)
            this.description = this.DESCRIPTIONS[0] + "#g" + dummyCard.damage + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        else
            this.description = this.DESCRIPTIONS[0] + "#r" + dummyCard.damage + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
