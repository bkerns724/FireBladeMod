package FireBlade.relics;

import FireBlade.cards.FireBladeCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GymTowel extends CustomRelic {
    public static final String ID = "FireBladeMod:GymTowel";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/GymTowel.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/GymTowel_outline.png";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int STRENGTH_GAIN = 1;

    public GymTowel() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onUseCard (AbstractCard card, UseCardAction useCardAction) {
        if (!card.hasTag(FireBladeCardTags.SMASH))
            return;

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractPlayer p = AbstractDungeon.player;

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH_GAIN), STRENGTH_GAIN));
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + STRENGTH_GAIN + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new GymTowel(); }
}
