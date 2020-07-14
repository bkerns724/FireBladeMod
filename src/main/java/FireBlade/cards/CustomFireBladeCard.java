package FireBlade.cards;

import basemod.abstracts.CustomCard;

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

        if (upgradedMagicNumberTwo) {
            magicNumberTwo = baseMagicNumberTwo;
            isMagicNumberTwoModified = true;
        }
    }

    public void upgradeMagicNumberTwo(int amount) {
        baseMagicNumberTwo += amount;
        magicNumberTwo = baseMagicNumberTwo;
        upgradedMagicNumberTwo = true;
    }

    public void increaseMagicNumber(int increase) {upgradeMagicNumber(increase);}
}