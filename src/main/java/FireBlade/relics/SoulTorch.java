package FireBlade.relics;

import FireBlade.powers.BurningPower;
import FireBlade.powers.SpiritRendPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SoulTorch extends CustomRelic {
    public static final String ID = "FireBladeMod:SoulTorch";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/SoulTorch.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Torch_outline.png";
    private static final AbstractRelic.RelicTier TIER = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final int burnAmount = 5;
    private static final int spiritRendAmount = 1;

    public SoulTorch() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void obtain() {
        // Below statement should always be true if this relic is being obtained.
        if (AbstractDungeon.player.hasRelic(EternalTorch.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if ((AbstractDungeon.player.relics.get(i)).relicId.equals(EternalTorch.ID)) {
                    instantObtain(AbstractDungeon.player, i,true);
                    break;
                }
            }
        }
    }

    public void atBattleStart() {
        flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            AbstractPlayer p = AbstractDungeon.player;
            addToTop(new ApplyPowerAction(m, p, new BurningPower(m, p, burnAmount), burnAmount));
            addToTop(new ApplyPowerAction(m, p, new SpiritRendPower(m, spiritRendAmount), spiritRendAmount));
        }
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + burnAmount + DESCRIPTIONS[1] + spiritRendAmount + DESCRIPTIONS[2]; }

    public AbstractRelic makeCopy() { return new SoulTorch(); }

    public boolean canSpawn() { return AbstractDungeon.player.hasRelic(EternalTorch.ID); }
}
