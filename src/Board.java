import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    public int[] board;

    public static final String startPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    private HashMap<Integer, Character> pieceMap = new HashMap<>();
    public Board(String fen){
        this.board = new int[64];
        loadFromFEN(fen);
        initializeMap();

    }
    public Board(){
        this.board = new int[64];
        initializeMap();
    }


    public void playMove(int piece, int position){
        board[position] = board[piece];
        board[piece] = Piece.none;
    }
    public void setPiece(int x, int y, int piece){
        int index = Main.getIndex(x, y);
        board[index] = piece;
    }




    public String toString(){
        StringBuilder string = new StringBuilder();
        for(int i = 7; i >= 0;i--){
            for(int j = 0; j < 8; j++){
                string.append(pieceMap.get(board[Main.getIndex(j, i)]));
                string.append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    private void initializeMap(){
        pieceMap.put(Piece.none, '*');
        pieceMap.put(Piece.white|Piece.king, 'K');
        pieceMap.put(Piece.white|Piece.queen, 'Q');
        pieceMap.put(Piece.white|Piece.rook, 'R');
        pieceMap.put(Piece.white|Piece.bishop, 'B');
        pieceMap.put(Piece.white|Piece.knight, 'N');
        pieceMap.put(Piece.white|Piece.pawn, 'P');
        pieceMap.put(Piece.black|Piece.king, 'k');
        pieceMap.put(Piece.black|Piece.queen, 'q');
        pieceMap.put(Piece.black|Piece.rook, 'r');
        pieceMap.put(Piece.black|Piece.bishop, 'b');
        pieceMap.put(Piece.black|Piece.knight, 'n');
        pieceMap.put(Piece.black|Piece.pawn, 'p');
    }

    public void loadFromFEN(String fen){
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
                    board[file+rank*8] = Piece.black|Piece.king;
                    file++;
                    break;
                case 'n':
                    board[file+rank*8] = Piece.black|Piece.knight;
                    file++;
                    break;
                case 'r':
                    board[file+rank*8] = Piece.black|Piece.rook;
                    file++;
                    break;
                case 'b':
                    board[file+rank*8] = Piece.black|Piece.bishop;
                    file++;
                    break;
                case 'q':
                    board[file+rank*8] = Piece.black|Piece.queen;
                    file++;
                    break;
                case 'p':
                    board[file+rank*8] = Piece.black|Piece.pawn;
                    file++;
                    break;
                case 'K':
                    board[file+rank*8] = Piece.white|Piece.king;
                    file++;
                    break;
                case 'N':
                    board[file+rank*8] = Piece.white|Piece.knight;
                    file++;
                    break;
                case 'R':
                    board[file+rank*8] = Piece.white|Piece.rook;
                    file++;
                    break;
                case 'B':
                    board[file+rank*8] = Piece.white|Piece.bishop;
                    file++;
                    break;
                case 'Q':
                    board[file+rank*8] = Piece.white|Piece.queen;
                    file++;
                    break;
                case 'P':
                    board[file+rank*8] = Piece.white|Piece.pawn;
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
                    ;


            }


        }


    }

}
