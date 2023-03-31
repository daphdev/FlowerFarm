package view;

import net.miginfocom.swing.MigLayout;
import swing.MyButton;
import swing.MyScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LandView extends SideView {
    private static final long serialVersionUID = 1L;
    private JPanel centerSubPanel;
    private JScrollPane centerPanelScroll;
    private List<JButton> plotBtns;

    public LandView() {
        init();
    }

    private void init() {
        setColor("#ddf0db", "#eef8ed");
        setTitleLabel("Land");
        setHelpMessage("Help Message for LandView");

        centerSubPanel = new JPanel(new MigLayout("wrap 3, align 50% 50%, gap 5% 5%, insets 5% 5%"));
        centerSubPanel.setBackground(Color.decode("#eef8ed"));

        centerPanelScroll = new MyScrollPane(centerSubPanel, "#eef8ed");

        centerPanel.setLayout(new MigLayout("insets 0"));
        centerPanel.add(centerPanelScroll, "dock center");
    }

    public void initPlotBtn(int plotNum, int plotNumMax) {
        plotBtns = new ArrayList<>();
        for (int i = 1; i <= plotNumMax; i++) {
            JButton plotBtn = new MyButton("Plot " + i, 180, 120, 20, "#efd0be", "#f4e0d5", "#ecc7b0",
                    "#c56830");
            plotBtn.setName(String.valueOf(i));
            if (i > plotNum) {
                plotBtn.setEnabled(false);
            }
            centerSubPanel.add(plotBtn);
            plotBtns.add(plotBtn);
        }
    }

    public List<JButton> getPlotBtns() {
        return this.plotBtns;
    }
}
