package entity;

public class Pesticide extends Item {
    private int occurAgain;

    public Pesticide() {}

    public Pesticide(String itemId, String itemName, String itemImg, int itemPrice, int levelUnlock, String itemDesc,
                     int occurAgain) {
        super(itemId, itemName, itemImg, itemPrice, levelUnlock, itemDesc);
        this.occurAgain = occurAgain;
    }

    public int getOccurAgain() {
        return occurAgain;
    }

    public void setOccurAgain(int occurAgain) {
        this.occurAgain = occurAgain;
    }

    @Override
    public String toString() {
        return "Pesticide [itemId=" + itemId + ", itemName=" + itemName + ", itemImg=" + itemImg + ", itemPrice="
                + itemPrice + ", levelUnlock=" + levelUnlock + ", itemDesc=" + itemDesc + ", occurAgain=" + occurAgain
                + "]";
    }

    public String getEffective() {
        if (occurAgain == 1) {
            return "Low";
        }
        return "High";
    }
}
