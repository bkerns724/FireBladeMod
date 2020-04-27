package FireBlade.cards.Uncommons;

import FireBlade.enums.TheFireBladeEnum;
import FireBlade.cards.CustomFireBladeCard;
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

public class EruptionSlash extends CustomFireBladeCard {

    public static final String ID = "FireBladeMod:EruptionSlash";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/EruptionSlash.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    private int realBaseDamage;

    public EruptionSlash() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, TheFireBladeEnum.THE_FIREBLADE_ORANGE, RARITY, TARGET);
        realBaseDamage = baseDamage = 3;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int bonusAmount = 0;
        addToBot(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.SLASH_VERTICAL), 0.1F));
        if (m.hasPower("FireBladeMod:BurningPower"))
            bonusAmount = magicNumber*(m.getPower("FireBladeMod:BurningPower")).amount;
        if (bonusAmount >= 5 && bonusAmount < 50)
            addToBot(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.FIRE), 0.1F));
        if (bonusAmount >= 50)
            addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = realBaseDamage;
        if (mo.hasPower("FireBladeMod:BurningPower")) {
            baseDamage += magicNumber*(mo.getPower("FireBladeMod:BurningPower")).amount;
        }
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        if (damage != baseDamage) {
            isDamageModified = true;
        }
    }

    public AbstractCard makeCopy() { return new EruptionSlash(); }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}