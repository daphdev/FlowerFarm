package entity;

public class Cell implements AbstractEntity {
    private int cellId;
    private int stage;
    private String flowerId;
    private String stageNeedCurrent;
    private int timeRemaining;
    private int selected;
    private int finalRevenue;
    private int occurPest;

    public Cell() {}

    public Cell(int cellId) {
        this.cellId = cellId;
        this.stage = 0; // Stage: 0, 1, 2, 3, 4, 5
        this.flowerId = "";
        this.stageNeedCurrent = "";
        this.timeRemaining = -1; // -1: Stage khong co Timer
        this.selected = 0;
        this.finalRevenue = 0;
        this.occurPest = 1; // 1: Theo mac dinh, 0: Xoa "P"
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public String getStageNeedCurrent() {
        return stageNeedCurrent;
    }

    public void setStageNeedCurrent(String stageNeedCurrent) {
        this.stageNeedCurrent = stageNeedCurrent;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getFinalRevenue() {
        return finalRevenue;
    }

    public void setFinalRevenue(int finalRevenue) {
        this.finalRevenue = finalRevenue;
    }

    public int getOccurPest() {
        return occurPest;
    }

    public void setOccurPest(int occurPest) {
        this.occurPest = occurPest;
    }

    @Override
    public int hashCode() {
        return 31 + Integer.valueOf(cellId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell o = (Cell) obj;
            return this.cellId == o.cellId;
        }
        return false;
    }
}
