package FireBlade.relics;

import FireBlade.cards.FireBladeCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BronzeKnuckles extends CustomRelic {
    public static final String ID = "FireBladeMod:BronzeKnuckles";
    public static final String IMG_PATH = "theFireBladeResources/images/relics/BronzeKnuckles.png";
    public static final String OUTLINE_IMG_PATH = "theFireBladeResources/images/relics/BronzeKnuckles_outline.png";
    private static final RelicTier TIER = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int strengthLoss = 2;

    public BronzeKnuckles() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
    }

    public void onUseCard (AbstractCard card, UseCardAction useCardAction) {
        if (!card.hasTag(FireBladeCardTags.SMASH))
            return;

        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractCreature m = useCardAction.target;
        AbstractPlayer p = AbstractDungeon.player;

        if (!card.target.equals(AbstractCard.CardTarget.ALL_ENEMY)) {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -strengthLoss), -strengthLoss, true, AbstractGameAction.AttackEffect.NONE));
            if (!m.hasPower("Artifact")) {
                addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, strengthLoss), strengthLoss, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        else
        {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -strengthLoss), -strengthLoss, true, AbstractGameAction.AttackEffect.NONE));
                if (!mo.hasPower("Artifact")) {
                    addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, strengthLoss), strengthLoss, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
    }

    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] + strengthLoss + this.DESCRIPTIONS[1]; }

    public AbstractRelic makeCopy() { return new BronzeKnuckles(); }
}
