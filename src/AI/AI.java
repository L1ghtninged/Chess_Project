package AI;

import java.util.ArrayList;

public class AI {
    public static final int pawnValue = 100;
    public static final int knightValue = 300;
    public static final int bishopValue = 300;
    public static final int rookValue = 500;
    public static final int queenValue = 900;

    public static int evaluate(Board board){
        return countMaterial(board.board);
    }

    public int search(int depth, Board board){
        return 0;
    }

    public static int countMaterial(int[] board){
        int material = 0;


        for(int i : board){
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
