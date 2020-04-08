package FireBlade.cards.Uncommons;

import FireBlade.enums.TheFireBladeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class GasolineStrike extends CustomCard {

    public static final String ID = "FireBladeMod:GasolineStrike";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/GasolineStrike.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    private int realBaseDamage;

    public GasolineStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        realBaseDamage = baseDamage = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int burnAmount = 0;
        addToBot(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL), 0.1F));
        if (m.hasPower("FireBladeMod:BurningPower"))
            burnAmount = (m.getPower("FireBladeMod:BurningPower")).amount;
        if (burnAmount >= 5 && burnAmount < 50)
            addToBot(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.FIRE), 0.1F));
        if (burnAmount >= 50)
            addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = realBaseDamage;
        if (mo.hasPower("FireBladeMod:BurningPower"))
            baseDamage += (mo.getPower("FireBladeMod:BurningPower")).amount;
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        if (mo.hasPower("FireBladeMod:BurningPower"))
            isDamageModified = true;
    }

    public AbstractCard makeCopy() { return new GasolineStrike(); }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
            realBaseDamage += 3;
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}