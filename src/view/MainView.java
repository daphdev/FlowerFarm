package view;

import javax.swing.*;
import java.awt.*;

public class MainView implements AbstractView {
    private JFrame mainFrame;
    private JPanel mainPanel, welcomeView;

    public MainView() {
        init();
    }

    private void init() {
        mainFrame = new JFrame();

        mainPanel = new JPanel(new CardLayout());

        welcomeView = new WelcomeView();

        mainPanel.add(welcomeView, "Welcome");

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setTitle("Flower Farm");
        mainFrame.setSize(1050, 680);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CardLayout getCardLayout() {
        return (CardLayout) mainPanel.getLayout();
    }

    public JPanel getWelcomeView() {
        return welcomeView;
    }
}
