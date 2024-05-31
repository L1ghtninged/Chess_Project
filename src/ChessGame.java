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

    /**
     * AI finds the best move, and plays it.
     * @param depth - AI searches to this depth
     */
    public void playMoveAI(int depth){

        Move move = AI.findBestMove(this, depth);
        if(move != null){
            playMove(move);

        }

    }

    /**
     * Determines, whether the game is a draw
     * @return boolean - true, if the game is a draw
     */
    public boolean isGameDraw(){
        if(countPieces() == 2){
            return true;
        }
        if(doPawnsRemain()) {
            return false;
        }

        return !canCheckmateHappen();
    }

    /**
     * Counts the number of pieces
     * @return int - number of pieces left on the board
     */
    private int countPieces(){
        int numOfPieces = 0;
        for(int i : board.board){
            if(i != Piece.none){
                numOfPieces++;
            }
        }
        return numOfPieces;
    }

    /**
     *
     * @return boolean, true if pawns still remain
     */
    private boolean doPawnsRemain(){

        for(int i : board.board){
            if((i & 0b111) == Piece.pawn){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return boolean, true if material is too low to checkmate
     */
    private boolean canCheckmateHappen(){
        int material = 0;
        for(int i  : board.board){
            material += Offsets.getPieceValue(i & 0b111);
        }
        if(material > 3){
            return true;
        }

        return false;
    }
}
