package view;

import entity.User;
import lib.RelativeLayout;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.ArrayUtils;
import swing.MyButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayView extends JPanel implements AbstractView {
    private static final long serialVersionUID = 1L;
    private JLabel iconLabel, titleLabel, usernameLabel, idLabel, statusLabel;
    private JButton levelBtn, expBtn, coinBtn, plotBtn, logoutBtn, settingBtn;
    private JPanel topPanel, infoPanel, centerPanel, leftPanel, rightPanel, bottomPanel, leftCard1, leftCard1Temp,
            leftCard2, rightCard1, leftBlank, rightBlank;
    private RelativeLayout r;
    private JButton[] taskBtns;

    public PlayView() {
        init();
    }

    private void init() {
        // Settings for PlayView
        setLayout(new MigLayout("insets 0"));
        r = new RelativeLayout(RelativeLayout.X_AXIS);
        r.setFill(true);

        // Top Panel
        topPanel = new JPanel(new MigLayout("insets 5 10 5 5"));
        topPanel.setBackground(Color.decode("#f0f0f0"));
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#d1d1d1")));

        iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon("img/gameIcon.png"));

        infoPanel = new JPanel();
        infoPanel.setLayout(new MigLayout("insets 0, wrap 1", "", "[13!]"));
        infoPanel.setBackground(Color.decode("#f0f0f0"));

        titleLabel = new JLabel("FLOWER FARM");
        titleLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
        usernameLabel = new JLabel("Username: admin");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        idLabel = new JLabel("ID: f337e691-1cab-42ca-a0d9-eaab079def38");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        infoPanel.add(titleLabel);
        infoPanel.add(usernameLabel);
        infoPanel.add(idLabel);

        levelBtn = new MyButton("<html><center>0<br><span style='font-size: 10px'>Level</span></center></html>", 70, 60,
                18, "#fbfbfb", "#ffffff", "#f5f5f5");
        expBtn = new MyButton("<html><center>1<br><span style='font-size: 10px'>Exp</span></center></html>", 70, 60, 18,
                "#fbfbfb", "#ffffff", "#f5f5f5");
        coinBtn = new MyButton("<html><center>15<br><span style='font-size: 10px'>Coin</span></center></html>", 70, 60,
                18, "#fbfbfb", "#ffffff", "#f5f5f5");
        plotBtn = new MyButton("<html><center>1<br><span style='font-size: 10px'>Plot</span></center></html>", 70, 60,
                18, "#fbfbfb", "#ffffff", "#f5f5f5");

        topPanel.add(iconLabel, "left");
        topPanel.add(infoPanel, "pushx, left, gap 5 5");
        topPanel.add(MyButton.createButtonGroup(new JButton[] {levelBtn, expBtn, coinBtn, plotBtn}, "#d1d1d1"),
                "pushx, right, h p!");

        // Center Panel
        centerPanel = new JPanel(r);

        // Center Panel > Left Panel
        leftPanel = new JPanel(new CardLayout());

        // Center Panel > Right Panel
        rightPanel = new JPanel(new CardLayout());
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#d1d1d1")));

        rightCard1 = new StorageView();
        rightCard1.setName("RStorage");

        rightBlank = new JPanel();

        rightPanel.add(rightCard1, "RStorage");
        rightPanel.add(rightBlank, "RBlank");

        centerPanel.add(leftPanel, 70f);
        centerPanel.add(rightPanel, 30f);

        // Bottom Panel
        bottomPanel = new JPanel(new MigLayout("insets 5"));
        bottomPanel.setBackground(Color.decode("#f0f0f0"));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#d1d1d1")));

        logoutBtn = new MyButton("Log out", 75, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        settingBtn = new MyButton("Setting", 75, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");

        statusLabel = new JLabel("Status message here.");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        bottomPanel.add(MyButton.createButtonGroup(new JButton[] {logoutBtn, settingBtn}, "#d1d1d1"), "left");
        bottomPanel.add(statusLabel, "pushx, left, gap 5 5");

        // Add Panels to PlayView
        add(topPanel, "dock north");
        add(centerPanel, "dock center");
        add(bottomPanel, "dock south");

        // Handle Events
        initLeftPanel();
        initTaskBtn();
    }

    public void updateTopPanel(User user) {
        this.usernameLabel.setText("Username: " + user.getUsername());
        this.idLabel.setText("ID: " + user.getId());
        this.levelBtn.setText("<html><center>" + String.valueOf(user.getLevel())
                + "<br><span style='font-size: 10px'>Level</span></center></html>");
        this.expBtn.setText("<html><center>" + String.valueOf(user.getExp())
                + "<br><span style='font-size: 10px'>Exp</span></center></html>");
        this.coinBtn.setText("<html><center>" + String.valueOf(user.getCoin())
                + "<br><span style='font-size: 10px'>Coin</span></center></html>");
        this.plotBtn.setText("<html><center>" + String.valueOf(user.getPlotNum())
                + "<br><span style='font-size: 10px'>Plot</span></center></html>");
    }

    public List<SideView> getCards() {
        Component[] cardComps = ArrayUtils.addAll(leftPanel.getComponents(), rightPanel.getComponents());
        List<SideView> cards = new ArrayList<>();
        for (Component cardComp : cardComps) {
            if (cardComp instanceof SideView) {
                cards.add((SideView) cardComp);
            }
        }
        return cards;
    }

    // Factory Method Pattern for Get Card SideView: Land, Shop, Plot, Storage
    public SideView getCard(String cardType) {
        if (cardType == null) {
            return null;
        }
        if (cardType.equalsIgnoreCase("Land")) {
            return getCards().stream().filter(e -> e instanceof LandView).findAny().orElse(null);
        } else if (cardType.equalsIgnoreCase("Shop")) {
            return getCards().stream().filter(e -> e instanceof ShopView).findAny().orElse(null);
        } else if (cardType.equalsIgnoreCase("Plot")) {
            return getCards().stream().filter(e -> e instanceof PlotView).findAny().orElse(null);
        } else if (cardType.equalsIgnoreCase("Storage")) {
            return getCards().stream().filter(e -> e instanceof StorageView).findAny().orElse(null);
        }
        return null;
    }

    public CardLayout getLeftCardLayout() {
        return (CardLayout) this.leftPanel.getLayout();
    }

    public CardLayout getRightCardLayout() {
        return (CardLayout) this.rightPanel.getLayout();
    }

    public void initTaskBtn() {
        taskBtns = new JButton[getCards().size()];
        for (int i = 0; i < getCards().size(); i++) {
            taskBtns[i] = new MyButton(getCards().get(i).getName().substring(1), 70, 32, 13, "#fbfbfb", "#ffffff",
                    "#f5f5f5");
            taskBtns[i].setName(getCards().get(i).getName());
        }
        setEnabledTaskBtn();
        handleTaskBtnEvent();
        bottomPanel.add(MyButton.createButtonGroup(taskBtns, "#d1d1d1"), "pushx, right, h p!");
    }

    public void setEnabledTaskBtn() {
        for (int i = 0; i < getCards().size(); i++) {
            taskBtns[i].setEnabled(!getCards().get(i).isVisible());
        }
    }

    private void handleTaskBtnEvent() {
        for (JButton taskBtn : taskBtns) {
            taskBtn.addActionListener(e -> {
                if (taskBtn.getName().startsWith("L")) {
                    getLeftCardLayout().show(leftPanel, taskBtn.getName());
                } else {
                    getRightCardLayout().show(rightPanel, taskBtn.getName());
                    setFloat(70, 30);
                }
                setEnabledTaskBtn();
            });
        }
    }

    public void updateTaskBtn() {
        bottomPanel.remove(bottomPanel.getComponent(2)); // Xoa taskBtn
        bottomPanel.revalidate();
        bottomPanel.repaint();
        Arrays.fill(taskBtns, null);
        taskBtns = null;
        initTaskBtn();
    }

    public void setDisabledTaskBtn() {
        for (JButton taskBtn : taskBtns) {
            taskBtn.setEnabled(false);
        }
    }

    public void setEnabledHideBtn(boolean b) {
        for (SideView card : getCards()) {
            card.getHideBtn().setEnabled(b);
        }
    }

    public void initLeftPanel() {
        leftCard1 = new LandView();
        leftCard1.setName("LLand");

        leftCard2 = new ShopView();
        leftCard2.setName("LShop");

        leftBlank = new JPanel();
        leftBlank.setName("LBlank");
        leftBlank.setBackground(Color.decode("#f7f7f7"));

        addCardsToLeftPanel(new JPanel[] {leftCard1, leftCard2, leftBlank});
    }

    public void updatePlotViewForLeftPanel(int plotId) {
        leftPanel.removeAll();
        leftPanel.revalidate();
        leftPanel.repaint();

        leftCard1Temp = leftCard1;
        leftCard1 = new PlotView(plotId);
        leftCard1.setName("LPlot " + plotId);

        addCardsToLeftPanel(new JPanel[] {leftCard1, leftCard2, leftBlank});
    }

    public void updateLandViewForLeftPanel() {
        leftPanel.removeAll();
        leftPanel.revalidate();
        leftPanel.repaint();

        leftCard1 = leftCard1Temp; // Lay lai LandView tu bien temp
        leftCard1Temp = null; // Xoa tham chieu cua bien

        addCardsToLeftPanel(new JPanel[] {leftCard1, leftCard2, leftBlank});
    }

    public void addCardsToLeftPanel(JPanel[] cards) {
        for (JPanel card : cards) {
            leftPanel.add(card, card.getName());
        }
        handleHideBtnEvent();
    }

    private void handleHideBtnEvent() {
        for (SideView card : getCards()) {
            card.getHideBtn().addActionListener(e -> {
                if (card.getName().startsWith("L")) {
                    getLeftCardLayout().show(leftPanel, "LBlank");
                } else {
                    getRightCardLayout().show(rightPanel, "RBlank");
                    setFloat(100, 0);
                }
                setEnabledTaskBtn();
            });
        }
    }

    public void setFloat(float left, float right) {
        centerPanel.add(leftPanel, left);
        centerPanel.add(rightPanel, right);
    }

    public void showStorageView() {
        if (!getCard("Storage").isVisible()) {
            getRightCardLayout().show(rightPanel, "RStorage");
            setFloat(70, 30);
            setEnabledTaskBtn();
        }
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public JButton getSettingBtn() {
        return settingBtn;
    }
}
