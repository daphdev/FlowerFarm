package observer;

public interface Subject {

    void addObserver(Observer o);

    void deleteObserver(Observer o);

    void notifyObservers();

}
