package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import dao.AbstractDAO;
import dao.CellDAO;
import dao.SettingDAO;
import dao.UserDAO;
import entity.Cell;
import factory.ControllerFactory;
import factory.DAOFactory;
import factory.ViewFactory;
import swing.MyOptionPane;
import view.AbstractView;
import view.CellView;
import view.PlayView;
import view.PlotView;

public class PlotController extends AbstractController {
	private final PlotView plotView;
	private final PlayView playView;
	private final CellDAO cellDAO;
	private final UserDAO userDAO;
	private final SettingDAO settingDAO;
	public static List<CellController> listCellController;

	public PlotController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
		super(listView, listDAO);
		this.plotView = (PlotView) ViewFactory.get("Plot", listView);
		this.playView = (PlayView) ViewFactory.get("Play", listView);
		this.cellDAO = (CellDAO) DAOFactory.get("Cell", listDAO);
		this.userDAO = (UserDAO) DAOFactory.get("User", listDAO);
		this.settingDAO = (SettingDAO) DAOFactory.get("Setting", listDAO);
		PlotController.listCellController = new ArrayList<>();
		handleEvents();
	}

	private void handleEvents() {
		initCellPanel();
		handleCellLockEvent();
		handleLandBtnEvent();
	}

	private void initCellPanel() {
		plotView.initCellPanel(getCellNum(), getCellNumMax());
		for (CellView cellView : plotView.getCellViews()) {
			invokeCellController(cellView, cellView.getCellId());
		}
	}

	private void invokeCellController(CellView cellView, int cellId) {
		listView.add(cellView);
		listCellController.add((CellController) ControllerFactory.create("Cell", listView, listDAO, cellId));
	}

	private void updateCellPanel() {
		JPanel cellPanel = plotView.getCellPanels().get(getCellNum() - 1);
		cellPanel.removeAll();
		cellPanel.revalidate();
		cellPanel.repaint();

		CellView cellView = (CellView) ViewFactory.create("Cell", getCellNum());
		cellPanel.add(cellView);
		plotView.getCellViews().add(cellView);
		cellDAO.add(new Cell(getCellNum()));
		invokeCellController(cellView, cellView.getCellId());
	}

	private void handleCellLockEvent() {
		int cellNum = getCellNum();
		int plotNum = getPlotNum();
		int cellNumMax = getCellNumMax();
		int plotNumMax = getPlotNumMax();
		if (cellNum < cellNumMax) {
			JButton cellLock = plotView.getCellLocks().get(0);
			cellLock.setEnabled(true);
			cellLock.addActionListener(e -> {
				if ((int) userDAO.getProperty("coin") >= 5) { // 5 la so tien de mo khoa 1 cell
					userDAO.setProperty("exp", (int) userDAO.getProperty("exp") + 1);
					userDAO.setProperty("coin", (int) userDAO.getProperty("coin") - 5);
					userDAO.setCellNumOfPlot(plotView.getPlotId(), cellNum + 1);
					// Xet cell cuoi cung cua mot plot
					if (cellNum == cellNumMax - 1 && plotNum < plotNumMax) {
						userDAO.setProperty("level", (int) userDAO.getProperty("level") + 1);
						userDAO.setProperty("plotNum", (int) userDAO.getProperty("plotNum") + 1);
						userDAO.setProperty("cellNum", userDAO.getProperty("cellNum") + "-0");
						MyOptionPane.showMessageDialog(plotView,
								"You have reached level " + userDAO.getProperty("level") + ".");
					}
					// Xet cell cuoi cung cua plot cuoi cung
					if (cellNum == cellNumMax - 1 && plotNum == plotNumMax) {
						userDAO.setProperty("level", (int) userDAO.getProperty("level") + 1);
						MyOptionPane.showMessageDialog(plotView,
								"You have reached level " + userDAO.getProperty("level") + " and won the game!");
					}
					plotView.getCellLocks().remove(cellLock);
					updateCellPanel();
					handleCellLockEvent();
				} else {
					MyOptionPane.showMessageDialog(plotView, "You don't have enough coin.");
				}
			});
		}
	}

	private void handleLandBtnEvent() {
		plotView.getLandBtn().addActionListener(e -> {
			playView.updateLandViewForLeftPanel();
			playView.updateTaskBtn();
			listView.removeIf(v -> v instanceof PlotView);
			listView.removeIf(v -> v instanceof CellView);
			listDAO.removeIf(d -> d instanceof CellDAO);
			stopTimer();
		});
	}

	public static void stopTimer() {
		if (listCellController != null && !listCellController.isEmpty()) {
			listCellController.forEach(CellController::stopTimer);
			listCellController.clear();
		}
	}

	private int getCellNum() {
		return userDAO.getCellNumOfPlot(plotView.getPlotId());
	}

	private int getPlotNum() {
		return (int) userDAO.getProperty("plotNum");
	}

	private int getCellNumMax() {
		return (int) settingDAO.getProperty("cellNumMax");
	}

	private int getPlotNumMax() {
		return (int) settingDAO.getProperty("plotNumMax");
	}
}
