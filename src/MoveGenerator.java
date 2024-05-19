import java.util.ArrayList;

public class MoveGenerator {
    private static ArrayList<Move> pseudoMoves = new ArrayList<>();
    private static boolean whiteToMove = true;
    private static boolean isInCheck;
    private static boolean castling;
    private static boolean enPassant;
    private static boolean promotion;


    public static ArrayList<Move> generatePseudoLegalMoves(Board board){
        pseudoMoves.clear();

// Iterate over the board to generate moves
        for (int i = 0; i < board.board.length; i++) {
            int piece = board.board[i];
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
                case Piece.pawn:
                    pseudoMoves.addAll(Piece.getPawnMoves(x, y, board));
                    break;
                case Piece.knight:
                    pseudoMoves.addAll(Piece.getKnightMoves(x, y, board));
                    break;
                case Piece.bishop:
                    pseudoMoves.addAll(Piece.getBishopMoves(x, y, board));
                    break;
                case Piece.rook:
                    pseudoMoves.addAll(Piece.getRookMoves(x, y, board));
                    break;
                case Piece.queen:
                    pseudoMoves.addAll(Piece.getQueenMoves(x, y, board));
                    break;
                case Piece.king:
                    pseudoMoves.addAll(Piece.getKingMoves(x, y, board));
                    break;
                default:
                    // Handle unexpected piece type if necessary
                    break;
            }
        }

        return pseudoMoves;
    }

    public static ArrayList<Move> getPseudoMoves() {
        return pseudoMoves;
    }

    public static void setPseudoMoves(ArrayList<Move> pseudoMoves) {
        MoveGenerator.pseudoMoves = pseudoMoves;
    }

    public static boolean isWhiteToMove() {
        return whiteToMove;
    }

    public static void setWhiteToMove(boolean whiteToMove) {
        MoveGenerator.whiteToMove = whiteToMove;
    }

    public static boolean isIsInCheck() {
        return isInCheck;
    }

    public static void setIsInCheck(boolean isInCheck) {
        MoveGenerator.isInCheck = isInCheck;
    }

    public static boolean isCastling() {
        return castling;
    }

    public static void setCastling(boolean castling) {
        MoveGenerator.castling = castling;
    }

    public static boolean isEnPassant() {
        return enPassant;
    }

    public static void setEnPassant(boolean enPassant) {
        MoveGenerator.enPassant = enPassant;
    }

    public static boolean isPromotion() {
        return promotion;
    }

    public static void setPromotion(boolean promotion) {
        MoveGenerator.promotion = promotion;
    }
}
