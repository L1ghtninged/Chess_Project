import java.util.ArrayList;

/**
 * Helps the AI order its moves, so that it can focus on the more relevant first. It prefers captures,
 * before any other move.
 */
public class MoveOrdering {
    public void sortMoves(ArrayList<Move> moves, Board board){
        ArrayList<Move> captures = new ArrayList<>();
        //ArrayList<Move> checks = new ArrayList<>();
        ArrayList<Move> others = new ArrayList<>();

        for(Move move : moves){
            if(isCapture(move, board)){
                captures.add(move);
            }
            else{
                others.add(move);
            }
        }
        captures.sort((move1, move2)-> getMVVLVA(move2, board) - getMVVLVA(move1, board));
        moves.clear();
        moves.addAll(captures);
        //moves.addAll(checks);
        moves.addAll(others);
    }

    /**
     * Determines whether a piece of higher value can be captured by a piece of lower value
     * @param move - is being examined
     * @param board - chessboard
     * @return a factor of how much the attacking piece is lower than the victim
     */
    public int getMVVLVA(Move move, Board board){
        int victimValue = Offsets.getPieceValue(board.board[move.getPositionIndex()]);
        int attackerValue = Offsets.getPieceValue(board.board[move.getPieceIndex()]);

        return victimValue * 10 - attackerValue;
    }

    /**
     *
     * @param move - chess move
     * @param board - chessboard
     * @return if a move is a capture
     */
    private boolean isCapture(Move move, Board board){
        return board.board[move.getPositionIndex()] != 0;
    }






}
