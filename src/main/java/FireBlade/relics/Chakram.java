package FireBlade.relics;

import FireBlade.powers.FervorPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Chakram extends CustomRelic {
    public static final String ID = "FireBladeMod:Chakram";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/Chakram.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Chakram_outline.png";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public static final int numAttacks = 3;
    public static final int fervorAmount = 1;

    public Chakram() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++counter;
            if (counter % 3 == 0) {
                counter = 0;
                flash();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FervorPower(AbstractDungeon.player, fervorAmount), fervorAmount));
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + numAttacks + DESCRIPTIONS[1] + fervorAmount + DESCRIPTIONS[2];
    }

    public AbstractRelic makeCopy() { return new Chakram(); }
}
