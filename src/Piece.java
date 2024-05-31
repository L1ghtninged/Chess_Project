import java.util.ArrayList;

/**
 * Holds the placeholders for all pieces
 * Works well with binary operations
 */
public class Piece {
    public final static int none = 0; // 00000
    public final static int king = 1; // 00001
    public final static int pawn = 2; //  00010
    public final static int knight = 3; // 00011
    public final static int bishop = 4; // 00100
    public final static int rook = 5; // 00101
    public final static int queen = 6; // 00110

    public final static int white = 8; // 01000
    public final static int black = 16; // 10000

    /**
     * Gets the color of a piece
     * @param piece
     * @return boolean, true if white, false if black
     */
    public static boolean getColor(int piece){

        return (piece & Piece.white) != 0;
    }

    /**
     * Determines, whether the square exists on the chessboard
     * @param x square's coordinate
     * @param y square's coordinate
     * @return boolean, true if square is valid
     */
    private static boolean isValidSquare(int x, int y){
        return x < 8 && x > -1 && y < 8 && y > -1;
    }

    /**
     * Determines, whether the square exists on the chessboard
     * @param square 1D version
     * @return boolean, true if square is valid
     */
    private static boolean isValidSquare(int square){
        return square < 64 && square > -1;
    }


    /**
     * Gets all possible pawn moves for a square(x,y)
     *
     * @param x pawn's coordinate
     * @param y pawn's coordinate
     * @param chessboard
     * @return list of pawn moves
     */
    public static ArrayList<Move> getPawnMoves(int x, int y, Board chessboard) {
        int[] board = chessboard.board;
        int piece = board[Main.getIndex(x, y)];
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = getColor(piece);


        if(white){ // white color
            // Check if the pawn can move 1 square forward
            if(board[Main.getIndex(x,y+1)]==0){

                if(y==6){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.knight));

                }else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y+1)));
            }
            // Check if the pawn can move 2 squares forward
            if(y!=6&&board[Main.getIndex(x,y+2)]==0 && board[Main.getIndex(x,y+1)]==0 && y==1){
                moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y+2)));

            }
            if(isValidSquare(x + 1, y + 1) && board[Main.getIndex(x+1, y+1)]!=0 && !getColor(board[Main.getIndex(x+1, y+1)])){
                if(y==6){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x+1,y+1)));
            }
            if(isValidSquare(x - 1, y + 1) && board[Main.getIndex(x-1, y+1)]!=0 && !getColor(board[Main.getIndex(x-1, y+1)])){
                if(y==6){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x-1,y+1)));
            }


        }
        else{ // black color
            // Check if the pawn can move 1 square forward
            if(board[Main.getIndex(x,y-1)]==0){

                if(y==1){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.knight));

                }else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y-1)));
            }
            // Check if the pawn can move 2 squares forward
            if(y!=1&&board[Main.getIndex(x,y-2)]==0 && board[Main.getIndex(x,y-1)]==0 && y==6){
                moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y-2)));
            }
            if(isValidSquare(x + 1, y - 1) && board[Main.getIndex(x+1, y-1)]!=0 && getColor(board[Main.getIndex(x+1, y-1)])){
                if(y==1){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x+1,y-1)));
            }
            if(isValidSquare(x - 1, y - 1) && board[Main.getIndex(x-1, y-1)]!=0 && getColor(board[Main.getIndex(x-1, y-1)])){
                if(y==1){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x-1,y-1)));
            }


        }

        if (chessboard.enPassantTarget != -1) { // checks for enPassant
            int enPassantX = Main.getFile(chessboard.enPassantTarget);
            int enPassantY = Main.getRank(chessboard.enPassantTarget);
            boolean whiteToMove = getColor(board[Main.getIndex(x, y)]);

            if (y == (whiteToMove ? 4 : 3) && Math.abs(x - enPassantX) == 1) {
                Move m = new Move(Main.getIndex(x, y), chessboard.enPassantTarget, true);
                moves.add(m);
            }
        }


        return moves;
    }

    /**
     * Gets all possible knight moves for a square(x,y)
     * Based on the Offsets directions for the knight
     * @param x knight's coordinate
     * @param y knight's coordinate
     * @param board
     * @return list of knight moves
     */
    public static ArrayList<Move> getKnightMoves(int x, int y, int[] board) {
        ArrayList<Move> moves = new ArrayList<>();
        int color = black;
        if(getColor(board[Main.getIndex(x,y)])){
            color = white;
        }
        for(int[] move : Offsets.KNIGHT_MOVES){
            int square = Main.getIndex(x+move[0], y+move[1]);
            if(isValidSquare(square)){
                if(board[square]!=0 && (board[square] & color)!=0){
                    continue;
                }
                Move m = new Move(Main.getIndex(x,y), square);
                moves.add(m);

            }

        }


        return moves;
    }
    /**
     * Gets all possible bishop moves for a square(x,y)
     * Based on the Offsets directions for the bishop
     * @param x bishop's coordinate
     * @param y bishop's coordinate
     * @param board
     * @return list of bishop moves
     */
    public static ArrayList<Move> getBishopMoves(int x, int y, int[] board) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean color = getColor(board[Main.getIndex(x,y)]);
        for(int[] direction : Offsets.BISHOP_DIRECTIONS){
            int newX = x;
            int newY = y;

            while(true){
                newX+=direction[0];
                newY+=direction[1];

                if(!isValidSquare(newX,newY)){
                    break;
                }
                int destinationPiece = board[Main.getIndex(newX, newY)];
                if (destinationPiece == none) {
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(newX, newY)));
                } else {
                    if (getColor(destinationPiece) != color) {
                        moves.add(new Move(Main.getIndex(x, y), Main.getIndex(newX, newY)));
                    }
                    break;
                }
            }
        }
        return moves;
    }
    /**
     * Gets all possible rook moves for a square(x,y)
     * Based on the Offsets directions for the rook
     * @param x rook's coordinate
     * @param y rook's coordinate
     * @param board
     * @return list of rook moves
     */
    public static ArrayList<Move> getRookMoves(int x, int y, int[] board) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean color = getColor(board[Main.getIndex(x,y)]);
        for(int[] direction : Offsets.ROOK_DIRECTIONS){
            int newX = x;
            int newY = y;

            while(true){
                newX+=direction[0];
                newY+=direction[1];

                if(!isValidSquare(newX,newY)){
                    break;
                }
                int destinationPiece = board[Main.getIndex(newX, newY)];
                if (destinationPiece == none) {
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(newX, newY)));
                } else {
                    if (getColor(destinationPiece) != color) {
                        moves.add(new Move(Main.getIndex(x, y), Main.getIndex(newX, newY)));
                    }
                    break;
                }
            }
        }
        return moves;
    }
    /**
     * Gets all possible knight queen for a square(x,y)
     * Adds the bishop and rook moves together
     * @param x queen's coordinate
     * @param y queen's coordinate
     * @param board
     * @return queen of knight moves
     */
    public static ArrayList<Move> getQueenMoves(int x, int y, int[] board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getBishopMoves(x,y,board));
        moves.addAll(getRookMoves(x,y,board));
        return moves;
    }
    /**
     * Gets all possible king moves for a square(x,y)
     * Doesn't react to checks
     * @param x king's coordinate
     * @param y king's coordinate
     * @param board
     * @return list of king moves
     */
    public static ArrayList<Move> getKingMoves(int x, int y, int[] board) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean color = getColor(board[Main.getIndex(x,y)]);

        for (int[] direction : Offsets.KING_DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isValidSquare(newX, newY)) {
                int destinationPiece = board[Main.getIndex(newX, newY)];
                if (destinationPiece == none || getColor(destinationPiece) != color) {
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(newX, newY)));
                }
            }
        }

        return moves;
    }

}