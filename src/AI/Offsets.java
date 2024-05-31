package AI;

public class Offsets {
    public static final int pawnValue = 100;
    public static final int knightValue = 300;
    public static final int bishopValue = 300;
    public static final int rookValue = 500;
    public static final int queenValue = 900;
    public static final int[][] KNIGHT_MOVES = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
    public static final int[][] BISHOP_DIRECTIONS = {
            {-1, -1}, // up-left
            {-1, 1},  // up-right
            {1, -1},  // down-left
            {1, 1}    // down-right
    };
    public static final int[][] ROOK_DIRECTIONS = {
            {0, 1},  // right
            {0, -1}, // left
            {1, 0},  // down
            {-1, 0}  // up
    };
    public static final int[][] KING_DIRECTIONS = {
            {0, 1},  // right
            {0, -1}, // left
            {1, 0},  // down
            {-1, 0}, // up
            {1, 1},  // down-right
            {1, -1}, // down-left
            {-1, 1}, // up-right
            {-1, -1} // up-left
    };
    public static final int[][] KNIGHT_VALUES = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}

    };
    public static final int[][] PAWN_VALUES = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 30, 30, 0, 0, 0},
            {5, -5, -20, 0, 0, -20, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    public static final int[][] PAWN_VALUES_ENDGAME = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {40, 40, 40, 40, 40, 40, 40, 40},
            {30, 30, 30, 30, 30, 30, 30, 30},
            {20, 20, 20, 20, 20, 20, 20, 20},
            {10, 10, 10, 10, 10, 10, 10, 10},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    public static final int[][] BISHOP_VALUES = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 10, 0, 0, 0, 0, 10, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
    };
    public static final int[][] ROOK_VALUES = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 10, 10, 10, 10, 10, 10, 5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {0, 0, 10, 20, 20, 10, 0, 0}
    };
    public static final int[][] QUEEN_VALUES = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}
    };
    public static final int[][] KING_VALUES = {
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {20, 50, 10, 0, 0, 10, 50, 20}
    };
    public static final int[][] KING_VALUES_ENDGAME = {
            {-50, -40, -30, -20, -20, -30, -40, -50},
            {-30, -20, -10, 0, 0, -10, -20, -30},
            {-30, -10, 20, 30, 30, 20, -10, -30},
            {-30, -10, 30, 40, 40, 30, -10, -30},
            {-30, -10, 30, 40, 40, 30, -10, -30},
            {-30, -10, 20, 30, 30, 20, -10, -30},
            {-30, -30, 0, 0, 0, 0, -30, -30},
            {-50, -30, -30, -30, -30, -30, -30, -50}
    };
    public static int getPieceValue(int piece){
        int p = piece & 0b111;

        return switch (p) {
            case Piece.pawn -> pawnValue;
            case Piece.knight -> knightValue;
            case Piece.bishop -> bishopValue;
            case Piece.rook -> rookValue;
            case Piece.queen -> queenValue;
            case Piece.king -> Integer.MAX_VALUE;
            default -> 0;
        };
    }
    public static int getPositionValue(int piece, int pieceIndex){
        int p = piece & 0b111;
        int rank = Main.getRank(pieceIndex);
        int file = Main.getFile(pieceIndex);
        if(!Piece.getColor(piece)){
            rank = 7 - rank;
        }
        return switch(p){
            case Piece.pawn -> PAWN_VALUES[rank][file];
            case Piece.knight -> KNIGHT_VALUES[rank][file];
            case Piece.bishop -> BISHOP_VALUES[rank][file];
            case Piece.rook -> ROOK_VALUES[rank][file];
            case Piece.queen -> QUEEN_VALUES[rank][file];
            case Piece.king -> KING_VALUES[rank][file] ;
            default -> 0;
        };


    }
    public static int getEndgamePositionValue(int piece, int pieceIndex){
        int p = piece & 0b111;
        int sign = Piece.getColor(piece) ? 1 : -1;
        int rank = Main.getRank(pieceIndex);
        int file = Main.getFile(pieceIndex);
        return switch(p){
            case Piece.pawn -> PAWN_VALUES_ENDGAME[rank][file] * sign;
            case Piece.knight -> 0;
            case Piece.bishop -> 0;
            case Piece.rook -> 0;
            case Piece.queen -> 0;
            case Piece.king -> KING_VALUES_ENDGAME[rank][file] * sign;
            default -> 0;
        };
    }

}
