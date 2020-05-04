package FireBlade.cards;

import FireBlade.powers.BattleMagePower;
import FireBlade.powers.FervorPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class CustomFireBladeCard extends CustomCard {
    public int magicNumberTwo = 0;
    public int baseMagicNumberTwo = 0;
    public boolean upgradedMagicNumberTwo = false;
    public boolean isMagicNumberTwoModified = false;
    public boolean isUnnate = false;

    public CustomFireBladeCard(String id, String name, String img, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, description, type, color, rarity, target);
    }

    public void displayUpgrades() {
        super.displayUpgrades();

        if (this.upgradedMagicNumberTwo) {
            this.magicNumberTwo = this.baseMagicNumberTwo;
            this.isMagicNumberTwoModified = true;
        }
    }

    public void upgradeMagicNumberTwo(int amount) {
        this.baseMagicNumberTwo += amount;
        this.magicNumberTwo = this.baseMagicNumberTwo;
        this.upgradedMagicNumberTwo = true;
    }

    @Override
    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        int temp = baseDamage;
        if (p.hasPower(BattleMagePower.POWER_ID) && p.hasPower(FervorPower.POWER_ID))
            baseDamage += p.getPower(FervorPower.POWER_ID).amount;
        super.applyPowers();
        baseDamage = temp;
        if (baseDamage != damage)
            isDamageModified = true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = AbstractDungeon.player;
        int temp = baseDamage;
        if (p.hasPower(BattleMagePower.POWER_ID) && p.hasPower(FervorPower.POWER_ID))
            baseDamage += p.getPower(FervorPower.POWER_ID).amount;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        if (baseDamage != damage)
            isDamageModified = true;
    }
}