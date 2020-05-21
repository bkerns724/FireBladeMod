package FireBlade.cards.Other;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComboHitsHelper extends CustomCard {
    public static final String ID = "FireBladeMod:ComboHitsHelper";
    public static final String IMG_PATH = "theFireBladeResources/images/cardImages/StrikeFireBlade.png";  // Should not be seen
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static int COST = 0;

    public ComboHitsHelper() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
        baseDamage = 3;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void upgrade() {
        upgradeName();
        isMultiDamage = true;
    }
}
