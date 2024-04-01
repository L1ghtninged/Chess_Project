public class Board {
    public int[] squares;
    public static final String startPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public Board(String fen){
        this.squares = new int[64];
        loadFromFEN(fen);


    }
    public Board(){
        this.squares = new int[64];
    }

    public void loadFromFEN(String fen){
        char[]pos = fen.toCharArray();
        int file = 0;
        int rank = 0;

        for (char c : pos) {
            switch (c) {
                case '/':
                    file = 0;
                    rank++;
                    break;
                case 'k':
                    squares[file+rank*8] = Piece.black|Piece.king;
                    file++;
                    break;
                case 'n':
                    squares[file+rank*8] = Piece.black|Piece.knight;
                    file++;
                    break;
                case 'r':
                    squares[file+rank*8] = Piece.black|Piece.rook;
                    file++;
                    break;
                case 'b':
                    squares[file+rank*8] = Piece.black|Piece.bishop;
                    file++;
                    break;
                case 'q':
                    squares[file+rank*8] = Piece.black|Piece.queen;
                    file++;
                    break;
                case 'p':
                    squares[file+rank*8] = Piece.black|Piece.pawn;
                    file++;
                    break;
                case 'K':
                    squares[file+rank*8] = Piece.white|Piece.king;
                    file++;
                    break;
                case 'N':
                    squares[file+rank*8] = Piece.white|Piece.knight;
                    file++;
                    break;
                case 'R':
                    squares[file+rank*8] = Piece.white|Piece.rook;
                    file++;
                    break;
                case 'B':
                    squares[file+rank*8] = Piece.white|Piece.bishop;
                    file++;
                    break;
                case 'Q':
                    squares[file+rank*8] = Piece.white|Piece.queen;
                    file++;
                    break;
                case 'P':
                    squares[file+rank*8] = Piece.white|Piece.pawn;
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
