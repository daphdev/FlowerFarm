package dao;

import entity.Cell;
import utils.Getter;
import utils.Setter;

import java.util.List;

public class CellDAO extends AbstractDAO {
    private final int plotId;

    public CellDAO(String username, int plotId) {
        this.plotId = plotId;
        setPath("user_data/" + username + "/cellPlot" + plotId + ".csv");
        setHeader(new String[] {"cellId", "stage", "flowerId", "stageNeedCurrent", "timeRemaining", "selected",
                "finalRevenue", "occurPest"});
        setSupp(Cell::new);
        setList(read());
    }

    public int getPlotId() {
        return plotId;
    }

    @SuppressWarnings("unchecked")
    public List<Cell> getCastedList() {
        return (List<Cell>) (List<?>) list;
    }

    public Cell getCell(int cellId) {
        return (Cell) list.get(cellId - 1);
    }

    public Object getProperty(int cellId, String propertyName) {
        return Getter.getProperty(getCell(cellId), propertyName);
    }

    public void setProperty(int cellId, String propertyName, Object propertyValue) {
        Setter.setProperty(getCell(cellId), propertyName, propertyValue);
        write(list);
    }
}
