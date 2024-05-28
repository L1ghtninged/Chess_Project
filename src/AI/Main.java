package AI;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        ChessGame game = new ChessGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        // Set up the board with the given moves
        game.playMove(new Move("e2", "e4"));
        game.playMove(new Move("e7", "e5"));
        game.playMove(new Move("g1", "f3"));
        game.playMove(new Move("d8", "h4"));

        // Find and print the best move at depth 2
        int depth = 5;
        Move bestMove = AI.findBestMove(game.board, depth);
        System.out.println("Best move at depth " + depth + ": " + bestMove);


    }

    public static void playMove(Move move, Board board){
        board.playMove(move);
        System.out.println(board);
    }
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


    public static int getRank(int square){
        return square/8;
    }
    public static int getFile(int square){
        return square - 8*Main.getRank(square);
    }
    public static int getIndex(int file, int rank){
        if(rank < 0 || file < 0){
            return -1;
        }
        if(rank > 7 || file > 7){
            return -1;
        }
        return file+rank*8;
    }
    public static String getSquare(int index){
        HashMap<Integer, Character> fileMap = new HashMap<>();
        fileMap = new HashMap<>();
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
    public static int getIndex(String square){
        HashMap<Character, Integer> fileMap = new HashMap<>();
        fileMap = new HashMap<>();
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