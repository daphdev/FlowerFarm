package dao;

import entity.Flower;

import java.util.List;

public class FlowerDAO extends ItemDAO {

    public FlowerDAO() {
        setPath("main_data/flower.csv");
        setHeader(new String[] {"itemId", "itemName", "itemImg", "itemPrice", "levelUnlock", "itemDesc", "revenue",
                "stageImg", "stageTime", "stageNeed"});
        setSupp(Flower::new);
        setList(read());
    }

    @SuppressWarnings("unchecked")
    public List<Flower> getFlowerList() {
        return (List<Flower>) (List<?>) list;
    }

}
