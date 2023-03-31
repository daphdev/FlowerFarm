package factory;

import dao.AbstractDAO;
import dao.CellDAO;
import dao.FertilizerDAO;
import dao.FlowerDAO;
import dao.PesticideDAO;
import dao.RegisterDAO;
import dao.SettingDAO;
import dao.StorageDAO;
import dao.UserDAO;

import java.util.List;

public class DAOFactory {

    public static AbstractDAO create(String name) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Register")) {
            return new RegisterDAO();
        } else if (name.equalsIgnoreCase("Setting")) {
            return new SettingDAO();
        } else if (name.equalsIgnoreCase("FL")) {
            return new FlowerDAO();
        } else if (name.equalsIgnoreCase("FE")) {
            return new FertilizerDAO();
        } else if (name.equalsIgnoreCase("PE")) {
            return new PesticideDAO();
        }
        return null;
    }

    public static AbstractDAO create(String name, String username) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("User")) {
            return new UserDAO(username);
        } else if (name.equalsIgnoreCase("Storage")) {
            return new StorageDAO(username);
        }
        return null;
    }

    public static AbstractDAO create(String name, String username, int id) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Cell")) {
            return new CellDAO(username, id);
        }
        return null;
    }

    public static AbstractDAO get(String name, List<AbstractDAO> list) {
        if (name == null) {
            return null;
        }
        if (name.equalsIgnoreCase("Register")) {
            return list.stream().filter(e -> e instanceof RegisterDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("User")) {
            return list.stream().filter(e -> e instanceof UserDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Setting")) {
            return list.stream().filter(e -> e instanceof SettingDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Storage")) {
            return list.stream().filter(e -> e instanceof StorageDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("FL")) {
            return list.stream().filter(e -> e instanceof FlowerDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("FE")) {
            return list.stream().filter(e -> e instanceof FertilizerDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("PE")) {
            return list.stream().filter(e -> e instanceof PesticideDAO).findAny().orElse(null);
        } else if (name.equalsIgnoreCase("Cell")) {
            return list.stream().filter(e -> e instanceof CellDAO).findAny().orElse(null);
        }
        return null;
    }

}
