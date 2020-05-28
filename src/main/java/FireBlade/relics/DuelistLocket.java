package FireBlade.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DuelistLocket extends CustomRelic {
    public static final String ID = "FireBladeMod:DuelistLocket";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/DuelistLocket.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Locket_outline.png";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int BLOCK_AMOUNT = 1;

    public DuelistLocket() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if (m != null && !m.isDeadOrEscaped())
            addToTop(new GainBlockAction(AbstractDungeon.player, BLOCK_AMOUNT, true));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMOUNT + DESCRIPTIONS[1];
    }

    public AbstractRelic makeCopy() { return new DuelistLocket(); }
}
