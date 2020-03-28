package FireBlade.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import FireBlade.cards.Uncommons.QuickAttack;

public class JabsPower extends AbstractPower {
    public static PowerType POWER_TYPE = PowerType.BUFF;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:JabsPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public JabsPower(AbstractCreature owner, int amount) {
        this.ID = "FireBladeMod:JabsPower";
        this.owner = owner;

        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Strikes32.png"), 0 ,0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/Strikes84.png"), 0, 0, 84, 84);

        this.type = POWER_TYPE;
        this.amount = amount;
        this.name = (CardCrawlGame.languagePack.getPowerStrings(this.ID)).NAME;

        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        for (int i = 0; i < this.amount; i++) {
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
            AbstractCard card = new QuickAttack();  // Need an attack card with proper damage amount
            if (target != null) {
                card.calculateCardDamage((AbstractMonster) target);
                this.addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
}
