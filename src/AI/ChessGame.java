package AI;

import java.util.ArrayList;

public class ChessGame {

    public ArrayList<Board> boards = new ArrayList<>();
    public Board board;
    public int[] promotionSet;
    public int chosenPromotion;
    public ChessGame(){
        this.board = new Board();
        setUpPromotion();
        board.loadFromFEN(Board.startPosition);
        boards.add(board);
    }
    public ChessGame(String fen){
        this.board = new Board();
        setUpPromotion();
        board.loadFromFEN(fen);
        boards.add(board);
    }
    public void setUpPromotion(){
        promotionSet = new int[4];
        promotionSet[0] = Piece.queen;
        promotionSet[1] = Piece.rook;
        promotionSet[2] = Piece.bishop;
        promotionSet[3] = Piece.knight;
        chosenPromotion = 0;
    }
    public void undoMove(){
        board = boards.get(boards.indexOf(board)-1);
    }
    public void redoMove(){
        board = boards.get(boards.indexOf(board)+1);
    }

    public void playMove(Move move){
        Board b = board.clone();
        b.playMove(move);
        boards.add(b);
        board = b;

    }


}
