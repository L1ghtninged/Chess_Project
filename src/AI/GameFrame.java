package AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {



    public GameFrame(Board chessBoard){
        GamePanel board = new GamePanel(chessBoard);
        SettingsPanel panel = new SettingsPanel();

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
