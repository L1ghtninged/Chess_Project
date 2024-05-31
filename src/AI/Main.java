package AI;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {


        ChessGame game = new ChessGame("8/8/8/4K3/8/8/bbk4p/8");
        game.loadFromFen(Board.startPosition);


        /*game.board.whiteCastlingKing = false;
        game.board.whiteCastlingQueen = false;
        game.board.blackCastlingQueen = false;
        game.board.blackCastlingKing = false;


         */


        GameFrame frame = new GameFrame(game);
        //int depth = 4;
        //Move bestMove = AI.findBestMove(game.board, depth);
        //System.out.println("Best move at depth " + depth + ": " + bestMove);


    }

    /**
     * Helps for debugging move-generation
     * @param depth - corresponds to 1 player making a move
     * @param board
     * @return number of possible positions
     */
    public static int moveGenerationTest(int depth, Board board) {
        if (depth == 0) {

            return 1;
        }


        int numPositions = 0;
        ArrayList<Move> moves = MoveGenerator.generateLegalMoves(board.isWhiteToMove, board);
        for (Move move : moves) {
            Board tmp = new Board(board); // Copy the board
            tmp.makeMove(move); // Apply the move on the copied board
            numPositions += moveGenerationTest(depth - 1, tmp); // Recursively count positions
        }

        return numPositions;
    }

    /**
     * @param square - index of a piece
     * @return the rank of the piece, based on the index
     */
    public static int getRank(int square){
        return square/8;
    }
    /**
     * @param square - index of a piece
     * @return the file of the piece, based on the index
     */
    public static int getFile(int square){
        return square - 8*Main.getRank(square);
    }

    /**
     *
     * @param file - file of the piece
     * @param rank - rank of the piece
     * @return index of a piece, based on its file and rank
     */
    public static int getIndex(int file, int rank){
        if(rank < 0 || file < 0){
            return -1;
        }
        if(rank > 7 || file > 7){
            return -1;
        }
        return file+rank*8;
    }

    /**
     *
     * @param index of a square on a chessboard
     * @return name of the square, based on the classical chess notation
     */
    public static String getSquare(int index){
        HashMap<Integer, Character> fileMap = new HashMap<>();
        fileMap.put(0, 'a');
        fileMap.put(1, 'b');
        fileMap.put(2, 'c');
        fileMap.put(3, 'd');
        fileMap.put(4, 'e');
        fileMap.put(5, 'f');
        fileMap.put(6, 'g');
        fileMap.put(7, 'h');

        char c = fileMap.get(getFile(index));
        int rank = getRank(index);
        return c+""+(rank+1);


    }

    /**
     *
     * @param square - name of a square on a chessboard, based on the classical chess notation
     * @return index of the square based on its name
     */
    public static int getIndex(String square){
        HashMap<Character, Integer> fileMap = new HashMap<>();
        fileMap.put('a', 0);
        fileMap.put('b', 1);
        fileMap.put('c', 2);
        fileMap.put('d', 3);
        fileMap.put('e', 4);
        fileMap.put('f', 5);
        fileMap.put('g', 6);
        fileMap.put('h', 7);

        int file = fileMap.get(square.charAt(0));
        int rank = Integer.parseInt(String.valueOf(square.charAt(1)))-1;
        return getIndex(file, rank);

    }

    /**
     * loads a chessboard
     * @param fen - string, describes a chess position
     * @return board
     */
    public static int[] loadFromFEN(String fen){
        int[] board = new int[64];
        char[]pos = fen.toCharArray();
        int file = 0;
        int rank = 7;

        for (char c : pos) {
            switch (c) {
                case '/':
                    file = 0;
                    rank--;
                    break;
                case 'k':
                    board[file+rank*8] = Piece.black| Piece.king;
                    file++;
                    break;
                case 'n':
                    board[file+rank*8] = Piece.black| Piece.knight;
                    file++;
                    break;
                case 'r':
                    board[file+rank*8] = Piece.black| Piece.rook;
                    file++;
                    break;
                case 'b':
                    board[file+rank*8] = Piece.black| Piece.bishop;
                    file++;
                    break;
                case 'q':
                    board[file+rank*8] = Piece.black| Piece.queen;
                    file++;
                    break;
                case 'p':
                    board[file+rank*8] = Piece.black| Piece.pawn;
                    file++;
                    break;
                case 'K':
                    board[file+rank*8] = Piece.white| Piece.king;
                    file++;
                    break;
                case 'N':
                    board[file+rank*8] = Piece.white| Piece.knight;
                    file++;
                    break;
                case 'R':
                    board[file+rank*8] = Piece.white| Piece.rook;
                    file++;
                    break;
                case 'B':
                    board[file+rank*8] = Piece.white| Piece.bishop;
                    file++;
                    break;
                case 'Q':
                    board[file+rank*8] = Piece.white| Piece.queen;
                    file++;
                    break;
                case 'P':
                    board[file+rank*8] = Piece.white| Piece.pawn;
                    file++;
                    break;
                case '1':
                    file++;
                    break;
                case '2':
                    file += 2;
                    break;
                case '3':
                    file += 3;
                    break;
                case '4':
                    file += 4;
                    break;
                case '5':
                    file += 5;
                    break;
                case '6':
                    file += 6;
                    break;
                case '7':
                    file += 7;
                    break;
                default:
            }
        }
        return board;
    }

}