package controller;

import dao.AbstractDAO;
import dao.SettingDAO;
import dao.UserDAO;
import factory.ControllerFactory;
import factory.DAOFactory;
import factory.ViewFactory;
import observer.Observer;
import observer.Subject;
import view.AbstractView;
import view.LandView;
import view.PlayView;
import view.PlotView;

import javax.swing.*;
import java.util.List;

public class LandController extends AbstractController implements Observer {
    private final LandView landView;
    private final PlayView playView;
    private final UserDAO userDAO;
    private final SettingDAO settingDAO;

    public LandController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
        super(listView, listDAO);
        this.landView = (LandView) ViewFactory.get("Land", listView);
        this.playView = (PlayView) ViewFactory.get("Play", listView);
        this.userDAO = (UserDAO) DAOFactory.get("User", listDAO);
        this.settingDAO = (SettingDAO) DAOFactory.get("Setting", listDAO);
        handleEvents();
    }

    private void handleEvents() {
        initPlotBtn();
        handlePlotBtnEvent();
    }

    private void initPlotBtn() {
        landView.initPlotBtn(getPlotNum(), getPlotNumMax());
    }

    private void handlePlotBtnEvent() {
        for (JButton plotBtn : landView.getPlotBtns()) {
            plotBtn.addActionListener(e -> {
                playView.updatePlotViewForLeftPanel(Integer.parseInt(plotBtn.getName()));
                playView.updateTaskBtn();
                PlotView plotView = (PlotView) playView.getCard("Plot");
                AbstractDAO cellDAO = DAOFactory.create("Cell", userDAO.getUser().getUsername(), plotView.getPlotId());
                listView.add(plotView);
                listDAO.add(cellDAO);
                ControllerFactory.create("Plot", listView, listDAO);
            });
        }
    }

    private void updatePlotBtn() {
        landView.getPlotBtns().get(getPlotNum() - 1).setEnabled(true);
    }

    private int getPlotNum() {
        return (int) userDAO.getProperty("plotNum");
    }

    private int getPlotNumMax() {
        return (int) settingDAO.getProperty("plotNumMax");
    }

    @Override
    public void update(Subject s, Object arg) {
        if (s instanceof UserDAO) {
            updatePlotBtn();
        }
    }
}
