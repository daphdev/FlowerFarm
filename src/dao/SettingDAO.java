package dao;

import entity.Setting;
import utils.Getter;

import java.util.List;

public class SettingDAO extends AbstractDAO {

    public SettingDAO() {
        setPath("main_data/setting.csv");
        setHeader(new String[] {"plotNumMax", "cellNumMax"});
        setSupp(Setting::new);
        setList(read());
    }

    @SuppressWarnings("unchecked")
    public List<Setting> getCastedList() {
        return (List<Setting>) (List<?>) list;
    }

    public Object getProperty(String propertyName) {
        return Getter.getProperty(list.get(0), propertyName);
    }
}
