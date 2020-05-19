package FireBlade.relics;

import FireBlade.powers.BurningPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class EternalTorch extends CustomRelic {
    public static final String ID = "FireBladeMod:EternalTorch";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/EternalTorch.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/Torch_outline.png";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int burnAmount = 4;

    public EternalTorch() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    @Override
    public void onVictory() {
        super.onVictory();
        counter = -1;
    }

    public void atBattleStart() {
        counter = 1;
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if (counter == 1) {
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(target, p, new BurningPower(target, p, burnAmount), burnAmount));
            counter = 0;
            grayscale = true;
        }
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + burnAmount + DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new EternalTorch(); }
}
