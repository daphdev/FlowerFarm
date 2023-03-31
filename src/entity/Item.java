package entity;

public abstract class Item implements AbstractEntity {
    protected String itemId;
    protected String itemName;
    protected String itemImg;
    protected int itemPrice;
    protected int levelUnlock;
    protected String itemDesc;

    public Item() {}

    public Item(String itemId, String itemName, String itemImg, int itemPrice, int levelUnlock, String itemDesc) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImg = itemImg;
        this.itemPrice = itemPrice;
        this.levelUnlock = levelUnlock;
        this.itemDesc = itemDesc;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getLevelUnlock() {
        return levelUnlock;
    }

    public void setLevelUnlock(int levelUnlock) {
        this.levelUnlock = levelUnlock;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @Override
    public int hashCode() {
        return 31 + itemId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item o = (Item) obj;
            return this.itemId.equals(o.itemId);
        }
        return false;
    }
}
