package view;

import entity.Item;
import net.miginfocom.swing.MigLayout;
import swing.MyButton;

import javax.swing.*;
import java.awt.*;

public class Entry extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel itemInfoPanel;
    private JButton actionBtn;
    private final Item item;

    public Entry(Item item) {
        this.item = item;
        init();
    }

    private void init() {
        setLayout(new MigLayout("align 50% 50%, insets 10 15 10 15"));
        setBackground(Color.decode("#ffffff"));
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#dfdfdf")));

        JLabel itemImgLabel = new JLabel();
        itemImgLabel.setIcon(new ImageIcon("img/" + item.getItemImg()));

        JLabel itemNameLabel = new JLabel(item.getItemName());
        itemNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        itemInfoPanel = new JPanel(new MigLayout("insets 0, align 0 50%"));
        itemInfoPanel.setBackground(Color.decode("#ffffff"));

        add(itemImgLabel, "cell 0 0 1 2, grow, gapright 8");
        add(itemNameLabel, "cell 1 0, grow, pushx");
        add(itemInfoPanel, "cell 1 1, grow, pushx");
    }

    public void addLevelLabel() {
        JLabel levelIconLabel = new JLabel();
        levelIconLabel.setIcon(new ImageIcon("img/levelIcon.png"));
        JLabel levelLabel = new JLabel(String.valueOf(item.getLevelUnlock()));
        levelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        itemInfoPanel.add(levelIconLabel);
        itemInfoPanel.add(levelLabel, "gapright 10");
    }

    public void addTimeLabel(int time) {
        JLabel timeIconLabel = new JLabel();
        timeIconLabel.setIcon(new ImageIcon("img/timeIcon.png"));
        JLabel timeLabel = new JLabel(time + "s");
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        itemInfoPanel.add(timeIconLabel);
        itemInfoPanel.add(timeLabel, "gapright 10");
    }

    public void addRevenueLabel(int revenue) {
        JLabel revenueIconLabel = new JLabel();
        revenueIconLabel.setIcon(new ImageIcon("img/revenueIcon.png"));
        JLabel revenueLabel = new JLabel(String.valueOf(revenue));
        revenueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        itemInfoPanel.add(revenueIconLabel);
        itemInfoPanel.add(revenueLabel, "gapright 10");
    }

    public void addPercentLabel(int percent) {
        JLabel percentIconLabel = new JLabel();
        percentIconLabel.setIcon(new ImageIcon("img/percentIcon.png"));
        JLabel percentLabel = new JLabel(percent + "%");
        percentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        itemInfoPanel.add(percentIconLabel);
        itemInfoPanel.add(percentLabel, "gapright 10");
    }

    public void addEffectiveLabel(String effective) {
        JLabel effectiveIconLabel = new JLabel();
        effectiveIconLabel.setIcon(new ImageIcon("img/effectiveIcon.png"));
        JLabel effectiveLabel = new JLabel(effective);
        effectiveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        itemInfoPanel.add(effectiveIconLabel);
        itemInfoPanel.add(effectiveLabel, "gapright 10");
    }

    public void addBuyBtn() {
        actionBtn = new MyButton(String.valueOf(item.getItemPrice()), 60, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5",
                "#d1d1d1");
        actionBtn.setIcon(new ImageIcon("img/buyBtnIcon.png"));
        actionBtn.setIconTextGap(8);

        add(actionBtn, "cell 2 0 1 2, w 60!");
    }

    public void addAmountLabel(int amount) {
        JLabel amountIconLabel = new JLabel();
        amountIconLabel.setIcon(new ImageIcon("img/amountIcon.png"));
        JLabel amountLabel = new JLabel(String.valueOf(amount));
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        itemInfoPanel.add(amountIconLabel);
        itemInfoPanel.add(amountLabel, "gapright 10");
    }

    public void addPickBtn() {
        actionBtn = new MyButton("", 40, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5", "#d1d1d1");
        actionBtn.setIcon(new ImageIcon("img/pickBtnIcon.png"));
        actionBtn.setEnabled(false);

        add(actionBtn, "cell 2 0 1 2, w 40!");
    }

    public JButton getActionBtn() {
        return actionBtn;
    }

    public Item getItem() {
        return item;
    }
}
