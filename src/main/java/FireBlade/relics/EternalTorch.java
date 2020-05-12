package FireBlade.relics;

import FireBlade.powers.BurningPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EternalTorch extends CustomRelic {
    public static final String ID = "FireBladeMod:EternalTorch";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/EternalTorch.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Torch_outline.png";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int burnAmount = 3;

    public EternalTorch() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void atBattleStart() {
        flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            AbstractPlayer p = AbstractDungeon.player;
            addToTop(new ApplyPowerAction(m, p, new BurningPower(m, p, burnAmount), burnAmount));
        }
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + burnAmount + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new EternalTorch(); }
}
