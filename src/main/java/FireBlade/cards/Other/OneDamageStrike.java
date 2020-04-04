package FireBlade.cards.Other;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class OneDamageStrike extends CustomCard
{
    public static final String ID = "FireBladeMod:OneDamageStrike";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/StrikeFireBlade.png";
    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 0;

    public OneDamageStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
        this.baseDamage = 1;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower("Pen Nib"))
            damage /= 2;

        if (damage == baseDamage)
            isDamageModified = false;
    }

    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);

        if (AbstractDungeon.player.hasPower("Pen Nib"))
            damage /= 2;

        if (damage == baseDamage)
            isDamageModified = false;
    }

    public AbstractCard makeCopy() { return new OneDamageStrike(); }

    public void upgrade() {
        if (!this.upgraded) {
        upgradeName();
        }
    }
  
    static  {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
}