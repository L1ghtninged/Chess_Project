import javax.swing.*;
import java.awt.*;

/**
 * Main GUI class
 */
public class GameFrame extends JFrame{
    SettingsPanel panel;
    GamePanel board;

    public GameFrame(ChessGame game){
        board = new GamePanel(game, panel);
        panel = new SettingsPanel(game, board);
        board.settingsPanel = panel;

        this.add(panel);
        this.add(board);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setSize(614, 735);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Chess");

    }
    public GameFrame(){
        ChessGame game = new ChessGame(Board.startPosition);
        board = new GamePanel(game, panel);
        panel = new SettingsPanel(game, board);
        board.settingsPanel = panel;

        this.add(panel);
        this.add(board);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setSize(614, 735);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Chess");

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }


}
