package AI;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    ChessGame game;
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 100;
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

    public SettingsPanel(ChessGame game){
        this.game = game;
        this.setFocusable(true);
        this.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        whitePawn = new ImageIcon("whitePawn.png").getImage();
        whiteKnight = new ImageIcon("whiteKnight.png").getImage();
        whiteBishop = new ImageIcon("whiteBishop.png").getImage();
        whiteRook = new ImageIcon("whiteRook.png").getImage();
        whiteKing = new ImageIcon("whiteKing.png").getImage();
        whiteQueen = new ImageIcon("whiteQueen.png").getImage();
        blackPawn = new ImageIcon("blackPawn.png").getImage();
        blackKnight = new ImageIcon("blackKnight.png").getImage();
        blackBishop = new ImageIcon("blackBishop.png").getImage();
        blackRook = new ImageIcon("blackRook.png").getImage();
        blackKing = new ImageIcon("blackKing.png").getImage();
        blackQueen = new ImageIcon("blackQueen.png").getImage();
    }
    public void updatePromotionImage(){
        repaint();
    }

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
}
