package AI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square extends Rectangle{


    public Square(int x, int y, Graphics g, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.width = GamePanel.SIZE;
        this.height = GamePanel.SIZE;
        draw(g, isWhite);
    }

    public void draw(Graphics g, boolean isWhite) {
        if (isWhite) {
            g.setColor(new Color(253, 234, 217));
        } else {
            g.setColor(new Color(145, 113, 78));
        }
        g.fillRect(x, y, width, height);

    }
}
