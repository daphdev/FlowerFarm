package controller;

import factory.ControllerFactory;
import factory.DAOFactory;
import factory.ViewFactory;
import view.AbstractView;
import view.MainView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainController extends AbstractController {
    private final MainView mainView;

    public MainController() {
        this.listView = new ArrayList<>();
        this.listDAO = new ArrayList<>();

        this.mainView = (MainView) ViewFactory.create("Main");
        AbstractView welcomeView = (AbstractView) mainView.getWelcomeView();

        this.listView.addAll(Arrays.asList(mainView, welcomeView));
        this.listDAO.addAll(Arrays.asList(DAOFactory.create("Register"), DAOFactory.create("Setting"),
                DAOFactory.create("FL"), DAOFactory.create("FE"), DAOFactory.create("PE")));

        ControllerFactory.create("Welcome", listView, listDAO);

        showMainView();
    }

    public void showMainView() {
        mainView.getMainFrame().setVisible(true);
    }
}
