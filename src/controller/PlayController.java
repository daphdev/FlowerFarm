package controller;

import dao.AbstractDAO;
import dao.CellDAO;
import dao.StorageDAO;
import dao.UserDAO;
import factory.ControllerFactory;
import factory.DAOFactory;
import factory.ViewFactory;
import observer.Observer;
import observer.Subject;
import swing.MyOptionPane;
import view.AbstractView;
import view.CellView;
import view.LandView;
import view.MainView;
import view.PlayView;
import view.PlotView;
import view.ShopView;
import view.StorageView;

import java.util.List;

public class PlayController extends AbstractController implements Observer {
    private final PlayView playView;
    private final UserDAO userDAO;
    private final StorageDAO storageDAO;

    public PlayController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
        super(listView, listDAO);

        this.playView = (PlayView) ViewFactory.get("Play", listView);

        this.userDAO = (UserDAO) DAOFactory.get("User", listDAO);
        this.storageDAO = (StorageDAO) DAOFactory.create("Storage", userDAO.getUser().getUsername());
        this.listDAO.add(storageDAO);

        // Invoke Controllers & Add Observers to Subjects
        this.userDAO.addObserver(this); // Subject
        this.userDAO.addObserver((Observer) ControllerFactory.create("Land", listView, listDAO));
        this.userDAO.addObserver((Observer) ControllerFactory.create("Shop", listView, listDAO));
        this.storageDAO.addObserver((Observer) ControllerFactory.create("Storage", listView, listDAO)); // Subject

        handleEvents();
    }

    private void handleEvents() {
        updateTopPanel();
        handleLogoutBtnEvent();
    }

    private void updateTopPanel() {
        playView.updateTopPanel(userDAO.getUser());
    }

    private void handleLogoutBtnEvent() {
        playView.getLogoutBtn().addActionListener(e -> {
            if (MyOptionPane.showConfirmDialog(playView, "Are you sure you want to log out?",
                    "Log out confirmation") == 0) {
                listView.removeIf(v -> v instanceof PlayView);
                listView.removeIf(v -> v instanceof LandView);
                listView.removeIf(v -> v instanceof ShopView);
                listView.removeIf(v -> v instanceof StorageView);
                listView.removeIf(v -> v instanceof PlotView);
                listView.removeIf(v -> v instanceof CellView);
                listDAO.removeIf(d -> d instanceof UserDAO);
                listDAO.removeIf(d -> d instanceof StorageDAO);
                listDAO.removeIf(d -> d instanceof CellDAO);
                userDAO.getObservers().clear();
                storageDAO.getObservers().clear();
                if (PlotController.listCellController != null) {
                    PlotController.stopTimer();
                }
                MainView mainView = (MainView) ViewFactory.get("Main", listView);
                mainView.getCardLayout().show(mainView.getMainPanel(), "Welcome");
                mainView.getMainPanel().remove(mainView.getMainPanel().getComponent(1)); // Xoa card PlayView
            }
        });
    }

    @Override
    public void update(Subject s, Object arg) {
        if (s instanceof UserDAO) {
            updateTopPanel();
        }
    }
}
