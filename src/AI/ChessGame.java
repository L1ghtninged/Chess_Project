package AI;

import java.util.ArrayList;

/**
 * Chess-game class, remembers all positions that occurred
 */
public class ChessGame {
    public boolean aiPlaying = false;
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
    public ChessGame(String fen, boolean aiPlaying){
        this.aiPlaying = aiPlaying;
        this.board = new Board(fen);
        setUpPromotion();
        boards.add(board);
    }
    /**
     * Sets an array with promotion pieces
     */
    public void setUpPromotion(){
        promotionSet = new int[4];
        promotionSet[0] = Piece.queen;
        promotionSet[1] = Piece.rook;
        promotionSet[2] = Piece.bishop;
        promotionSet[3] = Piece.knight;
        chosenPromotion = 0;
    }

    /**
     * Undoes the last move that has been played
     */
    public void undoMove(){
        boards.remove(boards.size()-1);
        board = boards.get(boards.size()-1);
    }

    /**
     * Clones the current board and plays the move
     * @param move - gets played
     */
    public void playMove(Move move){
        Board b = board.clone();
        board.playMove(move);
        boards.add(boards.size()-1, b);


    }

    /**
     * Loads a position from a string
     * @param fen - string, describes a chess position
     */
    public void loadFromFen(String fen){
        this.boards.clear();
        this.board = new Board(fen);
        boards.add(board);
    }

    public void playMoveAI(int depth){

        Move move = AI.findBestMove(this, depth);
        if(move != null){
            playMove(move);

        }

    }


}
