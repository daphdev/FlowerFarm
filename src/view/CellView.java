package view;

import dao.FlowerDAO;
import entity.Cell;
import entity.Flower;
import net.miginfocom.swing.MigLayout;
import swing.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CellView extends JPanel implements AbstractView {
    private static final long serialVersionUID = 1L;
    private final int cellId;
    private JLabel flowerNameLabel, stageLabel, timeLabel, cellImgLabel, warningIconLabel;
    private JProgressBar timeBar;
    private JButton ctBtn, hvBtn, urBtn, wtBtn, feBtn, peBtn, exitBtn;
    private JPanel topPanel, centerPanel, bottomPanel, buttonCard, warningCard, exitCard;
    private Cell cell;
    private FlowerDAO flowerDAO;

    public CellView(int cellId) {
        this.cellId = cellId;
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets 0", "[210!]", "[p!]"));

        // Top Panel
        topPanel = new JPanel(new MigLayout("fill, insets 3, gap 0 3, align 50% 50%", "", "[30!][20!]"));

        flowerNameLabel = new JLabel("Flower");
        flowerNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        stageLabel = new JLabel("S1", JLabel.RIGHT);
        stageLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
        stageLabel.setVisible(false);

        timeBar = new JProgressBar();
        timeBar.setBorderPainted(false);
        timeBar.setForeground(Color.decode("#aeef18"));
        timeBar.setBackground(Color.decode("#f0f0f0"));
        timeBar.setValue(50);

        timeLabel = new JLabel("30s");
        timeLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
        timeLabel.setVisible(false);

        topPanel.add(flowerNameLabel, "center, span 3, wrap");
        topPanel.add(stageLabel, "push, gapright 15, w 20%!");
        topPanel.add(timeBar, "push, center");
        topPanel.add(timeLabel, "push, gapleft 15, w 20%!");

        // Center Panel
        centerPanel = new JPanel(new MigLayout("insets 0, gap 0 0, align 50% 50%", "", "[190!]"));

        cellImgLabel = new JLabel();

        centerPanel.add(cellImgLabel);

        // Bottom Panel
        bottomPanel = new JPanel(new CardLayout());
        bottomPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#e6e6e6")));

        // Warning Card
        warningCard = new JPanel(new MigLayout("align 50% 50%", "", "[35!]"));

        warningIconLabel = new JLabel();

        warningCard.add(warningIconLabel);

        // Button Card
        buttonCard = new JPanel(new MigLayout("align 50% 50%", "", "[35!]"));

        // Exit Card
        exitCard = new JPanel(new MigLayout("align 50% 50%", "", "[35!]"));

        exitBtn = new MyButton("Exit", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        exitBtn.setIcon(new ImageIcon("img/exitBtnIcon.png"));
        exitBtn.setIconTextGap(8);

        exitCard.add(MyButton.createButtonGroup(new JButton[] {exitBtn}, "#d1d1d1"));

        // Add Cards to BottomPanel
        bottomPanel.add(warningCard, "Warning");
        bottomPanel.add(buttonCard, "Button");
        bottomPanel.add(exitCard, "Exit");

        // Add Panels to CellView
        add(topPanel, "dock north");
        add(centerPanel, "dock center");
        add(bottomPanel, "dock south");

        // Handle Events
        setBgColor("#fcfefc");
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setFlowerDAO(FlowerDAO flowerDAO) {
        this.flowerDAO = flowerDAO;
    }

    public void setBgColor(String bgColor) {
        topPanel.setBackground(Color.decode(bgColor));
        centerPanel.setBackground(Color.decode(bgColor));
        warningCard.setBackground(Color.decode(bgColor));
        buttonCard.setBackground(Color.decode(bgColor));
        exitCard.setBackground(Color.decode(bgColor));
    }

    public void updateFlowerNameLabel() {
        if (cell.getStage() == 0) {
            flowerNameLabel.setText("");
        } else {
            flowerNameLabel.setText(getFlower().getItemName());
        }
    }

    public void updateStageLabel() {
        if (cell.getStage() == 0 || cell.getStage() == 5) {
            stageLabel.setText("");
        } else {
            stageLabel.setText("S" + cell.getStage());
        }
    }

    public void updateTimeBar() {
        switch (cell.getStage()) {
            case 0:
                timeBar.setVisible(false);
                break;
            case 1:
            case 2:
            case 3:
                timeBar.setVisible(true);
                timeBar.setForeground(Color.decode("#aeef18")); // Green
                timeBar.setMinimum(0);
                timeBar.setMaximum(getFlower().getStageTime(cell.getStage()));
                updateTimeBarValue();
                break;
            case 4:
                timeBar.setVisible(true);
                timeBar.setForeground(Color.decode("#ffe51d")); // Yellow
                timeBar.setMinimum(0);
                timeBar.setMaximum(100);
                updateTimeBarValue();
                break;
            case 5:
                timeBar.setVisible(true);
                timeBar.setForeground(Color.decode("#ff1d36")); // Red
                timeBar.setMinimum(0);
                timeBar.setMaximum(100);
                updateTimeBarValue();
                break;
        }
    }

    public void updateTimeBarValue() {
        if (cell.getStage() == 4 || cell.getStage() == 5) {
            timeBar.setValue(100);
        } else {
            timeBar.setValue(cell.getTimeRemaining());
        }
    }

    public void updateTimeLabel() {
        if (cell.getStage() == 0 || cell.getStage() == 4 || cell.getStage() == 5) {
            timeLabel.setText("");
        } else {
            timeLabel.setText(cell.getTimeRemaining() + "s");
        }
    }

    public void updateCellImgLabel() {
        if (cell.getStage() == 0) {
            cellImgLabel.setIcon(new ImageIcon("img/blankCell.png"));
        } else if (cell.getStage() == 5) {
            cellImgLabel.setIcon(new ImageIcon("img/deadCell.png"));
        } else {
            cellImgLabel.setIcon(new ImageIcon("img/" + getFlower().getStageImg(cell.getStage())));
        }
    }

    public void updateWarningCard() {
        switch (cell.getStage()) {
            case 0:
            case 5:
                warningIconLabel.setIcon(new ImageIcon("img/warningIcon.png"));
                break;
            case 4:
                warningIconLabel.setIcon(new ImageIcon("img/tickIcon.png"));
                break;
            case 1:
            case 2:
            case 3:
                if (cell.getStageNeedCurrent().length() != 0) {
                    warningIconLabel.setIcon(new ImageIcon("img/warningIcon.png"));
                } else {
                    warningIconLabel.setIcon(new ImageIcon("img/tickIcon.png"));
                }
                break;
        }
    }

    public void updateButtonCard() {
        buttonCard.removeAll();
        buttonCard.revalidate();
        buttonCard.repaint();
        if (cell.getStage() == 0) {
            ctBtn = new MyButton("Cultivate", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
            ctBtn.setIcon(new ImageIcon("img/ctBtnIcon.png"));
            ctBtn.setIconTextGap(8);

            buttonCard.add(MyButton.createButtonGroup(new JButton[] {ctBtn}, "#d1d1d1"));
        } else if (cell.getStage() == 4) {
            hvBtn = new MyButton("Harvest", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
            hvBtn.setIcon(new ImageIcon("img/hvBtnIcon.png"));
            hvBtn.setIconTextGap(8);

            buttonCard.add(MyButton.createButtonGroup(new JButton[] {hvBtn}, "#d1d1d1"));
        } else if (cell.getStage() == 5) {
            urBtn = new MyButton("Uproot", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
            urBtn.setIcon(new ImageIcon("img/urBtnIcon.png"));
            urBtn.setIconTextGap(8);

            buttonCard.add(MyButton.createButtonGroup(new JButton[] {urBtn}, "#d1d1d1"));
        } else {
            buttonCard.add(MyButton.createButtonGroup(stageNeedBtns(), "#d1d1d1"));
        }
    }

    public JButton[] stageNeedBtns() {
        wtBtn = new MyButton("", 38, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        wtBtn.setIcon(new ImageIcon("img/wtBtnIcon.png"));
        wtBtn.setName("W");

        feBtn = new MyButton("", 38, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        feBtn.setIcon(new ImageIcon("img/feBtnIcon.png"));
        feBtn.setName("F");

        peBtn = new MyButton("", 38, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        peBtn.setIcon(new ImageIcon("img/peBtnIcon.png"));
        peBtn.setName("P");

        JButton[] needBtns = new JButton[] {wtBtn, feBtn, peBtn};
        List<JButton> stageNeedBtns = new ArrayList<>();
        String stageNeed = getFlower().getStageNeed(cell.getStage());
        String stageNeedCurrent = cell.getStageNeedCurrent();

        for (JButton needBtn : needBtns) {
            if (stageNeed.contains(needBtn.getName())) {
                stageNeedBtns.add(needBtn);
            }
        }
        for (JButton stageNeedBtn : stageNeedBtns) {
            if (!stageNeedCurrent.contains(stageNeedBtn.getName())) {
                stageNeedBtn.setEnabled(false);
            }
        }
        return stageNeedBtns.toArray(new JButton[0]);
    }

    public Flower getFlower() {
        return flowerDAO.getFlowerList().stream().filter(e -> cell.getFlowerId().equals(e.getItemId())).findAny()
                .orElse(null);
    }

    public void handleMouseEvent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (cell.getSelected() == 0) {
                    setBgColor("#ffffff");
                    stageLabel.setVisible(true);
                    timeLabel.setVisible(true);
                    ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "Button");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (cell.getSelected() == 0) {
                    if (stillInPanel(e.getPoint())) {
                        return;
                    }
                    setBgColor("#fcfefc");
                    stageLabel.setVisible(false);
                    timeLabel.setVisible(false);
                    ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "Warning");
                }
            }
        });
    }

    private boolean stillInPanel(Point2D p) {
        return this.contains((Point) p);
    }

    public void focusCellView() {
        setBgColor("#ffffff");
        stageLabel.setVisible(true);
        timeLabel.setVisible(true);
        ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "Exit");
    }

    public void exitFocusCellView() {
        setBgColor("#fcfefc");
        stageLabel.setVisible(false);
        timeLabel.setVisible(false);
        ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "Warning");
    }

    // Factory Method Pattern for Get Item Button: ctBtn, feBtn, peBtn
    public JButton getItemBtn(String itemType) {
        if (itemType == null) {
            return null;
        }
        if (itemType.equalsIgnoreCase("FL")) {
            return ctBtn;
        } else if (itemType.equalsIgnoreCase("FE")) {
            return feBtn;
        } else if (itemType.equalsIgnoreCase("PE")) {
            return peBtn;
        }
        return null;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }

    public JButton getWtBtn() {
        return wtBtn;
    }

    public JButton getHvBtn() {
        return hvBtn;
    }

    public JButton getUrBtn() {
        return urBtn;
    }

    public int getCellId() {
        return cellId;
    }

    public Cell getCell() {
        return cell;
    }
}
