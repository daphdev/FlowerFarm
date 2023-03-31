package dao;

import entity.AbstractEntity;
import utils.IO;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractDAO implements DAO<AbstractEntity> {
    protected String path;
    protected String[] header;
    protected Supplier<AbstractEntity> supp;
    protected List<AbstractEntity> list;

    public AbstractDAO() {}

    @Override
    public List<AbstractEntity> read() {
        return IO.readFile(supp, path);
    }

    @Override
    public void write(List<AbstractEntity> list) {
        IO.writeFile(list, path, header);
    }

    @Override
    public void add(AbstractEntity entity) {
        list.add(entity);
        write(list);
    }

    @Override
    public void edit(AbstractEntity entity) {
        int index = 0;
        for (AbstractEntity e : list) {
            if (e.equals(entity)) {
                list.set(index, entity);
                break;
            }
            index++;
        }
        write(list);
    }

    @Override
    public void delete(AbstractEntity entity) {
        list.removeIf(e -> e.equals(entity));
        write(list);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setSupp(Supplier<AbstractEntity> supp) {
        this.supp = supp;
    }

    public List<AbstractEntity> getList() {
        return list;
    }

    public void setList(List<AbstractEntity> list) {
        this.list = list;
    }
}
