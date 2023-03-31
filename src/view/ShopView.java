package view;

import dao.AbstractDAO;
import entity.AbstractEntity;
import entity.Fertilizer;
import entity.Flower;
import entity.Item;
import entity.Pesticide;
import factory.DAOFactory;
import net.miginfocom.swing.MigLayout;
import swing.MyButton;
import swing.MyScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShopView extends SideView {
    private static final long serialVersionUID = 1L;
    private JButton flBtn, feBtn, peBtn;
    private JPanel centerHeaderPanel, centerBodyPanel, flCard, feCard, peCard;
    private JScrollPane flCardScroll, feCardScroll, peCardScroll;
    private JButton[] tabBtns;
    private List<Entry> flEntries, feEntries, peEntries;

    public ShopView() {
        init();
    }

    private void init() {
        setColor("#eaf5c5", "#f1f9d9");
        setTitleLabel("Shop");
        setHelpMessage("Help Message for ShopView");

        // Center Header Panel
        centerHeaderPanel = new JPanel(new MigLayout("align 50% 50%"));
        centerHeaderPanel.setBackground(Color.decode("#f5fbe6"));
        centerHeaderPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#d1d1d1")));

        flBtn = new MyButton("Flower", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        flBtn.setIcon(new ImageIcon("img/flBtnIcon.png"));
        flBtn.setIconTextGap(8);

        feBtn = new MyButton("Fertilizer", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        feBtn.setIcon(new ImageIcon("img/feBtnIcon.png"));
        feBtn.setIconTextGap(8);

        peBtn = new MyButton("Pesticide", 105, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        peBtn.setIcon(new ImageIcon("img/peBtnIcon.png"));
        peBtn.setIconTextGap(8);

        tabBtns = new JButton[] {flBtn, feBtn, peBtn};

        centerHeaderPanel.add(MyButton.createButtonGroup(tabBtns, "#d1d1d1"), "h p!");

        // Center Body Panel
        centerBodyPanel = new JPanel(new CardLayout());
        centerBodyPanel.setBackground(Color.decode("#f1f9d9"));

        String[] ML = new String[] {"fillx, wrap 2, gap 10 10, insets 10", "[fill]", "[fill, 90!]"};

        flCard = new JPanel(new MigLayout(ML[0], ML[1], ML[2]));
        flCard.setBackground(Color.decode("#f1f9d9"));
        flCardScroll = new MyScrollPane(flCard, "#f1f9d9");

        feCard = new JPanel(new MigLayout(ML[0], ML[1], ML[2]));
        feCard.setBackground(Color.decode("#f1f9d9"));
        feCardScroll = new MyScrollPane(feCard, "#f1f9d9");

        peCard = new JPanel(new MigLayout(ML[0], ML[1], ML[2]));
        peCard.setBackground(Color.decode("#f1f9d9"));
        peCardScroll = new MyScrollPane(peCard, "#f1f9d9");

        centerBodyPanel.add(flCardScroll, "Flower");
        centerBodyPanel.add(feCardScroll, "Fertilizer");
        centerBodyPanel.add(peCardScroll, "Pesticide");

        // Add Panels to Center Panel
        centerPanel.setLayout(new MigLayout("insets 0"));
        centerPanel.add(centerHeaderPanel, "dock north");
        centerPanel.add(centerBodyPanel, "dock center");

        // Handle Events
        handleTabBtnEvent();
        setActivatedTabBtn();
    }

    private void handleTabBtnEvent() {
        for (JButton tabBtn : tabBtns) {
            tabBtn.addActionListener(e -> {
                ((CardLayout) centerBodyPanel.getLayout()).show(centerBodyPanel, tabBtn.getText());
                setActivatedTabBtn();
            });
        }
    }

    private void setActivatedTabBtn() {
        Component[] cardComps = centerBodyPanel.getComponents();
        for (int i = 0; i < cardComps.length; i++) {
            if (cardComps[i].isVisible()) {
                tabBtns[i].setFont(new Font("Segoe UI", Font.BOLD, 13));
            } else {
                tabBtns[i].setFont(new Font("Segoe UI", Font.PLAIN, 13));
            }
        }
    }

    // Factory Method Pattern for Create Entry: Flower, Fertilizer, Pesticide
    public Entry createEntry(Item item) {
        Entry entry = new Entry(item);
        entry.addLevelLabel();
        if (item instanceof Flower) {
            entry.addTimeLabel(((Flower) item).getTime());
            entry.addRevenueLabel(((Flower) item).getRevenue());
        } else if (item instanceof Fertilizer) {
            entry.addPercentLabel(((Fertilizer) item).getPercent());
        } else if (item instanceof Pesticide) {
            entry.addEffectiveLabel(((Pesticide) item).getEffective());
        }
        entry.addBuyBtn();
        return entry;
    }

    public List<Entry> getEntries() {
        return Stream.of(flEntries, feEntries, peEntries).flatMap(Collection::stream).collect(Collectors.toList());
    }

    // Factory Method Pattern for Get Entries: FL, FE, PE
    public List<Entry> getEntries(String itemType) {
        if (itemType == null) {
            return null;
        }
        if (itemType.equalsIgnoreCase("FL")) {
            return flEntries;
        } else if (itemType.equalsIgnoreCase("FE")) {
            return feEntries;
        } else if (itemType.equalsIgnoreCase("PE")) {
            return peEntries;
        }
        return null;
    }

    // Factory Method Pattern for Set Entries: FL, FE, PE
    public void setEntries(String itemType, List<Entry> entries) {
        if (itemType.equalsIgnoreCase("FL")) {
            flEntries = entries;
        } else if (itemType.equalsIgnoreCase("FE")) {
            feEntries = entries;
        } else if (itemType.equalsIgnoreCase("PE")) {
            peEntries = entries;
        }
    }

    // Factory Method Pattern for Get Card: FL, FE, PE
    public JPanel getCard(String itemType) {
        if (itemType == null) {
            return null;
        }
        if (itemType.equalsIgnoreCase("FL")) {
            return flCard;
        } else if (itemType.equalsIgnoreCase("FE")) {
            return feCard;
        } else if (itemType.equalsIgnoreCase("PE")) {
            return peCard;
        }
        return null;
    }

    public void initCard(String[] itemTypes, List<AbstractDAO> listDAO) {
        for (String itemType : itemTypes) {
            List<Entry> entries = new ArrayList<>();
            for (AbstractEntity item : DAOFactory.get(itemType, listDAO).getList()) {
                Entry entry = createEntry((Item) item);
                getCard(itemType).add(entry);
                entries.add(entry);
            }
            setEntries(itemType, entries);
        }
    }

    // Reset Entry Component of Card and Entry Object of List Entries
    public void resetCard(String[] itemTypes) {
        for (String itemType : itemTypes) {
            getCard(itemType).removeAll();
            getCard(itemType).revalidate();
            getCard(itemType).repaint();
            getEntries(itemType).clear();
        }
    }

    // Use in ShopController
    public JButton[] getTabBtns() {
        return tabBtns;
    }
}
