package FireBlade.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

import java.util.ArrayList;

public class AntiMagicPower extends AbstractPower {
    public static final PowerType POWER_TYPE = PowerType.DEBUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FireBladeMod:AntiMagicPower");
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final String POWER_ID = "FireBladeMod:AntiMagicPower";

    // In the code powers are either buff or debuff, but I don't want this triggering on "buffs" that are merely informative displays
    private static final ArrayList<String> notMagicList = new ArrayList<String>() {
        {
            add(ModeShiftPower.POWER_ID);
            add(StasisPower.POWER_ID);
        }
    };

    private AbstractCreature source;

    // I don't want this triggering on recovering from shackle, since the source should be considered the player, not the monster, despite the code
    // I don't want to patch the shackle power because I could cause cross mod problems.
    boolean shackleIgnore = true;
    boolean isEndOfTurn = true;

    public AntiMagicPower(AbstractCreature owner, AbstractCreature source, int amount) {
        ID = POWER_ID;
        this.owner = owner;
        this.source = source;

        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/AntiMagic32.png"), 0 ,0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("theFireBladeResources/images/powers/AntiMagic84.png"), 0, 0, 84, 84);

        type = POWER_TYPE;
        this.amount = amount;
        name = (CardCrawlGame.languagePack.getPowerStrings(ID)).NAME;

        updateDescription();
    }

    public void atStartOfTurn() {
        super.atStartOfTurn();
        isEndOfTurn = false;
    }

    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        shackleIgnore = true;
        isEndOfTurn = true;
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature funcSource) {
        if (funcSource == owner && !power.ID.equals(GainStrengthPower.POWER_ID) &&
                (power.type == PowerType.BUFF || target != owner) && !notMagicList.contains(power.ID))
        {
            if (shackleIgnore && isEndOfTurn && power.ID == StrengthPower.POWER_ID && target.hasPower(GainStrengthPower.POWER_ID) && target == owner) {
                shackleIgnore = false;
            }
            else {
                flash();
                addToTop(new DamageAction(owner, new DamageInfo(source, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
