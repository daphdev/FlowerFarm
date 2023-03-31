package dao;

import entity.Item;

import java.util.List;

public abstract class ItemDAO extends AbstractDAO {

    public ItemDAO() {}

    @SuppressWarnings("unchecked")
    public List<Item> getCastedList() {
        return (List<Item>) (List<?>) list;
    }

}
