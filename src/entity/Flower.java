package entity;

public class Flower extends Item {
    private int revenue;
    private String stageImg;
    private String stageTime;
    private String stageNeed;

    public Flower() {}

    public Flower(String itemId, String itemName, String itemImg, int itemPrice, int levelUnlock, String itemDesc,
                  int revenue, String stageImg, String stageTime, String stageNeed) {
        super(itemId, itemName, itemImg, itemPrice, levelUnlock, itemDesc);
        this.revenue = revenue;
        this.stageImg = stageImg;
        this.stageTime = stageTime;
        this.stageNeed = stageNeed;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getStageImg() {
        return stageImg;
    }

    public void setStageImg(String stageImg) {
        this.stageImg = stageImg;
    }

    public String getStageTime() {
        return stageTime;
    }

    public void setStageTime(String stageTime) {
        this.stageTime = stageTime;
    }

    public String getStageNeed() {
        return stageNeed;
    }

    public void setStageNeed(String stageNeed) {
        this.stageNeed = stageNeed;
    }

    @Override
    public String toString() {
        return "Flower [itemId=" + itemId + ", itemName=" + itemName + ", itemImg=" + itemImg + ", itemPrice="
                + itemPrice + ", levelUnlock=" + levelUnlock + ", itemDesc=" + itemDesc + ", revenue=" + revenue
                + ", stageImg=" + stageImg + ", stageTime=" + stageTime + ", stageNeed=" + stageNeed + "]";
    }

    public String getStageImg(int stage) {
        String[] stageImgs = stageImg.split("-");
        return stageImgs[stage - 1];
    }

    public int getStageTime(int stage) {
        String[] stageTimes = stageTime.split("-");
        return Integer.parseInt(stageTimes[stage - 1]);
    }

    public String getStageNeed(int stage) {
        String[] stageNeeds = stageNeed.split("-");
        return stageNeeds[stage - 1];
    }

    public int getTime() {
        int timeSum = 0;
        String[] stageTimes = stageTime.split("-");
        for (String time : stageTimes) {
            timeSum += Integer.parseInt(time);
        }
        return timeSum;
    }
}
