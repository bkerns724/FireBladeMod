package FireBlade.cards;

import basemod.abstracts.CustomCard;

public abstract class CustomFireBladeCard extends CustomCard {
    public int magicNumberTwo = 0;
    public int baseMagicNumberTwo = 0;
    public boolean upgradedMagicNumberTwo = false;
    public boolean isMagicNumberTwoModified = false;

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

 }