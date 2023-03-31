package dao;

import entity.Fertilizer;

public class FertilizerDAO extends ItemDAO {

    public FertilizerDAO() {
        setPath("main_data/fertilizer.csv");
        setHeader(new String[] {"itemId", "itemName", "itemImg", "itemPrice", "levelUnlock", "itemDesc", "percent"});
        setSupp(Fertilizer::new);
        setList(read());
    }

}
