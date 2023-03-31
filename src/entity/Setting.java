package entity;

public class Setting implements AbstractEntity {
    private int plotNumMax;
    private int cellNumMax;

    public Setting() {}

    public Setting(int plotNumMax, int cellNumMax) {
        this.plotNumMax = plotNumMax;
        this.cellNumMax = cellNumMax;
    }

    public int getPlotNumMax() {
        return plotNumMax;
    }

    public void setPlotNumMax(int plotNumMax) {
        this.plotNumMax = plotNumMax;
    }

    public int getCellNumMax() {
        return cellNumMax;
    }

    public void setCellNumMax(int cellNumMax) {
        this.cellNumMax = cellNumMax;
    }
}
