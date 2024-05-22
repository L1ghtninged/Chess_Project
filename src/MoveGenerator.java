import java.util.ArrayList;

public class MoveGenerator {

    private static int findKingPosition(int[] board, int color) {
        for (int i = 0; i < board.length; i++) {
            int piece = board[i];
            if ((piece & 0b111) == Piece.king && (piece & (Piece.white | Piece.black)) == color) {
                return i;
            }
        }
        return -1;
    }

    public static boolean leavesKingInCheck(Move move, boolean whiteToMove, Board chessboard) {
        int[] board = chessboard.board;
        int[] tempBoard = board.clone(); // Clone the board to simulate the move

        makeMove(tempBoard, move);

        int kingPos = findKingPosition(tempBoard, whiteToMove ? Piece.white : Piece.black);
        return isSquareAttacked(chessboard, kingPos, !whiteToMove);
    }
    public static boolean isSquareAttacked(Board chessboard, int squareIndex, boolean byWhite) {
        int[] board = chessboard.board;
        ArrayList<Move> opponentMoves = generatePseudoLegalMoves(chessboard, byWhite);

        for (Move move : opponentMoves) {
            if (move.getPositionIndex() == squareIndex) {
                return true;
            }
        }
        return false;
    }
    private static void makeMove(int[] board, Move move) {
        board[move.getPositionIndex()] = board[move.getPieceIndex()];
        board[move.getPieceIndex()] = Piece.none;

    }

    public static ArrayList<Move> generatePseudoLegalMoves(Board chessboard, boolean whiteToMove){
        ArrayList<Move> pseudoMoves = new ArrayList<>();
        int[] board = chessboard.board;

        for (int i = 0; i < board.length; i++) {
            int piece = board[i];
            if (piece == Piece.none) {
                continue;
            }
            int pieceType = piece & 0b111; // Mask to get the piece type (last three bits)
            boolean pieceColor = Piece.getColor(piece);

            // Skip pieces of the opposite color
            if (pieceColor != whiteToMove) {
                continue;
            }

            int x = Main.getFile(i); // File (column) of the piece
            int y = Main.getRank(i); // Rank (row) of the piece

            // Generate moves based on the type of piece
            switch (pieceType) {
                case Piece.pawn -> pseudoMoves.addAll(Piece.getPawnMoves(x, y, chessboard));
                case Piece.knight -> pseudoMoves.addAll(Piece.getKnightMoves(x, y, board));
                case Piece.bishop -> pseudoMoves.addAll(Piece.getBishopMoves(x, y, board));
                case Piece.rook -> pseudoMoves.addAll(Piece.getRookMoves(x, y, board));
                case Piece.queen -> pseudoMoves.addAll(Piece.getQueenMoves(x, y, board));
                case Piece.king -> pseudoMoves.addAll(Piece.getKingMoves(x, y, board));
                default -> {
                }
                // Handle unexpected piece type if necessary
            }
        }

        return pseudoMoves;
    }
    public static ArrayList<Move> generateLegalMoves(boolean whiteToMove, Board chessboard) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        ArrayList<Move> pseudoMoves = generatePseudoLegalMoves(chessboard, whiteToMove);
        for (Move move : pseudoMoves) {

            if (!leavesKingInCheck(move, whiteToMove, chessboard)) {
                legalMoves.add(move);
            }
            else{
                if(move.isEnPassant){
                    System.out.println("En passant is illegal");
                }
            }
        }
        if(whiteToMove){
            if(chessboard.whiteCastlingKing){
                if(checkWhiteCastlingKing(chessboard)){
                    legalMoves.add(new Move(1));
                }
            }
            if(chessboard.whiteCastlingQueen){
                if(checkWhiteCastlingQueen(chessboard)){
                    legalMoves.add(new Move(2));
                }
            }
        }
        else{
            if(chessboard.blackCastlingKing){
                if(checkBlackCastlingKing(chessboard)){
                    legalMoves.add(new Move(1));
                }
            }
            if(chessboard.blackCastlingQueen){
                if(checkBlackCastlingQueen(chessboard)){
                    legalMoves.add(new Move(2));
                }
            }
        }


        return legalMoves;
    }

    public static boolean checkWhiteCastlingKing( Board board){
        if(isSquareAttacked(board, 4, false)|isSquareAttacked(board, 5, false)|isSquareAttacked(board, 6, false)){
            return false;
        }
        if(board.board[5] != Piece.none| board.board[6] != Piece.none){
            return false;
        }
        if(board.board[7] != (Piece.rook | Piece.white)){
            return false;
        }


        return true;
    }
    public static boolean checkWhiteCastlingQueen( Board board){
        if(isSquareAttacked(board, 4, false)|isSquareAttacked(board, 3, false)|isSquareAttacked(board, 2, false)){
            return false;
        }
        if(board.board[3]!=0|board.board[2]!=0|board.board[1]!=0){
            return false;
        }
        if(board.board[0] != (Piece.rook | Piece.white)){
            return false;
        }


        return true;
    }
    // dodělat    black castling
    public static boolean checkBlackCastlingKing(Board board){
        if(isSquareAttacked(board, 60, true)|isSquareAttacked(board, 61, true)|isSquareAttacked(board, 62, true)){
            return false;
        }
        if(board.board[61] != 0| board.board[62] != 0){
            return false;
        }
        if(board.board[63] != (Piece.rook | Piece.black)){
            return false;
        }


        return true;
    }
    public static boolean checkBlackCastlingQueen(Board board){
        if(isSquareAttacked(board, 60, true)|isSquareAttacked(board, 59, true)|isSquareAttacked(board, 58, true)){
            return false;
        }
        if(board.board[59] != 0| board.board[58] != 0 | board.board[57] !=0){
            return false;
        }
        if(board.board[56] != (Piece.rook | Piece.black)){
            return false;
        }


        return true;
    }
}
