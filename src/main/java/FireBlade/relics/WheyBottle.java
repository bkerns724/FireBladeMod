package FireBlade.relics;

import FireBlade.cards.FireBladeCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WheyBottle extends CustomRelic {
    public static final String ID = "FireBladeMod:WheyBottle";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/WheyBottle.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/WheyBottle_outline.png";
    private static final RelicTier TIER = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.HEAVY;
    private static final int VIGOR_AMOUNT = 4;

    public WheyBottle() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onUseCard (AbstractCard card, UseCardAction useCardAction) {
        if (!card.hasTag(FireBladeCardTags.ENDURANCE))
            return;

        flash();

        AbstractPlayer p = AbstractDungeon.player;

        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, VIGOR_AMOUNT), VIGOR_AMOUNT));
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + VIGOR_AMOUNT + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new WheyBottle(); }
}
