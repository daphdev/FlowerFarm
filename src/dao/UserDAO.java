package dao;

import entity.User;
import observer.Observer;
import observer.Subject;
import utils.Getter;
import utils.Setter;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO implements Subject {
    private final List<Observer> observers;

    public UserDAO(String username) {
        setPath("user_data/" + username + "/user.csv");
        setHeader(new String[] {"id", "username", "password", "level", "exp", "coin", "plotNum", "cellNum"});
        setSupp(User::new);
        setList(read());
        this.observers = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public List<User> getCastedList() {
        return (List<User>) (List<?>) list;
    }

    public User getUser() {
        return (User) list.get(0);
    }

    public Object getProperty(String propertyName) {
        return Getter.getProperty(getUser(), propertyName);
    }

    public void setProperty(String propertyName, Object propertyValue) {
        Setter.setProperty(getUser(), propertyName, propertyValue);
        dataChanged();
        write(list);
    }

    public int getCellNumOfPlot(int plotId) {
        String[] cellNums = getUser().getCellNum().split("-");
        return Integer.parseInt(cellNums[plotId - 1]);
    }

    public void setCellNumOfPlot(int plotId, int newCellNum) {
        String[] cellNums = getUser().getCellNum().split("-");
        cellNums[plotId - 1] = String.valueOf(newCellNum);
        getUser().setCellNum(String.join("-", cellNums));
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
