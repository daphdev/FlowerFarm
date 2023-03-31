package controller;

import dao.AbstractDAO;
import dao.StorageDAO;
import factory.DAOFactory;
import factory.ViewFactory;
import observer.Observer;
import observer.Subject;
import view.AbstractView;
import view.StorageView;

import java.util.List;

public class StorageController extends AbstractController implements Observer {
    private final StorageView storageView;
    private final StorageDAO storageDAO;

    public StorageController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
        super(listView, listDAO);
        this.storageView = (StorageView) ViewFactory.get("Storage", listView);
        this.storageDAO = (StorageDAO) DAOFactory.get("Storage", listDAO);
        handleEvents();
    }

    private void handleEvents() {
        initCard();
    }

    private void initCard() {
        storageView.initCard(new String[] {"FL", "FE", "PE"}, storageDAO, listDAO);
    }

    private void updateCard() {
        storageView.resetCard(new String[] {"FL", "FE", "PE"});
        initCard();
    }

    @Override
    public void update(Subject s, Object arg) {
        if (s instanceof StorageDAO) {
            updateCard();
        }
    }
}
