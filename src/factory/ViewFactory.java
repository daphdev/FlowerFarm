package factory;

import view.AbstractView;
import view.CellView;
import view.LandView;
import view.MainView;
import view.PlayView;
import view.PlotView;
import view.ShopView;
import view.StorageView;
import view.WelcomeView;

import java.util.List;

public class ViewFactory {

    public static AbstractView create(String name) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Main")) {
            return new MainView();
        } else if (name.equalsIgnoreCase("Welcome")) {
            return new WelcomeView();
        } else if (name.equalsIgnoreCase("Play")) {
            return new PlayView();
        } else if (name.equalsIgnoreCase("Land")) {
            return new LandView();
        } else if (name.equalsIgnoreCase("Shop")) {
            return new ShopView();
        } else if (name.equalsIgnoreCase("Storage")) {
            return new StorageView();
        }
        return null;
    }

    public static AbstractView create(String name, int id) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Plot")) {
            return new PlotView(id);
        } else if (name.equalsIgnoreCase("Cell")) {
            return new CellView(id);
        }
        return null;
    }

    public static AbstractView get(String name, List<AbstractView> list) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Main")) {
            return list.stream().filter(e -> e instanceof MainView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Welcome")) {
            return list.stream().filter(e -> e instanceof WelcomeView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Play")) {
            return list.stream().filter(e -> e instanceof PlayView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Land")) {
            return list.stream().filter(e -> e instanceof LandView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Shop")) {
            return list.stream().filter(e -> e instanceof ShopView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Plot")) {
            return list.stream().filter(e -> e instanceof PlotView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Storage")) {
            return list.stream().filter(e -> e instanceof StorageView).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Cell")) {
            return list.stream().filter(e -> e instanceof CellView).findAny().orElse(null);
        }
        return null;
    }

    public static AbstractView get(String name, List<AbstractView> list, int cellId) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Cell")) {
            for (AbstractView e : list) {
                if (e instanceof CellView && ((CellView) e).getCellId() == cellId) {
                    return e;
                }
            }
        }
        return null;
    }

}
