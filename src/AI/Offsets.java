package AI;

public class Offsets {
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


}
