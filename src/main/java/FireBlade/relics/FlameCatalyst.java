package FireBlade.relics;

import FireBlade.cards.FireBladeCardTags;
import FireBlade.powers.FervorPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FlameCatalyst extends CustomRelic {
    public static final String ID = "FireBladeMod:FlameCatalyst";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/FlameCatalyst.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/FlameCatalyst_outline.png";
    private static final RelicTier TIER = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int fervorAmount = 4;

    public FlameCatalyst() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new FervorPower(p, fervorAmount), fervorAmount));
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (!targetCard.hasTag(FireBladeCardTags.FLAME) || targetCard.type == AbstractCard.CardType.POWER)
            return;

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        useCardAction.exhaustCard = true;
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + fervorAmount + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new FlameCatalyst(); }
}
