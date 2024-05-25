package AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLOutput;

public class GamePanel extends JPanel implements MouseListener {
    Board board;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 600;
    public static final int SIZE = 75;
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

    int targetSquare = -1;

    public GamePanel(Board chessboard) {
        this.addMouseListener(this);
        this.board = chessboard;
        this.setFocusable(true);
        this.setBounds(0, SettingsPanel.PANEL_HEIGHT, GAME_WIDTH, GAME_HEIGHT);
        whitePawn = new ImageIcon("whitePawn.png").getImage();
        whiteKnight = new ImageIcon("whiteKnight.png").getImage();
        whiteBishop = new ImageIcon("whiteBishop.png").getImage();
        whiteRook= new ImageIcon("whiteRook.png").getImage();
        whiteKing = new ImageIcon("whiteKing.png").getImage();
        whiteQueen = new ImageIcon("whiteQueen.png").getImage();
        blackPawn = new ImageIcon("blackPawn.png").getImage();
        blackKnight = new ImageIcon("blackKnight.png").getImage();
        blackBishop = new ImageIcon("blackBishop.png").getImage();
        blackRook= new ImageIcon("blackRook.png").getImage();
        blackKing = new ImageIcon("blackKing.png").getImage();
        blackQueen = new ImageIcon("blackQueen.png").getImage();
    }


    public void drawBoard(Graphics g){
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                boolean isWhite = (x+y)%2==0;

                new Square(x*SIZE, y*SIZE, g, isWhite);
            }
        }


    }
    public void drawPieces(Graphics g){
        for(int y = 7; y >= 0; y--){
            for(int x = 0; x < 8; x++){
                int index = Main.getIndex(x, y);

                switch (board.board[index]) {
                    case Piece.rook | Piece.white -> g.drawImage(whiteRook, x * SIZE, (7-y) * SIZE, null);
                    case Piece.bishop | Piece.white -> g.drawImage(whiteBishop, x * SIZE, (7-y) * SIZE, null);
                    case Piece.knight | Piece.white -> g.drawImage(whiteKnight, x * SIZE, (7-y) * SIZE, null);
                    case Piece.king | Piece.white -> g.drawImage(whiteKing, x * SIZE, (7-y) * SIZE, null);
                    case Piece.queen | Piece.white -> g.drawImage(whiteQueen, x * SIZE, (7-y) * SIZE, null);
                    case Piece.pawn | Piece.white -> g.drawImage(whitePawn, x * SIZE, (7-y) * SIZE, null);

                    case Piece.rook | Piece.black -> g.drawImage(blackRook, x * SIZE, (7-y) * SIZE, null);
                    case Piece.bishop | Piece.black -> g.drawImage(blackBishop, x * SIZE, (7-y) * SIZE, null);
                    case Piece.knight | Piece.black -> g.drawImage(blackKnight, x * SIZE, (7-y) * SIZE, null);
                    case Piece.king | Piece.black -> g.drawImage(blackKing, x * SIZE, (7-y) * SIZE, null);
                    case Piece.queen | Piece.black -> g.drawImage(blackQueen, x * SIZE, (7-y) * SIZE, null);
                    case Piece.pawn | Piece.black -> g.drawImage(blackPawn, x * SIZE, (7-y) * SIZE, null);

                }
            }
        }
    }
    public void paint(Graphics g){
        drawBoard(g);
        drawPieces(g);

    }

    public void movePiece(int startIndex, int endIndex){
        try{
            board.playMove(new Move(startIndex, endIndex));

        }catch(IllegalArgumentException e){
            try{
                board.playMove(new Move(startIndex, endIndex, true));

            }catch(IllegalArgumentException exception){

            }
            if((board.board[startIndex] & 0b00111) == Piece.king && (endIndex == startIndex + 2)){
                try{

                    board.playMove(new Move(1));
                }catch (IllegalArgumentException exception){

                }

            }
            else if((board.board[startIndex] & 0b00111) == Piece.king && (endIndex == startIndex - 2)){
                try{
                    board.playMove(new Move(2));
                }catch (IllegalArgumentException exception){

                }

            }
            else if(Main.getRank(endIndex) == 7 && board.board[startIndex] == (Piece.pawn | Piece.white)){
                try{
                    board.playMove(new Move(startIndex, endIndex, Piece.queen | Piece.white));
                }catch (IllegalArgumentException exception){

                }
            }
            else if(Main.getRank(endIndex) == 0 && board.board[startIndex] == (Piece.pawn | Piece.black)){
                try{
                    board.playMove(new Move(startIndex, endIndex, Piece.queen | Piece.black));
                }catch (IllegalArgumentException exception){

                }
            }
        }



        targetSquare = -1;
        repaint();
    }
    public void selectedSquare(int x, int y){
        int color = board.isWhiteToMove ? Piece.white : Piece.black;
        int piece = board.board[Main.getIndex(x, y)];
        if(targetSquare == Main.getIndex(x,y)){
            targetSquare = -1;
        }
        else if((piece & color) == color){
            targetSquare = Main.getIndex(x, y);
        }
        else if(targetSquare != -1){
            movePiece(targetSquare, Main.getIndex(x,y));
        }
        System.out.println("Target square "+targetSquare);
        System.out.println("Piece "+piece);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / SIZE;
        int y = e.getY() / SIZE;
        y = 7 - y;
        selectedSquare(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
