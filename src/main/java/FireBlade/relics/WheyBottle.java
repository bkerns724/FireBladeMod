package FireBlade.relics;

import FireBlade.cards.TheFireBladeCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class WheyBottle extends CustomRelic {
    public static final String ID = "FireBladeMod:WheyBottle";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/WheyBottle.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/WheyBottle_outline.png";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public WheyBottle() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onUseCard (AbstractCard card, UseCardAction useCardAction) {
        if (!card.hasTag(TheFireBladeCardTags.ENDURANCE))
            return;

        flash();

        AbstractPlayer p = AbstractDungeon.player;
        int strengthGain = 4;

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthGain), strengthGain));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, strengthGain), strengthGain));
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0]; }

    public AbstractRelic makeCopy() { return new WheyBottle(); }
}
