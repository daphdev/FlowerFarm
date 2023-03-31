package dao;

import entity.Register;

import java.util.List;

public class RegisterDAO extends AbstractDAO {

    public RegisterDAO() {
        setPath("main_data/register.csv");
        setHeader(new String[] {"id", "username", "password"});
        setSupp(Register::new);
        setList(read());
    }

    @SuppressWarnings("unchecked")
    public List<Register> getCastedList() {
        return (List<Register>) (List<?>) list;
    }

}
