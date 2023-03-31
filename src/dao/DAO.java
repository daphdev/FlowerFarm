package dao;

import java.util.List;

public interface DAO<T> {

    public List<T> read();

    public void write(List<T> list);

    public void add(T t);

    public void edit(T t);

    public void delete(T t);

}
