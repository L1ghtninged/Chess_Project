package AI;
import java.util.ArrayList;

/**
 * Main AI class
 */
public class AI {
    public static final MoveOrdering ordering = new MoveOrdering();
    public static final int positiveInfinity = 100000;
    public static final int negativeInfinity = -positiveInfinity;

    /**
     *
     * @param chessboard - evaluated chessboard
     * @return evaluation of a position with no looking ahead.
     */
    public static int evaluate(Board chessboard) {
        int eval = 0;
        int[] board = chessboard.board;
        int friendlyKingIndex = MoveGenerator.findKingPosition(chessboard.board, chessboard.isWhiteToMove ? Piece.white : Piece.black);
        int opponentKingIndex = MoveGenerator.findKingPosition(chessboard.board, chessboard.isWhiteToMove ? Piece.black : Piece.white);
        int numOfPieces = 0;
        int endgameValue = 0;
        for(int i = 0; i < board.length; i++){
            int piece = board[i];
            if(piece == Piece.none){
                continue;
            }
            else{
                numOfPieces ++;
            }
             if((piece & 0b111) == Piece.pawn){

                endgameValue+=1;
            }
            else if((piece & 0b111) == Piece.knight || (piece & 0b111) == Piece.bishop || (piece & 0b111) == Piece.rook){
                endgameValue += 2;
            }
            else if((piece & 0b111) == Piece.queen){
                endgameValue += 6;
            }
            else{
                endgameValue ++;
            }
            int sign = Piece.getColor(piece) ? 1 : -1;
            eval += sign * (Offsets.getPieceValue(piece) + Offsets.getPositionValue(piece, i));

        }
         if(endgameValue < 14){
            eval = 0;
            for(int i = 0; i < board.length; i++){
                int piece = board[i];
                if(piece == Piece.none){
                    continue;
                }
                numOfPieces++;
                int sign = Piece.getColor(piece) ? 1 : -1;
                eval += sign * (Offsets.getPieceValue(piece) + Offsets.getEndgamePositionValue(piece, i));

            }
        }
        double endgameWeight = calculateEndgameWeight(numOfPieces);

        eval += forceKingIntoCornerEval(friendlyKingIndex, opponentKingIndex, endgameWeight);

        int perspective = chessboard.isWhiteToMove ? 1 : -1;
        return eval * perspective;
    }

    /**
     * Recursive function, searches and evaluates moves into the future
     * Uses "alpha-beta pruning"
     * @param depth
     * @param alpha - The best evaluation
     * @param beta - Worst evaluation
     * @param board - Board, which is being evaluated
     * @return returns alpha
     */
    public static int search(int depth, int alpha, int beta, Board board) {
        if (depth == 0) {
            return evaluate(board);
        }

        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(board.isWhiteToMove, board);
        ordering.sortMoves(moves, board);


        for (Move move : moves) {
            Board tmp = new Board(board);
            tmp.makeMove(move);
            int evaluation = -search(depth - 1, -beta, -alpha, tmp);

            if (evaluation >= beta) {
                return beta;
            }
            alpha = Math.max(alpha, evaluation);
        }
        if (moves.size() == 0) {
            boolean isWhiteToMove = board.isWhiteToMove;
            boolean isInCheck = MoveGenerator.isSquareAttacked(board, MoveGenerator.findKingPosition(board.board, isWhiteToMove ? Piece.white : Piece.black), !isWhiteToMove);
            if (isInCheck) { //Checkmate
                return negativeInfinity - depth;
            }
            return 0;  //Stalemate
        }
        return alpha;
    }

    /**
     * Uses the search function to evaluate all legal moves
     * @param game - The game, which is being played
     * @param depth - Search function searches to this depth
     * @return Best move
     */
    public static Move findBestMove(ChessGame game, int depth) {
        Board board = game.board;
        Move bestMove = null;
        int bestEvaluation = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(board.isWhiteToMove, board);
        int numOfRepetitions;
        for (Move move : moves) {
            Board tmp = new Board(board);
            tmp.makeMove(move);
            numOfRepetitions = 0;
            for(Board b: game.boards){
                if(b.equals(tmp)){
                    numOfRepetitions++;
                }
            }
            int evaluation = 0;

            if(numOfRepetitions != 3){ // Repeating a position 3 times is a draw.
                evaluation = -search(depth - 1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, tmp);
                if(move.getCastling() > 0){
                    evaluation += 50;
                }
            }

            if (evaluation > bestEvaluation) {
                bestEvaluation = evaluation;
                bestMove = move;
            }
            alpha = Math.max(alpha, evaluation);
        }
        return bestMove;
    }

    /**
     * https://github.com/SebLague/Chess-Coding-Adventure/tree/Chess-V1-Unity
     * @param friendlyKingIndex - Index of the friendly king
     * @param opponentKingIndex - Index of the opponents king
     * @param endGameWeight - Final evaluation is being multiplied by this
     * @return evaluation of a restricted king
     */
    public static int forceKingIntoCornerEval(int friendlyKingIndex, int opponentKingIndex, double endGameWeight){
        int evaluation = 0;
        int opponentKingRank = Main.getRank(opponentKingIndex);
        int opponentKingFile = Main.getFile(opponentKingIndex);

        int opponentKingDistanceFromCentreFile = Math.max(3 - opponentKingFile, opponentKingFile - 4);
        int opponentKingDistanceFromCentreRank = Math.max(3 - opponentKingRank, opponentKingRank - 4);
        int opponentKingDistanceFromCentre = opponentKingDistanceFromCentreFile + opponentKingDistanceFromCentreRank;
        evaluation+=opponentKingDistanceFromCentre;

        int friendlyKingRank = Main.getRank(friendlyKingIndex);
        int friendlyKingFile = Main.getFile(friendlyKingIndex);

        int distanceBetweenKingsRank = Math.abs(friendlyKingRank - opponentKingRank);
        int distanceBetweenKingsFile = Math.abs(friendlyKingFile - opponentKingFile);
        int distanceBetweenKings = distanceBetweenKingsFile + distanceBetweenKingsRank;
        evaluation += (14 - distanceBetweenKings);


        return (int)(evaluation * endGameWeight);
    }

    /**
     * Calculates, how much a position is nearing the endgame
     * @param pieceCount - endgame thresh-hold is dependant on the number of pieces
     * @return double
     */
    private static double calculateEndgameWeight(int pieceCount){
        int endgameThreshHold = 32 / 2;
        return Math.max(0, Math.min(1, + 1.0 * (endgameThreshHold - pieceCount) / endgameThreshHold));
    }

}
