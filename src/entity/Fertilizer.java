package entity;

public class Fertilizer extends Item {
    private int percent;

    public Fertilizer() {}

    public Fertilizer(String itemId, String itemName, String itemImg, int itemPrice, int levelUnlock, String itemDesc,
                      int percent) {
        super(itemId, itemName, itemImg, itemPrice, levelUnlock, itemDesc);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Fertilizer [itemId=" + itemId + ", itemName=" + itemName + ", itemImg=" + itemImg + ", itemPrice="
                + itemPrice + ", levelUnlock=" + levelUnlock + ", itemDesc=" + itemDesc + ", percent=" + percent + "]";
    }
}
