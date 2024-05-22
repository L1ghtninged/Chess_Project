import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.loadFromFEN(Board.startPosition);
        //board.loadFromFEN("rnbq1k1r/pp1Pbppp/2p5/8/2B5/8/PPP1NnPP/RNBQK2R");
        int numberOfPositions = moveGenerationTest(4, board);
        System.out.println(numberOfPositions);

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
        int[] positions = new int[20];
        int i = 0;
        for (Move move : moves) {

            Board tmp = new Board(board); // Copy the board
            tmp.makeMove(move); // Apply the move on the copied board
            numPositions += moveGenerationTest(depth - 1, tmp); // Recursively count positions
            if(depth == 3){
                System.out.println(move + " " + numPositions);
                positions[i] = numPositions;
            }
            i++;
        }

        for(int j = 0; j < positions.length; j++){
            if(j!=0 && positions[j] !=0){
                System.out.println(positions[j] - positions[j-1]);
            }
            else if(positions[j] != 0){
                System.out.println(positions[j]);
            }

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

}