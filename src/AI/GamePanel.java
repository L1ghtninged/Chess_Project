package AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener, KeyListener {
    ChessGame game;
    SettingsPanel settingsPanel;
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
    public boolean ai = false;
    int targetSquare = -1;

    public GamePanel(ChessGame game, SettingsPanel settings) {
        this.game = game;
        this.settingsPanel = settings;
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);
        this.setBounds(0, SettingsPanel.PANEL_HEIGHT, GAME_WIDTH, GAME_HEIGHT);

        // Load images
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
    public GamePanel(ChessGame game, SettingsPanel settings, boolean ai) {
        this.ai = ai;
        this.game = game;
        this.settingsPanel = settings;
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);
        this.setBounds(0, SettingsPanel.PANEL_HEIGHT, GAME_WIDTH, GAME_HEIGHT);

        // Load images
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
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    public void drawBoard(Graphics g) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                boolean isWhite = (x + y) % 2 == 0;
                if (Main.getIndex(x, (7 - y)) == targetSquare) {
                    new Square(x * SIZE, y * SIZE, g, isWhite, true);
                } else {
                    new Square(x * SIZE, y * SIZE, g, isWhite);
                }
            }
        }
    }

    public void drawPieces(Graphics g) {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                int index = Main.getIndex(x, y);
                switch (game.board.board[index]) {
                    case Piece.rook | Piece.white -> g.drawImage(whiteRook, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.bishop | Piece.white -> g.drawImage(whiteBishop, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.knight | Piece.white -> g.drawImage(whiteKnight, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.king | Piece.white -> g.drawImage(whiteKing, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.queen | Piece.white -> g.drawImage(whiteQueen, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.pawn | Piece.white -> g.drawImage(whitePawn, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.rook | Piece.black -> g.drawImage(blackRook, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.bishop | Piece.black -> g.drawImage(blackBishop, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.knight | Piece.black -> g.drawImage(blackKnight, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.king | Piece.black -> g.drawImage(blackKing, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.queen | Piece.black -> g.drawImage(blackQueen, x * SIZE, (7 - y) * SIZE, null);
                    case Piece.pawn | Piece.black -> g.drawImage(blackPawn, x * SIZE, (7 - y) * SIZE, null);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
    }

    public void movePiece(int startIndex, int endIndex) {
        Move move = new Move(startIndex, endIndex);
        Move m = isMoveLegal(move);
        if(m != null){
            game.playMove(m);
        }
        else{
            targetSquare = -1;
            return;
        }
        targetSquare = -1;
        if(game.aiPlaying){
            game.playMoveAI(4);
        }


        if(MoveGenerator.generateLegalMoves(game.board.isWhiteToMove, game.board).size() == 0){
            if(game.board.isWhiteToMove){
                System.out.println("Black won");
            }
            else{
                System.out.println("White won");
            }
        }
        settingsPanel.updatePromotionImage();
        repaint();
    }

    public void selectedSquare(int x, int y) {
        int color = game.board.isWhiteToMove ? Piece.white : Piece.black;
        int piece = game.board.board[Main.getIndex(x, y)];
        if (targetSquare == Main.getIndex(x, y)) {
            targetSquare = -1;
        } else if ((piece & color) == color) {
            targetSquare = Main.getIndex(x, y);
        } else if (targetSquare != -1) {
            movePiece(targetSquare, Main.getIndex(x, y));
        }
        repaint();
    }
    private Move isMoveLegal(Move move){
        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(game.board.isWhiteToMove, game.board);
        if(moves.contains(move)){
            return move;
        }

        Move enPassantMove = new Move(move.getPieceIndex(), move.getPositionIndex(), true);
        if(moves.contains(enPassantMove)){
            return new Move(move.getPieceIndex(), move.getPositionIndex(), true);
        }
        int piece = game.board.board[move.getPieceIndex()];
        if(piece == (Piece.white | Piece.king)){
            if(move.getPositionIndex() == move.getPieceIndex() + 2){
                if(moves.contains(new Move(1))){
                    return new Move(1);
                }
            }
            if(move.getPositionIndex() == move.getPieceIndex() - 2){
                if(moves.contains(new Move(2))){
                    return new Move(2);
                }
            }
        }
        if(piece == (Piece.black | Piece.king)){
            if(move.getPositionIndex() == move.getPieceIndex() + 2){
                if(moves.contains(new Move(1))){

                    return new Move(1);
                }
            }
            if(move.getPositionIndex() == move.getPieceIndex() - 2){
                if(moves.contains(new Move(2))){
                    return new Move(2);
                }
            }
        }

        int promotion = game.promotionSet[game.chosenPromotion];
        if(moves.contains(new Move(move.getPieceIndex(), move.getPositionIndex(), promotion | Piece.white))){
            return new Move(move.getPieceIndex(), move.getPositionIndex(), promotion | Piece.white);
        }
        if(moves.contains(new Move(move.getPieceIndex(), move.getPositionIndex(), promotion | Piece.black))){
            return new Move(move.getPieceIndex(), move.getPositionIndex(), promotion | Piece.black);
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            int x = e.getX() / SIZE;
            int y = e.getY() / SIZE;
            y = 7 - y;
            selectedSquare(x, y);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*if(e.getKeyCode() == 37){
            if(game.boards.size()>1){
                game.undoMove();
                repaint();
            }
        }

         */
        if(e.getKeyCode() == 38){
            if(game.chosenPromotion == 3){
                game.chosenPromotion = 0;
            }
            else{
                game.chosenPromotion++;
            }
        }
        else if(e.getKeyCode() == 40){
            if(game.chosenPromotion == 0){
                game.chosenPromotion = 3;
            }
            else{
                game.chosenPromotion--;
            }
        }
        System.out.println(game.chosenPromotion);
        settingsPanel.updatePromotionImage();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
