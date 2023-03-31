package swing;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class MyScrollPane extends JScrollPane {
    private static final long serialVersionUID = 1L;
    private final String bgColor;

    public MyScrollPane(Component view, String bgColor) {
        super(view);
        this.bgColor = bgColor;
        setBackground(Color.decode(bgColor));
        setBorder(BorderFactory.createEmptyBorder());
        getVerticalScrollBar().setUnitIncrement(14);
        getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton(0, 12);
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton(0, 12);
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(Color.decode(bgColor));
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

                if (trackHighlight == DECREASE_HIGHLIGHT) {
                    paintDecreaseHighlight(g);
                } else if (trackHighlight == INCREASE_HIGHLIGHT) {
                    paintIncreaseHighlight(g);
                }
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                ((Graphics2D) g).setRenderingHints(qualityHints);
                if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                    return;
                }
                int w = thumbBounds.width;
                int h = thumbBounds.height;
                g.translate(thumbBounds.x, thumbBounds.y);
                g.setColor(new Color(0f, 0f, 0f, 0.15f));
                g.fillRoundRect(0, 0, w - 2, h - 1, 10, 10);
                g.translate(-thumbBounds.x, -thumbBounds.y);
            }
        });
        getHorizontalScrollBar().setUnitIncrement(14);
        getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 10));
        getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton(12, 0);
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton(12, 0);
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(Color.decode(bgColor));
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

                if (trackHighlight == DECREASE_HIGHLIGHT) {
                    paintDecreaseHighlight(g);
                } else if (trackHighlight == INCREASE_HIGHLIGHT) {
                    paintIncreaseHighlight(g);
                }
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                ((Graphics2D) g).setRenderingHints(qualityHints);
                if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                    return;
                }
                int w = thumbBounds.width;
                int h = thumbBounds.height;
                g.translate(thumbBounds.x, thumbBounds.y);
                g.setColor(new Color(0f, 0f, 0f, 0.15f));
                g.fillRoundRect(0, 0, w - 1, h - 2, 10, 10);
                g.translate(-thumbBounds.x, -thumbBounds.y);
            }
        });
    }

    private JButton createZeroButton(int w, int h) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.decode(bgColor));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(w, h));
        button.setMinimumSize(new Dimension(w, h));
        button.setMaximumSize(new Dimension(w, h));
        return button;
    }
}
