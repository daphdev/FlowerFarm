package dao;

import entity.Storage;
import observer.Observer;
import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class StorageDAO extends AbstractDAO implements Subject {
    private final List<Observer> observers;

    public StorageDAO(String username) {
        setPath("user_data/" + username + "/storage.csv");
        setHeader(new String[] {"itemId", "amount"});
        setSupp(Storage::new);
        setList(read());
        this.observers = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<Storage> getCastedList() {
        return (List<Storage>) (List<?>) list;
    }

    public void addItem(String itemId) {
        Storage s = (Storage) list.stream().filter(e -> itemId.equals(((Storage) e).getItemId())).findAny()
                .orElse(null);
        if (s != null && list.contains(s)) {
            s.setAmount(s.getAmount() + 1);
            dataChanged();
            write(list);
            return;
        }
        add(new Storage(itemId, 1));
        dataChanged();
    }

    public void pickItem(String itemId) {
        Storage s = (Storage) list.stream().filter(e -> itemId.equals(((Storage) e).getItemId())).findAny()
                .orElse(null);
        if (s != null) {
            s.setAmount(s.getAmount() - 1);
            if (s.getAmount() == 0) {
                delete(s);
            }
        }
        dataChanged();
        write(list);
    }

    public void dataChanged() {
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
