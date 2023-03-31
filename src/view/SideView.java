package view;

import net.miginfocom.swing.MigLayout;
import swing.MyButton;
import swing.MyOptionPane;

import javax.swing.*;
import java.awt.*;

public abstract class SideView extends JPanel implements AbstractView {
    private static final long serialVersionUID = 1L;
    protected JLabel titleLabel;
    protected JButton helpBtn, hideBtn;
    protected JPanel topPanel, centerPanel, bottomPanel;

    public SideView() {
        init();
    }

    private void init() {
        // Settings for SideView
        setLayout(new MigLayout("insets 0"));

        // Top Panel
        topPanel = new JPanel(new MigLayout("fillx, insets 5 10 5 5", "", "[p!]"));
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#d1d1d1")));

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        helpBtn = new MyButton("?", 25, 25, 14, "#fbfbfb", "#ffffff", "#f5f5f5");
        hideBtn = new MyButton("_", 25, 25, 14, "#fbfbfb", "#ffffff", "#f5f5f5");

        topPanel.add(titleLabel, "left");
        topPanel.add(MyButton.createButtonGroup(new JButton[] {helpBtn, hideBtn}, "#d1d1d1"), "right");

        // Center Panel
        centerPanel = new JPanel();

        // Bottom Panel
        bottomPanel = new JPanel();

        // Add Panels to SideView
        add(topPanel, "dock north");
        add(centerPanel, "dock center");
    }

    protected void setColor(String topPanelColor, String centerPanelColor) {
        topPanel.setBackground(Color.decode(topPanelColor));
        centerPanel.setBackground(Color.decode(centerPanelColor));
    }

    protected void setTitleLabel(String title) {
        titleLabel.setText(title);
    }

    protected void setHelpMessage(String message) {
        helpBtn.addActionListener(e -> MyOptionPane.showMessageDialog(this, message));
    }

    public JButton getHideBtn() {
        return this.hideBtn;
    }
}
