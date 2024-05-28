package AI;
import java.util.ArrayList;

public class AI {
    public static final MoveOrdering ordering = new MoveOrdering();
    public static int positions = 0;
    public static int evaluate(Board board) {
        int perspective = board.isWhiteToMove ? 1 : -1;
        int eval = countValue(board.board);
        return eval * perspective;
    }

    public static int search(int depth, int alpha, int beta, Board board) {
        if (depth == 0) {
            positions++;
            return evaluate(board);
        }

        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(board.isWhiteToMove, board);
        ordering.sortMoves(moves, board);
        if (moves.size() == 0) {
            boolean isWhiteToMove = board.isWhiteToMove;
            boolean isInCheck = MoveGenerator.isSquareAttacked(board, MoveGenerator.findKingPosition(board.board, isWhiteToMove ? Piece.white : Piece.black), !isWhiteToMove);
            if (isInCheck) {
                return Integer.MIN_VALUE+1;
            }
            return 0;
        }

        for (Move move : moves) {
            Board tmp = new Board(board);
            tmp.makeMove(move);
            int evaluation = -search(depth - 1, -beta, -alpha, tmp);

            if (evaluation >= beta) {
                return beta;
            }
            alpha = Math.max(alpha, evaluation);
        }
        return alpha;
    }

    public static Move findBestMove(Board board, int depth) {
        Move bestMove = null;
        int bestEvaluation = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(board.isWhiteToMove, board);

        for (Move move : moves) {
            Board tmp = new Board(board);
            tmp.makeMove(move);
            int evaluation = -search(depth - 1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, tmp);

            if (evaluation > bestEvaluation) {
                bestEvaluation = evaluation;
                bestMove = move;
            }
            alpha = Math.max(alpha, evaluation);
        }
        System.out.println(positions);
        positions = 0;
        return bestMove;
    }

    public static int countValue(int[] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            int piece = board[i];
            int sign = Piece.getColor(piece) ? 1 : -1;
            count += ((Offsets.getPieceValue(piece) + Offsets.getPositionValue(piece, i))) * sign;
        }
        return count;
    }
    public static int countMaterial(int[] board){
        int material = 0;
        for(int piece : board){
            int sign = Piece.getColor(piece) ? 1 : -1;
            material += Offsets.getPieceValue(piece) * sign;
        }
        return material;
    }

}
