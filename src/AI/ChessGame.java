package AI;

import java.util.ArrayList;

public class ChessGame {

    public ArrayList<Board> boards = new ArrayList<>();
    public Board board;

    public ChessGame(){
        this.board = new Board();
        board.loadFromFEN(Board.startPosition);
        boards.add(board);
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
