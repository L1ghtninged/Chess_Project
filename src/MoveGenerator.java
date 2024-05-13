import java.util.ArrayList;

public class MoveGenerator {
    private static ArrayList<Move> pseudoMoves = new ArrayList<>();
    private static boolean isWhiteToMove;
    private static boolean isInCheck;
    private static boolean castling;
    private static boolean enPassant;


    public static ArrayList<Move> generatePseudoLegalMoves(){
        pseudoMoves.clear();
        if(isInCheck){

        }


        return pseudoMoves;
    }

}
