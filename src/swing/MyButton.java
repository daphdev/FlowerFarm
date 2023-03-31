package swing;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private static final long serialVersionUID = 1L;

    public MyButton(String label, int width, int height, int fontSize, String normalColor, String rolloverColor,
                    String pressedColor) {
        super(label);
        setPreferredSize(new Dimension(width, height));
        setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
        setBackgroundColor(normalColor, rolloverColor, pressedColor);
        setBorder(BorderFactory.createEmptyBorder());
    }

    public MyButton(String label, int width, int height, int fontSize, String normalColor, String rolloverColor,
                    String pressedColor, String borderColor) {
        this(label, width, height, fontSize, normalColor, rolloverColor, pressedColor);
        setBorder(BorderFactory.createLineBorder(Color.decode(borderColor)));
    }

    private void setBackgroundColor(String normalColor, String rolloverColor, String pressedColor) {
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(Color.decode(normalColor));
        addChangeListener(e -> {
            if (getModel().isPressed()) {
                setBackground(Color.decode(pressedColor));
            } else if (getModel().isRollover()) {
                setBackground(Color.decode(rolloverColor));
            } else {
                setBackground(Color.decode(normalColor));
            }
        });
    }

    public static JPanel createButtonGroup(JButton[] buttons, String borderColor) {
        JPanel btnGroupPanel = new JPanel(new GridLayout(1, buttons.length, 1, 0));
        btnGroupPanel.setBackground(Color.decode(borderColor));
        btnGroupPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        for (JButton btn : buttons) {
            btnGroupPanel.add(btn);
        }
        return btnGroupPanel;
    }
}
