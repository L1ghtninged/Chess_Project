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
        boards.add(board);
    }
    public ChessGame(String fen){
        this.board = new Board(fen);
        setUpPromotion();
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
        boards.remove(boards.size()-1);
        board = boards.get(boards.size()-1);
    }
    public void redoMove(){
        board = boards.get(boards.indexOf(board)+1);
    }

    public void playMove(Move move){
        Board b = board.clone();
        board.playMove(move);
        boards.add(boards.size()-1, b);


    }


}
