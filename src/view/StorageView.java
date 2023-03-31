package view;

import dao.AbstractDAO;
import dao.ItemDAO;
import dao.StorageDAO;
import entity.Item;
import entity.Storage;
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

public class StorageView extends SideView {
    private static final long serialVersionUID = 1L;
    private JLabel flPickLabel, fePickLabel, pePickLabel;
    private JButton flBtn, feBtn, peBtn;
    private JPanel centerHeaderPanel, centerBodyPanel, flCard, feCard, peCard, tabBtnPanel, flPick, fePick, pePick;
    private JScrollPane flCardScroll, feCardScroll, peCardScroll;
    private JButton[] tabBtns;
    private List<Entry> flEntries, feEntries, peEntries;

    public StorageView() {
        init();
    }

    private void init() {
        setColor("#e8f2fe", "#edf7fe");
        setTitleLabel("Storage");
        setHelpMessage("Help Message for StorageView");

        // Center Header Panel
        centerHeaderPanel = new JPanel(new CardLayout());
        centerHeaderPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#d1d1d1")));

        // tabBtnPanel
        tabBtnPanel = new JPanel(new MigLayout("align 50% 50%"));
        tabBtnPanel.setBackground(Color.decode("#f2f9ff"));

        flBtn = new MyButton("FL", 70, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        flBtn.setIcon(new ImageIcon("img/flBtnIcon.png"));
        flBtn.setIconTextGap(8);

        feBtn = new MyButton("FE", 70, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        feBtn.setIcon(new ImageIcon("img/feBtnIcon.png"));
        feBtn.setIconTextGap(8);

        peBtn = new MyButton("PE", 70, 32, 13, "#fbfbfb", "#ffffff", "#f5f5f5");
        peBtn.setIcon(new ImageIcon("img/peBtnIcon.png"));
        peBtn.setIconTextGap(8);

        tabBtns = new JButton[] {flBtn, feBtn, peBtn};

        tabBtnPanel.add(MyButton.createButtonGroup(tabBtns, "#d1d1d1"), "h p!");

        // flPick
        flPick = new JPanel(new MigLayout("align 50% 50%"));
        flPick.setBackground(Color.decode("#f2f9ff"));

        flPickLabel = new JLabel("Pick a Flower");
        flPickLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));

        flPick.add(flPickLabel);

        // fePick
        fePick = new JPanel(new MigLayout("align 50% 50%"));
        fePick.setBackground(Color.decode("#f2f9ff"));

        fePickLabel = new JLabel("Pick a Fertilizer");
        fePickLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));

        fePick.add(fePickLabel);

        // pePick
        pePick = new JPanel(new MigLayout("align 50% 50%"));
        pePick.setBackground(Color.decode("#f2f9ff"));

        pePickLabel = new JLabel("Pick a Pesticide");
        pePickLabel.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 18));

        pePick.add(pePickLabel);

        centerHeaderPanel.add(tabBtnPanel, "tabBtnPanel");
        centerHeaderPanel.add(flPick, "FL");
        centerHeaderPanel.add(fePick, "FE");
        centerHeaderPanel.add(pePick, "PE");

        // Center Body Panel
        centerBodyPanel = new JPanel(new CardLayout());
        centerBodyPanel.setBackground(Color.decode("#edf7fe"));

        String[] ML = new String[] {"fillx, wrap 1, gap 10 10, insets 10", "[fill]", "[fill, 90!]"};

        flCard = new JPanel(new MigLayout(ML[0], ML[1], ML[2]));
        flCard.setBackground(Color.decode("#edf7fe"));
        flCardScroll = new MyScrollPane(flCard, "#edf7fe");

        feCard = new JPanel(new MigLayout(ML[0], ML[1], ML[2]));
        feCard.setBackground(Color.decode("#edf7fe"));
        feCardScroll = new MyScrollPane(feCard, "#edf7fe");

        peCard = new JPanel(new MigLayout(ML[0], ML[1], ML[2]));
        peCard.setBackground(Color.decode("#edf7fe"));
        peCardScroll = new MyScrollPane(peCard, "#edf7fe");

        centerBodyPanel.add(flCardScroll, "FL");
        centerBodyPanel.add(feCardScroll, "FE");
        centerBodyPanel.add(peCardScroll, "PE");

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

    public void setActivatedTabBtn() {
        Component[] cardComps = centerBodyPanel.getComponents();
        for (int i = 0; i < cardComps.length; i++) {
            if (cardComps[i].isVisible()) {
                tabBtns[i].setFont(new Font("Segoe UI", Font.BOLD, 13));
            } else {
                tabBtns[i].setFont(new Font("Segoe UI", Font.PLAIN, 13));
            }
        }
    }

    public Entry createEntry(Item item, int amount) {
        Entry entry = new Entry(item);
        entry.addAmountLabel(amount);
        entry.addPickBtn();
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

    public void initCard(String[] itemTypes, StorageDAO storageDAO, List<AbstractDAO> listDAO) {
        for (String itemType : itemTypes) {
            List<Entry> entries = new ArrayList<>();
            for (Storage s : storageDAO.getCastedList()) {
                if (s.getItemId().startsWith(itemType)) {
                    ItemDAO itemDAO = (ItemDAO) DAOFactory.get(itemType, listDAO);
                    Item item = itemDAO.getCastedList().stream().filter(e -> s.getItemId().equals(e.getItemId()))
                            .findAny().orElse(null);
                    Entry entry = createEntry(item, s.getAmount());
                    getCard(itemType).add(entry);
                    entries.add(entry);
                }
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

    // Use in ShopController & CellController
    public void showCard(String cardName) {
        ((CardLayout) centerBodyPanel.getLayout()).show(centerBodyPanel, cardName);
        setActivatedTabBtn();
    }

    // Use in CellController
    public void showPick(String cardName) {
        ((CardLayout) centerHeaderPanel.getLayout()).show(centerHeaderPanel, cardName);
    }
}
