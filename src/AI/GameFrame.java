package AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame{



    public GameFrame(ChessGame game){
        SettingsPanel panel = new SettingsPanel(game);
        GamePanel board = new GamePanel(game, panel);

        this.add(panel);
        this.add(board);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setSize(614, 735);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


}
