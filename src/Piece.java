import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

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

    public static boolean getColor(int piece){

        return (piece & Piece.white) != 0;
    }
    private static boolean isValidSquare(int x, int y){
        return x < 8 && x > -1 && y < 8 && y > -1;
    }
    private static boolean isValidSquare(int square){
        return square < 64 && square > -1;
    }

    public static ArrayList<Move> getPawnMoves(int x, int y, Board chessboard) {
        int piece = chessboard.board[Main.getIndex(x, y)];
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = getColor(piece);


        if(white){
            // Check if the pawn can move 1 square forward
            if(chessboard.board[Main.getIndex(x,y+1)]==0){

                if(y==6){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y+1), Piece.white|Piece.knight));

                }else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y+1)));
            }
            // Check if the pawn can move 2 squares forward
            if(y!=6&&chessboard.board[Main.getIndex(x,y+2)]==0 && chessboard.board[Main.getIndex(x,y+1)]==0 && y==1){
                moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y+2)));
            }
            if(isValidSquare(x + 1, y + 1) && chessboard.board[Main.getIndex(x+1, y+1)]!=0 && !getColor(chessboard.board[Main.getIndex(x+1, y+1)])){
                if(y==6){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y+1), Piece.white|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x+1,y+1)));
            }
            if(isValidSquare(x - 1, y + 1) && chessboard.board[Main.getIndex(x-1, y+1)]!=0 && !getColor(chessboard.board[Main.getIndex(x-1, y+1)])){
                if(y==6){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y+1), Piece.white|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x-1,y+1)));
            }


        }
        else{
            // Check if the pawn can move 1 square forward
            if(chessboard.board[Main.getIndex(x,y-1)]==0){

                if(y==1){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x, y-1), Piece.black|Piece.knight));

                }else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y-1)));
            }
            // Check if the pawn can move 2 squares forward
            if(y!=1&&chessboard.board[Main.getIndex(x,y-2)]==0 && chessboard.board[Main.getIndex(x,y-1)]==0 && y==6){
                moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x,y-2)));
            }
            if(isValidSquare(x + 1, y - 1) && chessboard.board[Main.getIndex(x+1, y-1)]!=0 && getColor(chessboard.board[Main.getIndex(x+1, y-1)])){
                if(y==1){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x+1, y-1), Piece.black|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x+1,y-1)));
            }
            if(isValidSquare(x - 1, y - 1) && chessboard.board[Main.getIndex(x-1, y-1)]!=0 && getColor(chessboard.board[Main.getIndex(x-1, y-1)])){
                if(y==1){
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.bishop));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.queen));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.rook));
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(x-1, y-1), Piece.black|Piece.knight));

                }
                else moves.add(new Move(Main.getIndex(x,y), Main.getIndex(x-1,y-1)));
            }


        }



        return moves;
    }

    public static ArrayList<Move> getKnightMoves(int x, int y, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        int color = black;
        if(getColor(board.board[Main.getIndex(x,y)])){
            color = white;
        }
        for(int[] move : Offsets.KNIGHT_MOVES){
            int square = Main.getIndex(x+move[0], y+move[1]);
            if(isValidSquare(square)){
                if(board.board[square]!=0 && (board.board[square] & color)!=0){
                    continue;
                }
                Move m = new Move(Main.getIndex(x,y), square);
                moves.add(m);

            }

        }


        return moves;
    }

    public static ArrayList<Move> getBishopMoves(int x, int y, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean color = getColor(board.board[Main.getIndex(x,y)]);
        for(int[] direction : Offsets.BISHOP_DIRECTIONS){
            int newX = x;
            int newY = y;

            while(true){
                newX+=direction[0];
                newY+=direction[1];

                if(!isValidSquare(newX,newY)){
                    break;
                }
                int destinationPiece = board.board[Main.getIndex(newX, newY)];
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

    public static ArrayList<Move> getRookMoves(int x, int y, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean color = getColor(board.board[Main.getIndex(x,y)]);
        for(int[] direction : Offsets.ROOK_DIRECTIONS){
            int newX = x;
            int newY = y;

            while(true){
                newX+=direction[0];
                newY+=direction[1];

                if(!isValidSquare(newX,newY)){
                    break;
                }
                int destinationPiece = board.board[Main.getIndex(newX, newY)];
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

    public static ArrayList<Move> getQueenMoves(int x, int y, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getBishopMoves(x,y,board));
        moves.addAll(getRookMoves(x,y,board));
        return moves;
    }

    public static ArrayList<Move> getKingMoves(int x, int y, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        boolean color = getColor(board.board[Main.getIndex(x,y)]);

        for (int[] direction : Offsets.KING_DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isValidSquare(newX, newY)) {
                int destinationPiece = board.board[Main.getIndex(newX, newY)];
                if (destinationPiece == none || getColor(destinationPiece) != color) {
                    moves.add(new Move(Main.getIndex(x, y), Main.getIndex(newX, newY)));
                }
            }
        }

        return moves;
    }

}
