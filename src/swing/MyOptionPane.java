package swing;

import javax.swing.*;
import java.awt.*;

public class MyOptionPane {

    public static void showMessageDialog(Component parentComponent, Object message) {
        JButton ok = customBtn("OK");
        showOptionDialog(parentComponent, message, "Message", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new Object[] {ok}, ok);
    }

    public static int showConfirmDialog(Component parentComponent, Object message, String title) {
        JButton yes = customBtn("Yes");
        JButton no = customBtn("No");
        return showOptionDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[] {yes, no}, no);
    }

    // Phuong thuc dinh dang chung toan bo JDialog
    private static int showOptionDialog(Component parentComponent, Object message, String title, int optionType,
                                        int messageType, Icon icon, Object[] options, Object initialValue) {
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options,
                initialValue);
    }

    // Phuong thuc tao Button cho JOptionPane
    private static JButton customBtn(String name) {
        JButton customBtn = new MyButton(name, 55, 30, 12, "#fbfbfb", "#ffffff", "#f5f5f5", "#d1d1d1");
        customBtn.addActionListener(e -> {
            JOptionPane pane = getOptionPane((JComponent) e.getSource());
            pane.setValue(customBtn);
        });
        return customBtn;
    }

    // Lay ra JOptionPane dang chua Button tuong ung
    private static JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent) parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }

}
