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

public class DuelistLocket extends CustomRelic {
    public static final String ID = "FireBladeMod:DuelistLocket";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/DuelistLocket.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Locket_outline.png";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int AMOUNT = 4;

    public DuelistLocket() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        super.atBattleStartPreDraw();
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                addToBot(new RelicAboveCreatureAction(m, this));
                addToBot(new ApplyPowerAction(m, p, new AntiMagicPower(m, p, AMOUNT), AMOUNT));
            }
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + AMOUNT + DESCRIPTIONS[1];
    }

    public AbstractRelic makeCopy() { return new DuelistLocket(); }
}
