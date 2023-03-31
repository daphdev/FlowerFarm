package factory;

import controller.AbstractController;
import controller.CellController;
import controller.LandController;
import controller.MainController;
import controller.PlayController;
import controller.PlotController;
import controller.ShopController;
import controller.StorageController;
import controller.WelcomeController;
import dao.AbstractDAO;
import view.AbstractView;

import java.util.List;

public class ControllerFactory {

    public static AbstractController create(String name) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Main")) {
            return new MainController();
        }
        return null;
    }

    public static AbstractController create(String name, List<AbstractView> listView, List<AbstractDAO> listDAO) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Welcome")) {
            return new WelcomeController(listView, listDAO);
        } else if (name.equalsIgnoreCase("Play")) {
            return new PlayController(listView, listDAO);
        } else if (name.equalsIgnoreCase("Land")) {
            return new LandController(listView, listDAO);
        } else if (name.equalsIgnoreCase("Shop")) {
            return new ShopController(listView, listDAO);
        } else if (name.equalsIgnoreCase("Plot")) {
            return new PlotController(listView, listDAO);
        } else if (name.equalsIgnoreCase("Storage")) {
            return new StorageController(listView, listDAO);
        }
        return null;
    }

    public static AbstractController create(String name,
                                            List<AbstractView> listView,
                                            List<AbstractDAO> listDAO,
                                            int cellId) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Cell")) {
            return new CellController(listView, listDAO, cellId);
        }
        return null;
    }

}
