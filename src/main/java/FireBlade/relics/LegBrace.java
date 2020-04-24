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
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class LegBrace extends CustomRelic {
    public static final String ID = "FireBladeMod:LegBrace";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/LegBrace.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/LegBrace_outline.png";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int armorGain = 2;

    public LegBrace() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onUseCard (AbstractCard card, UseCardAction useCardAction) {
        if (!card.hasTag(FireBladeCardTags.ENDURANCE))
            return;

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractPlayer p = AbstractDungeon.player;

        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, armorGain), armorGain));
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + armorGain + this.DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new LegBrace(); }
}
