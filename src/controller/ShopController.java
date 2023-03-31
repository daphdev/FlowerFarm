package controller;

import dao.AbstractDAO;
import dao.StorageDAO;
import dao.UserDAO;
import factory.DAOFactory;
import factory.ViewFactory;
import observer.Observer;
import observer.Subject;
import swing.MyOptionPane;
import view.AbstractView;
import view.Entry;
import view.PlayView;
import view.ShopView;
import view.StorageView;

import javax.swing.*;
import java.util.List;

public class ShopController extends AbstractController implements Observer {
    private final ShopView shopView;
    private final StorageView storageView;
    private final PlayView playView;
    private final UserDAO userDAO;
    private final StorageDAO storageDAO;

    public ShopController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
        super(listView, listDAO);
        this.shopView = (ShopView) ViewFactory.get("Shop", listView);
        this.storageView = (StorageView) ViewFactory.get("Storage", listView);
        this.playView = (PlayView) ViewFactory.get("Play", listView);
        this.userDAO = (UserDAO) DAOFactory.get("User", listDAO);
        this.storageDAO = (StorageDAO) DAOFactory.get("Storage", listDAO);
        handleEvents();
    }

    private void handleEvents() {
        initCard();
        handleBuyBtnEvent();
        handleShopStorageSyncEvent();
    }

    private void initCard() {
        shopView.initCard(new String[]{"FL", "FE", "PE"}, listDAO);
    }

    private void updateCard() {
        shopView.resetCard(new String[]{"FL", "FE", "PE"});
        initCard();
    }

    private void handleBuyBtnEvent() {
        for (Entry entry : shopView.getEntries()) {
            entry.getActionBtn().addActionListener(e -> {
                if ((int) userDAO.getProperty("coin") >= entry.getItem().getItemPrice()) {
                    userDAO.setProperty("coin", (int) userDAO.getProperty("coin") - entry.getItem().getItemPrice());
                    storageDAO.addItem(entry.getItem().getItemId());
                } else {
                    MyOptionPane.showMessageDialog(shopView, "You don't have enough coin.");
                }
            });
            if (entry.getItem().getLevelUnlock() > (int) userDAO.getProperty("level")) {
                entry.getActionBtn().setEnabled(false);
            }
        }
    }

    private void handleShopStorageSyncEvent() {
        for (JButton tabBtn : shopView.getTabBtns()) {
            tabBtn.addActionListener(e -> {
                playView.showStorageView();
                storageView.showCard(tabBtn.getText().substring(0, 2).toUpperCase());
            });
        }
    }

    @Override
    public void update(Subject s, Object arg) {
        if (s instanceof UserDAO) {
            updateCard();
            handleBuyBtnEvent();
        }
    }
}
