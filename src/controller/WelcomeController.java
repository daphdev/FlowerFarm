package controller;

import dao.AbstractDAO;
import dao.RegisterDAO;
import entity.Register;
import entity.User;
import factory.ControllerFactory;
import factory.DAOFactory;
import factory.ViewFactory;
import swing.MyOptionPane;
import utils.Validator;
import view.AbstractView;
import view.MainView;
import view.PlayView;
import view.WelcomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class WelcomeController extends AbstractController {
    private final WelcomeView welcomeView;
    private final MainView mainView;
    private final RegisterDAO registerDAO;

    public WelcomeController(List<AbstractView> listView, List<AbstractDAO> listDAO) {
        super(listView, listDAO);
        this.welcomeView = (WelcomeView) ViewFactory.get("Welcome", listView);
        this.mainView = (MainView) ViewFactory.get("Main", listView);
        this.registerDAO = (RegisterDAO) DAOFactory.get("Register", listDAO);
        handleEvents();
    }

    private void handleEvents() {
        handleSignupEvent();
        handleLoginEvent();
    }

    private void handleSignupEvent() {
        welcomeView.getSignupBtn().addActionListener(e -> {
            JTextField uF = welcomeView.getUsernameField();
            JTextField pF = welcomeView.getPasswordField();
            Validator v = new Validator(welcomeView);
            String uFIsNotEmptyMessage = "Username can't be blank. Please input again.";
            String pFIsNotEmptyMessage = "Password can't be blank. Please input again.";
            String uFIsRegexMatchMessage = "Username only contains uppercase, lowercase letters and numbers.";
            String pFIsRegexMatchMessage = "Password only contains uppercase, lowercase letters and numbers.";
            String uFIsNotExistedMessage = "This username already exists. Please input a new one.";

            if (v.isNotEmpty(uF, uFIsNotEmptyMessage) && v.isRegexMatch(uF, "([a-zA-Z0-9]+)", uFIsRegexMatchMessage)
                    && v.isNotExisted(uF, registerDAO.getList(), "username", uFIsNotExistedMessage)
                    && v.isNotEmpty(pF, pFIsNotEmptyMessage)
                    && v.isRegexMatch(pF, "([a-zA-Z0-9]+)", pFIsRegexMatchMessage)) {
                Register register = new Register(uF.getText(), pF.getText());
                registerDAO.add(register);
                AbstractDAO userDAO = DAOFactory.create("User", uF.getText());
                userDAO.add(new User(register));
                MyOptionPane.showMessageDialog(welcomeView, "Successful registration!");
            }
        });
    }

    Action loginAction = new AbstractAction() {
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField uF = welcomeView.getUsernameField();
            JTextField pF = welcomeView.getPasswordField();
            Validator v = new Validator(welcomeView);
            String uFIsNotEmptyMessage = "Username can't be blank. Please input again.";
            String pFIsNotEmptyMessage = "Password can't be blank. Please input again.";
            String uFIsRegexMatchMessage = "Username only contains uppercase, lowercase letters and numbers.";
            String pFIsRegexMatchMessage = "Password only contains uppercase, lowercase letters and numbers.";
            String isExistedRegisterMessage = "Wrong username and/or password!";

            if (v.isNotEmpty(uF, uFIsNotEmptyMessage) && v.isRegexMatch(uF, "([a-zA-Z0-9]+)", uFIsRegexMatchMessage)
                    && v.isNotEmpty(pF, pFIsNotEmptyMessage)
                    && v.isRegexMatch(pF, "([a-zA-Z0-9]+)", pFIsRegexMatchMessage)
                    && v.isExistedRegister(uF, pF, registerDAO.getCastedList(), isExistedRegisterMessage)) {
                MyOptionPane.showMessageDialog(welcomeView, "Successful logging in!");

                PlayView playView = (PlayView) ViewFactory.create("Play");
                AbstractView landView = playView.getCard("Land");
                AbstractView shopView = playView.getCard("Shop");
                AbstractView storageView = playView.getCard("Storage");
                listView.addAll(Arrays.asList(playView, landView, shopView, storageView));

                mainView.getMainPanel().add(playView, "Play");
                mainView.getCardLayout().show(mainView.getMainPanel(), "Play");

                AbstractDAO userDAO = DAOFactory.create("User", uF.getText());
                listDAO.add(userDAO);

                ControllerFactory.create("Play", listView, listDAO);
            }
        }
    };

    private void handleLoginEvent() {
        welcomeView.getPasswordField().addActionListener(loginAction);
        welcomeView.getLoginBtn().addActionListener(loginAction);
    }
}
