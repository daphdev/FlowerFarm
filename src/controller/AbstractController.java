package controller;

import dao.AbstractDAO;
import view.AbstractView;

import java.util.List;

public abstract class AbstractController {
    protected List<AbstractView> listView;
    protected List<AbstractDAO> listDAO;

    public AbstractController() {}

    public AbstractController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
        this.listView = listView;
        this.listDAO = listDAO;
    }
}
