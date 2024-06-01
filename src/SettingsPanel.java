import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Settings panel for the game.
 */
public class SettingsPanel extends JPanel implements ActionListener {
    ChessGame game;
    GamePanel board;
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 100;
    public static final int BUTTON_WIDTH = 150;
    public static final int BUTTON_HEIGHT = 85;
    public static final int GAP = 10;
    Image whitePawn;
    Image whiteKnight;
    Image whiteBishop;
    Image whiteRook;
    Image whiteQueen;
    Image whiteKing;
    Image blackPawn;
    Image blackKnight;
    Image blackBishop;
    Image blackRook;
    Image blackQueen;
    Image blackKing;
    JButton newGameAI;
    JButton newGame;
    JLabel result;

    public SettingsPanel(ChessGame game, GamePanel board){
        this.board = board;
        this.game = game;
        this.setFocusable(true);
        this.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        // Images from: https://commons.wikimedia.org/wiki/Category:SVG_chess_pieces
        whitePawn = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("whitePawn.png"))).getImage();
        whiteKnight = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("whiteKnight.png"))).getImage();
        whiteBishop = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("whiteBishop.png"))).getImage();
        whiteRook = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("whiteRook.png"))).getImage();
        whiteKing = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("whiteKing.png"))).getImage();
        whiteQueen = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("whiteQueen.png"))).getImage();
        blackPawn = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("blackPawn.png"))).getImage();
        blackKnight = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("blackKnight.png"))).getImage();
        blackBishop = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("blackBishop.png"))).getImage();
        blackRook = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("blackRook.png"))).getImage();
        blackKing = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("blackKing.png"))).getImage();
        blackQueen = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("blackQueen.png"))).getImage();

        newGameAI = new JButton();
        newGameAI.setFocusable(false);
        newGameAI.setBounds(100, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        newGameAI.setVisible(true);
        newGameAI.setText("NEW GAME AI");
        newGameAI.addActionListener(this);

        newGame = new JButton();
        newGame.setFocusable(false);
        newGame.setBounds(newGameAI.getX() + BUTTON_WIDTH + GAP, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        newGame.setVisible(true);
        newGame.setText("NEW GAME");
        newGame.addActionListener(this);

        result = new JLabel("Draw");
        result.setVisible(false);
        result.setBounds(newGame.getX() + BUTTON_WIDTH, 20, 400, 50);
        result.setFont(new Font("MV Boli", Font.BOLD, 30));
        this.add(newGameAI);
        this.add(newGame);
        this.add(result);
        this.setLayout(null);
    }

    /**
     * Calls the method repaint for this panel
     */
    public void updatePromotionImage(){
        repaint();
    }

    /**
     * Behaves like the paint method
     * To call this method - repaint()
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch ((game.promotionSet[game.chosenPromotion]) | (game.board.isWhiteToMove ? Piece.white : Piece.black)) {
            case Piece.rook | Piece.white -> g.drawImage(whiteRook, 0, 0, null);
            case Piece.bishop | Piece.white -> g.drawImage(whiteBishop, 0, 0, null);
            case Piece.knight | Piece.white -> g.drawImage(whiteKnight, 0, 0,null);
            case Piece.king | Piece.white -> g.drawImage(whiteKing, 0, 0,null);
            case Piece.queen | Piece.white -> g.drawImage(whiteQueen, 0, 0, null);
            case Piece.pawn | Piece.white -> g.drawImage(whitePawn, 0, 0,null);
            case Piece.rook | Piece.black -> g.drawImage(blackRook, 0, 0,null);
            case Piece.bishop | Piece.black -> g.drawImage(blackBishop, 0, 0,null);
            case Piece.knight | Piece.black -> g.drawImage(blackKnight, 0, 0,null);
            case Piece.king | Piece.black -> g.drawImage(blackKing, 0,0,null);
            case Piece.queen | Piece.black -> g.drawImage(blackQueen, 0, 0, null);
            case Piece.pawn | Piece.black -> g.drawImage(blackPawn, 0, 0, null);
        }
    }

    /**
     * Loads a new game when a button is clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == newGameAI){
            game = new ChessGame(Board.startPosition, true);
            board.game = game;
            board.repaint();
        }
        else if(e.getSource() == newGame){
            game = new ChessGame(Board.startPosition, false);
            board.game = game;

            board.repaint();
        }
        result.setVisible(false);
        board.gameOver = false;
        updatePromotionImage();
    }

    /**
     * Displays the result of the game
     * @param res
     */
    public void changeResultLabel(int res){
        if(res == 0){
            result.setText("Draw");
        }
        else if(res == 1){
            result.setText("White won");
        }
        else if(res == 2){
            result.setText("Black won");
        }
        result.setVisible(true);

    }


}
