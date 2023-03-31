package view;

import lib.RelativeLayout;
import net.miginfocom.swing.MigLayout;
import swing.MyButton;

import javax.swing.*;
import java.awt.*;

public class WelcomeView extends JPanel implements AbstractView {
    private static final long serialVersionUID = 1L;
    private JLabel loginLabel, usernameLabel, passwordLabel, titleLabel, icon1, icon2, icon3;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, signupBtn;
    private JPanel leftPanel, rightPanel;
    private RelativeLayout r;

    public WelcomeView() {
        init();
    }

    private void init() {
        r = new RelativeLayout(RelativeLayout.X_AXIS);
        r.setFill(true);
        setLayout(r);

        // Left Panel
        leftPanel = new JPanel(new MigLayout("wrap 1, align 50% 45%, gap 20 20, fillx, insets 30"));
        leftPanel.setBackground(Color.decode("#fdfdfd"));

        titleLabel = new JLabel("Flower Farm");
        titleLabel.setFont(new Font("Linux Libertine", Font.PLAIN, 90));

        icon1 = new JLabel();
        icon1.setIcon(new ImageIcon("img/icon1.png"));
        icon2 = new JLabel();
        icon2.setIcon(new ImageIcon("img/icon2.png"));
        icon3 = new JLabel();
        icon3.setIcon(new ImageIcon("img/icon3.png"));

        leftPanel.add(titleLabel, "center");
        leftPanel.add(icon1, "gapright 25, h 200!, center, split 3");
        leftPanel.add(icon2, "gapright 25, h 200!, center");
        leftPanel.add(icon3, "h 200!, center");

        // Right Panel
        rightPanel = new JPanel(new MigLayout("wrap 1, align 50% 40%, fillx, insets 30", "", "[p!]"));
        rightPanel.setBackground(Color.decode("#f3f8fe"));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#d1d1d1")));

        loginLabel = new JLabel("Log in");
        loginLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 25));

        usernameLabel = new JLabel("Username *");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setColumns(30);
        usernameField.setPreferredSize(new Dimension(300, 45));
        usernameField
                .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#d1d1d1")),
                        BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        passwordLabel = new JLabel("Password *");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setColumns(30);
        passwordField.setPreferredSize(new Dimension(300, 45));
        passwordField
                .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#d1d1d1")),
                        BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        loginBtn = new MyButton("Log in", 95, 45, 15, "#fbfbfb", "#ffffff", "#f5f5f5", "#d1d1d1");
        signupBtn = new MyButton("Sign up", 95, 45, 15, "#fbfbfb", "#ffffff", "#f5f5f5", "#d1d1d1");

        rightPanel.add(loginLabel, "h 45!");
        rightPanel.add(usernameLabel, "h 45!");
        rightPanel.add(usernameField, "h 45!");
        rightPanel.add(passwordLabel, "h 45!");
        rightPanel.add(passwordField, "h 45!");
        rightPanel.add(loginBtn, "w 100%, h 45!, gaptop 15, gapright 20, split 2");
        rightPanel.add(signupBtn, "w 100%, h 45!, gaptop 15, gapleft 20");

        add(leftPanel, 65f);
        add(rightPanel, 35f);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JButton getSignupBtn() {
        return signupBtn;
    }
}
