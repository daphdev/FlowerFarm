package controller;

import dao.AbstractDAO;
import dao.CellDAO;
import dao.FlowerDAO;
import dao.StorageDAO;
import dao.UserDAO;
import entity.Cell;
import entity.Fertilizer;
import entity.Flower;
import entity.Pesticide;
import factory.DAOFactory;
import factory.ViewFactory;
import view.AbstractView;
import view.CellView;
import view.Entry;
import view.PlayView;
import view.PlotView;
import view.StorageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CellController extends AbstractController {
    private final int cellId;
    private final CellView cellView;
    private final PlotView plotView;
    private final PlayView playView;
    private final StorageView storageView;
    private final CellDAO cellDAO;
    private final UserDAO userDAO;
    private final StorageDAO storageDAO;
    private final Cell cell;
    private Timer timer;

    public CellController(List<AbstractView> listView, List<AbstractDAO> listDAO, int cellId) {
        super(listView, listDAO);
        this.cellId = cellId;
        this.cellView = (CellView) ViewFactory.get("Cell", listView, cellId);
        this.plotView = (PlotView) ViewFactory.get("Plot", listView);
        this.playView = (PlayView) ViewFactory.get("Play", listView);
        this.storageView = (StorageView) ViewFactory.get("Storage", listView);

        this.cellDAO = (CellDAO) DAOFactory.get("Cell", listDAO);
        this.userDAO = (UserDAO) DAOFactory.get("User", listDAO);
        this.storageDAO = (StorageDAO) DAOFactory.get("Storage", listDAO);
        FlowerDAO flowerDAO = (FlowerDAO) DAOFactory.get("FL", listDAO);

        this.cell = cellDAO.getCell(cellId);
        this.cellView.setCell(cell);
        this.cellView.setFlowerDAO(flowerDAO);

        handleEvents();
    }

    private void handleEvents() {
        updateCellView();
        handleTimerEvent();
    }

    private void handleTimerEvent() {
        if ((cell.getStage() == 1 || cell.getStage() == 2 || cell.getStage() == 3) && cell.getTimeRemaining() > 0) {
            timer = new Timer(1000, new ActionListener() {
                int count = cell.getTimeRemaining();

                @Override
                public void actionPerformed(ActionEvent e) {
                    count--;
                    if (count == 0
                            && (cell.getStageNeedCurrent().contains("W") || cell.getStageNeedCurrent().contains("P"))) {
                        cellDAO.setProperty(cellId, "timeRemaining", count);
                        cellView.updateTimeBarValue();
                        cellView.updateTimeLabel();
                        timer.stop();
                        cellDAO.setProperty(cellId, "stage", 5); // Nhay len Stage 5
                        updateCellView();
                    } else if (count == 0 && (!cell.getStageNeedCurrent().contains("W")
                            && !cell.getStageNeedCurrent().contains("P"))) {
                        cellDAO.setProperty(cellId, "timeRemaining", count);
                        cellView.updateTimeBarValue();
                        cellView.updateTimeLabel();
                        timer.stop();
                        cellDAO.setProperty(cellId, "stage", cell.getStage() + 1); // Tang len Stage tiep theo
                        if (cell.getStage() == 1 || cell.getStage() == 2 || cell.getStage() == 3) {
                            cellDAO.setProperty(cellId, "stageNeedCurrent",
                                    cellView.getFlower().getStageNeed(cell.getStage()));
                            cellDAO.setProperty(cellId, "timeRemaining",
                                    cellView.getFlower().getStageTime(cell.getStage()));
                            handleTimerEvent();
                        }
                        updateCellView();
                    } else {
                        cellDAO.setProperty(cellId, "timeRemaining", count);
                        cellView.updateTimeBarValue();
                        cellView.updateTimeLabel();
                    }
                }
            });
            timer.start();
        }
    }

    private void updateCellView() {
        checkOccurPest();
        cellView.updateFlowerNameLabel();
        cellView.updateStageLabel();
        cellView.updateTimeBar();
        cellView.updateTimeLabel();
        cellView.updateCellImgLabel();
        cellView.updateButtonCard();
        cellView.updateWarningCard();
        cellView.handleMouseEvent();
        handleItemBtnEvent("FL");
        handleItemBtnEvent("FE");
        handleItemBtnEvent("PE");
        handleExitBtnEvent();
        handleWtBtnEvent();
        handleHvBtnEvent();
        handleUrBtnEvent();
    }

    // Handle Events for ctBtn (FL), feBtn (FE), peBtn (PE)
    private void handleItemBtnEvent(String itemType) {
        if (cellView.getItemBtn(itemType) != null) {
            cellView.getItemBtn(itemType).addActionListener(e -> {
                playView.showStorageView();
                storageView.showCard(itemType);
                storageView.showPick(itemType);
                cellDAO.setProperty(cellId, "selected", 1);
                cellView.focusCellView();
                plotView.setDisabledCellView();
                plotView.getLandBtn().setEnabled(false);
                playView.setDisabledTaskBtn();
                playView.setEnabledHideBtn(false);
                playView.getLogoutBtn().setEnabled(false);
                playView.getSettingBtn().setEnabled(false);
                handlePickBtnEvent(itemType);
            });
        }
    }

    private void handlePickBtnEvent(String itemType) {
        for (Entry entry : storageView.getEntries(itemType)) {
            entry.getActionBtn().setEnabled(true);
            entry.getActionBtn().addActionListener(e -> {
                if (itemType.equals("FL")) {
                    Flower flower = (Flower) entry.getItem();
                    cellDAO.setProperty(cellId, "stage", 1); // Tang len Stage 1
                    cellDAO.setProperty(cellId, "flowerId", flower.getItemId());
                    cellDAO.setProperty(cellId, "stageNeedCurrent", flower.getStageNeed(cell.getStage()));
                    cellDAO.setProperty(cellId, "timeRemaining", flower.getStageTime(cell.getStage()));
                    cellDAO.setProperty(cellId, "finalRevenue", flower.getRevenue());
                    handleTimerEvent();
                }
                if (itemType.equals("FE")) {
                    Fertilizer fertilizer = (Fertilizer) entry.getItem();
                    cellDAO.setProperty(cellId, "stageNeedCurrent", cell.getStageNeedCurrent().replace("F", ""));
                    cellDAO.setProperty(cellId, "finalRevenue",
                            (int) (cell.getFinalRevenue() * (1 + (float) fertilizer.getPercent() / 100)));
                }
                if (itemType.equals("PE")) {
                    Pesticide pesticide = (Pesticide) entry.getItem();
                    cellDAO.setProperty(cellId, "stageNeedCurrent", cell.getStageNeedCurrent().replace("P", ""));
                    cellDAO.setProperty(cellId, "occurPest", pesticide.getOccurAgain());
                }
                cellDAO.setProperty(cellId, "selected", 0);
                cellView.exitFocusCellView();
                updateCellView();
                storageDAO.pickItem(entry.getItem().getItemId());
                storageView.showPick("tabBtnPanel");
                storageView.getEntries(itemType).forEach(en -> en.getActionBtn().setEnabled(false));
                plotView.setEnabledCellView();
                plotView.getLandBtn().setEnabled(true);
                playView.setEnabledTaskBtn();
                playView.setEnabledHideBtn(true);
                playView.getLogoutBtn().setEnabled(true);
                playView.getSettingBtn().setEnabled(true);
                // Set message playView
            });
        }
    }

    private void checkOccurPest() {
        if (cell.getOccurPest() == 0) {
            cellDAO.setProperty(cellId, "stageNeedCurrent", cell.getStageNeedCurrent().replace("P", ""));
        }
    }

    private void handleExitBtnEvent() {
        cellView.getExitBtn().addActionListener(e -> {
            cellDAO.setProperty(cellId, "selected", 0);
            cellView.exitFocusCellView();
            storageView.showPick("tabBtnPanel");
            storageView.getEntries().forEach(en -> en.getActionBtn().setEnabled(false));
            storageView.getEntries().forEach(en -> {
                for (ActionListener l : en.getActionBtn().getActionListeners()) {
                    en.getActionBtn().removeActionListener(l);
                }
            });
            plotView.setEnabledCellView();
            plotView.getLandBtn().setEnabled(true);
            playView.setEnabledTaskBtn();
            playView.setEnabledHideBtn(true);
            playView.getLogoutBtn().setEnabled(true);
            playView.getSettingBtn().setEnabled(true);
        });
    }

    private void handleWtBtnEvent() {
        if (cellView.getWtBtn() != null) {
            cellView.getWtBtn().addActionListener(e -> {
                cellDAO.setProperty(cellId, "stageNeedCurrent", cell.getStageNeedCurrent().replace("W", ""));
                updateCellView();
                // Set message playView
            });
        }
    }

    private void handleHvBtnEvent() {
        if (cellView.getHvBtn() != null) {
            cellView.getHvBtn().addActionListener(e -> {
                userDAO.setProperty("coin", userDAO.getUser().getCoin() + cell.getFinalRevenue());
                toStage0();
                updateCellView();
                // Set message playView
            });
        }
    }

    private void handleUrBtnEvent() {
        if (cellView.getUrBtn() != null) {
            cellView.getUrBtn().addActionListener(e -> {
                toStage0();
                updateCellView();
                // Set message playView
            });
        }
    }

    private void toStage0() {
        cellDAO.setProperty(cellId, "stage", 0);
        cellDAO.setProperty(cellId, "flowerId", "");
        cellDAO.setProperty(cellId, "stageNeedCurrent", "");
        cellDAO.setProperty(cellId, "timeRemaining", -1);
        cellDAO.setProperty(cellId, "finalRevenue", 0);
        cellDAO.setProperty(cellId, "occurPest", 1);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
}
