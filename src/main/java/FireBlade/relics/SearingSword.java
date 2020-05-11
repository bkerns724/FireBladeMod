package FireBlade.relics;

import FireBlade.powers.BurningPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SearingSword extends CustomRelic {
    public static final String ID = "FireBladeMod:SearingSword";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/SearingSword.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/SearingSword_outline.png";
    private static final RelicTier TIER = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int strengthAmount = 1;
    private static final int burningAmount = 1;

    public SearingSword() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -strengthAmount), -strengthAmount));
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (info.owner == p && info.type == DamageInfo.DamageType.NORMAL)
            addToBot(new ApplyPowerAction(target, p, new BurningPower(target, p, burningAmount), burningAmount));
    }

    public String getUpdatedDescription() { return DESCRIPTIONS[0] + burningAmount + DESCRIPTIONS[1] + strengthAmount + DESCRIPTIONS[2]; }

    public AbstractRelic makeCopy() { return new SearingSword(); }
}
