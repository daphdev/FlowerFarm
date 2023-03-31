package dao;

import entity.Pesticide;

public class PesticideDAO extends ItemDAO {

    public PesticideDAO() {
        setPath("main_data/pesticide.csv");
        setHeader(
                new String[] {"itemId", "itemName", "itemImg", "itemPrice", "levelUnlock", "itemDesc", "occurAgain"});
        setSupp(Pesticide::new);
        setList(read());
    }

}
