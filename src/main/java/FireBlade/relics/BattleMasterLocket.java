package FireBlade.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BattleMasterLocket extends CustomRelic {
    public static final String ID = "FireBladeMod:BattleMasterLocket";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/BattleMasterLocket.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Locket_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int BLOCK_AMOUNT = 4;

    public BattleMasterLocket() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void obtain() {
        // Below statement should always be true if this relic is being obtained.
        if (AbstractDungeon.player.hasRelic(DuelistLocket.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if ((AbstractDungeon.player.relics.get(i)).relicId.equals(DuelistLocket.ID)) {
                    instantObtain(AbstractDungeon.player, i,true);
                    break;
                }
            }
        }
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

    public AbstractRelic makeCopy() { return new BattleMasterLocket(); }

    public boolean canSpawn() { return AbstractDungeon.player.hasRelic(DuelistLocket.ID); }
}
