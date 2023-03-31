package entity;

public class Storage implements AbstractEntity {
    private String itemId;
    private int amount;

    public Storage() {}

    public Storage(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return 31 + itemId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Storage) {
            Storage o = (Storage) obj;
            return this.itemId.equals(o.itemId);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Storage [itemId=" + itemId + ", amount=" + amount + "]";
    }
}
