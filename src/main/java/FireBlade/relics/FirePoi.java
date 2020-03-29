package FireBlade.relics;

import FireBlade.cards.TheFireBladeCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FirePoi extends CustomRelic {
    public static final String ID = "FireBladeMod:FirePoi";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/FirePoi.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/FirePoi_outline.png";
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private static final int fireThorns = 4;

    public FirePoi() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onPlayCard (AbstractCard card, AbstractMonster m) {
        if (!card.hasTag(TheFireBladeCardTags.BURNER))
            return;

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new FlameBarrierPower(p, fireThorns), fireThorns));
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + fireThorns + this.DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new FirePoi(); }
}
