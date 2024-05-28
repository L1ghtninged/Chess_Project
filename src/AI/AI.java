package AI;
import java.util.ArrayList;

public class AI {
    public static final int pawnValue = 100;
    public static final int knightValue = 300;
    public static final int bishopValue = 300;
    public static final int rookValue = 500;
    public static final int queenValue = 900;

    public static int evaluate(Board board) {
        int perspective = board.isWhiteToMove ? 1 : -1;
        int materialScore = countMaterial(board.board);
        return materialScore * perspective;
    }

    public static int search(int depth, int alpha, int beta, Board board) {
        if (depth == 0) {
            return evaluate(board);
        }

        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(board.isWhiteToMove, board);
        if (moves.size() == 0) {
            boolean isWhiteToMove = board.isWhiteToMove;
            boolean isInCheck = MoveGenerator.isSquareAttacked(board, MoveGenerator.findKingPosition(board.board, isWhiteToMove ? Piece.white : Piece.black), !isWhiteToMove);
            if (isInCheck) {
                return Integer.MIN_VALUE + 1;
            }
            return 0;
        }

        System.out.println("Depth: " + depth + ", Alpha: " + alpha + ", Beta: " + beta + ", Number of moves: " + moves.size());

        for (Move move : moves) {
            Board tmp = new Board(board);
            tmp.makeMove(move);
            int evaluation = -search(depth - 1, -beta, -alpha, tmp);
            System.out.println("Evaluating move: " + move + ", Evaluation: " + evaluation);

            if (evaluation >= beta) {
                System.out.println("Beta cutoff for move: " + move + ", Evaluation: " + evaluation);
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
        System.out.println("Finding best move at depth: " + depth);
        System.out.println("Initial alpha: " + alpha + ", Initial beta: " + beta);

        for (Move move : moves) {
            Board tmp = new Board(board);
            tmp.makeMove(move);
            int evaluation = -search(depth - 1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, tmp);
            System.out.println("Move: " + move + ", Evaluation: " + evaluation);

            if (evaluation > bestEvaluation) {
                bestEvaluation = evaluation;
                bestMove = move;
            }
            alpha = Math.max(alpha, evaluation);
        }

        System.out.println("Best move found: " + bestMove + ", evaluation: " + bestEvaluation);
        return bestMove;
    }

    public static int countMaterial(int[] board) {
        int material = 0;
        for (int i : board) {
            int piece = i & 0b111;
            int sign = Piece.getColor(i) ? 1 : -1;
            switch (piece) {
                case Piece.pawn -> material += (pawnValue * sign);
                case Piece.knight -> material += (knightValue * sign);
                case Piece.bishop -> material += (bishopValue * sign);
                case Piece.rook -> material += (rookValue * sign);
                case Piece.queen -> material += (queenValue * sign);
                default -> {
                }
            }
        }
        return material;
    }
}
