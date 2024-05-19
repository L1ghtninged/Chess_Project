import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(Board.startPosition);
        board.playMove(new Move("e2","e4"));
        System.out.println(board);
        board.playMove(new Move("d7","d5"));
        System.out.println(board);
        board.playMove(new Move("e4","d5"));
        System.out.println(board);
        board.playMove(new Move("e7","e6"));
        System.out.println(board);
        board.playMove(new Move("d5","e6"));
        System.out.println(board);
        board.playMove(new Move("e8","e7"));
        System.out.println(board);
        board.playMove(new Move("e6","f7"));
        System.out.println(board);
        board.playMove(new Move("d8","e8"));
        System.out.println(board);
        board.playMove(new Move("f2","f3"));
        System.out.println(board);
        board.playMove(new Move("e7","d7"));
        System.out.println(board);
        board.playMove(new Move("h2", "h3"));
        System.out.println(board);
        board.playMove(new Move("f8", "c5"));
        System.out.println(board);
        board.playMove(new Move("f7", "f8", Piece.white|Piece.rook));
        System.out.println(board);
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