package AI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Graphical square
 */
public class Square extends Rectangle{

    boolean isTargetSquare = false;
    public Square(int x, int y, Graphics g, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.width = GamePanel.SIZE;
        this.height = GamePanel.SIZE;
        draw(g, isWhite);
    }
    public Square(int x, int y, Graphics g, boolean isWhite, boolean isTargetSquare){
        this.x = x;
        this.y = y;
        this.width = GamePanel.SIZE;
        this.height = GamePanel.SIZE;
        this.isTargetSquare = isTargetSquare;
        drawColor(g, isWhite);
    }

    /**
     * Draws this square
     * @param g
     * @param isWhite
     */
    public void draw(Graphics g, boolean isWhite) {
        if (isWhite) {
            g.setColor(new Color(253, 234, 217));
        } else {
            g.setColor(new Color(145, 113, 78));
        }
        g.fillRect(x, y, width, height);

    }
    // Draws this square highlighted
    public void drawColor(Graphics g, boolean isWhite){
        if (isWhite) {
            g.setColor(new Color(229, 252, 116));
        } else {
            g.setColor(new Color(153, 169, 81));
        }
        g.fillRect(x, y, width, height);
    }
}
