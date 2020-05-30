package FireBlade.relics;

import FireBlade.powers.AntiMagicPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BattleMasterLocket extends CustomRelic {
    public static final String ID = "FireBladeMod:BattleMasterLocket";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/BattleMasterLocket.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Locket_outline.png";
    private static final RelicTier TIER = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int AMOUNT = 4;
    private static final int APPLY_TIMES = 2;

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
    public void atBattleStartPreDraw() {
        super.atBattleStartPreDraw();
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                addToBot(new RelicAboveCreatureAction(m, this));
                for (int i = 0; i < APPLY_TIMES; i++)
                    addToBot(new ApplyPowerAction(m, p, new AntiMagicPower(m, p, AMOUNT), AMOUNT));
            }
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + AMOUNT + DESCRIPTIONS[1] + APPLY_TIMES + DESCRIPTIONS[2];
    }

    public AbstractRelic makeCopy() { return new BattleMasterLocket(); }

    public boolean canSpawn() { return AbstractDungeon.player.hasRelic(DuelistLocket.ID); }
}
