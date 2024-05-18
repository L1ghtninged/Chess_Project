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
        int color = Piece.black;
        if(whiteToMove){
            color = Piece.white;
        }

        for(int i = 0; i < board.board.length; i++){
            int piece = board.board[i];
            if(piece == Piece.none){
                break;
            }
            if(piece == (Piece.pawn | color)){
                pseudoMoves.addAll(Piece.getPawnMoves(Main.getFile(i), Main.getRank(i), board));

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
