package view;

import factory.ViewFactory;
import net.miginfocom.swing.MigLayout;
import swing.MyButton;
import swing.MyScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlotView extends SideView {
    private static final long serialVersionUID = 1L;
    private final int plotId;
    private JButton landBtn;
    private JPanel centerSubPanel;
    private JScrollPane centerPanelScroll;
    private List<JPanel> cellPanels;
    private List<CellView> cellViews;
    private List<JButton> cellLocks;

    public PlotView(int plotId) {
        this.plotId = plotId;
        init();
    }

    private void init() {
        setColor("#eef8ed", "#f8fcf8");
        setTitleLabel("Plot " + plotId);
        setHelpMessage("Help Message for PlotView");

        // Center Panel
        centerSubPanel = new JPanel(new MigLayout("wrap 3, align 50% 50%, gap 20 20, insets 2%"));
        centerSubPanel.setBackground(Color.decode("#f8fcf8"));

        centerPanelScroll = new MyScrollPane(centerSubPanel, "#f8fcf8");

        centerPanel.setLayout(new MigLayout("insets 0"));
        centerPanel.add(centerPanelScroll, "dock center");

        // Bottom Panel
        bottomPanel.setLayout(new MigLayout("insets 5, fill"));
        bottomPanel.setBackground(Color.decode("#f1f9f0"));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#d1d1d1")));

        landBtn = new MyButton("My Land", 100, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5", "#d1d1d1");
        landBtn.setIcon(new ImageIcon("img/landIcon.png"));
        landBtn.setIconTextGap(8);

        bottomPanel.add(landBtn, "right, h p!");

        add(bottomPanel, "dock south");
    }

    public void initCellPanel(int cellNum, int cellNumMax) {
        cellPanels = new ArrayList<>();
        cellViews = new ArrayList<>();
        cellLocks = new ArrayList<>();
        for (int i = 1; i <= cellNumMax; i++) {
            JPanel cellPanel = new JPanel(new MigLayout("insets 0, fill", "[p!]", "[p!]"));
            if (i <= cellNum) {
                CellView cellView = (CellView) ViewFactory.create("Cell", i);
                cellPanel.add(cellView);
                cellViews.add(cellView);
            }
            if (i > cellNum) {
                JButton cellLock = new MyButton("Cell " + String.valueOf(i), 210, 300, 20, "#efd0be", "#f4e0d5",
                        "#ecc7b0", "#c56830");
                cellLock.setName(String.valueOf(i));
                cellLock.setEnabled(false);
                cellPanel.add(cellLock);
                cellLocks.add(cellLock);
            }
            centerSubPanel.add(cellPanel);
            cellPanels.add(cellPanel);
        }
    }

    public void setDisabledCellView() {
        for (CellView cellView : cellViews) {
            cellView.setVisible(cellView.getCell().getSelected() != 0);
        }
        cellLocks.forEach(e -> e.setVisible(false));
    }

    public void setEnabledCellView() {
        cellViews.forEach(e -> e.setVisible(true));
        cellLocks.forEach(e -> e.setVisible(true));
    }

    public int getPlotId() {
        return plotId;
    }

    public JButton getLandBtn() {
        return landBtn;
    }

    public List<JPanel> getCellPanels() {
        return cellPanels;
    }

    public List<JButton> getCellLocks() {
        return cellLocks;
    }

    public List<CellView> getCellViews() {
        return cellViews;
    }
}
