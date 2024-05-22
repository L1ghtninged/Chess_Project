import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Board implements Cloneable{
    public ArrayList<Move> game = new ArrayList<>();
    public int[] board;
    public int enPassantTarget = -1;
    public boolean isWhiteToMove = true;
    public boolean whiteCastlingQueen = true;
    public boolean whiteCastlingKing = true;
    public boolean blackCastlingKing = true;
    public boolean blackCastlingQueen = true;
    public HashMap<Integer, Character> pieceMap = new HashMap<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return enPassantTarget == board1.enPassantTarget && isWhiteToMove == board1.isWhiteToMove && whiteCastlingQueen == board1.whiteCastlingQueen && whiteCastlingKing == board1.whiteCastlingKing && blackCastlingKing == board1.blackCastlingKing && blackCastlingQueen == board1.blackCastlingQueen && Arrays.equals(board, board1.board) && pieceMap.equals(board1.pieceMap);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(enPassantTarget, isWhiteToMove, whiteCastlingQueen, whiteCastlingKing, blackCastlingKing, blackCastlingQueen, pieceMap);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    public static final String startPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    public Board(String fen){
        this.board = new int[64];
        loadFromFEN(fen);
        initializeMap();
    }
    public Board(){
        this.board = new int[64];
        initializeMap();
    }

    public Board(Board b) {
        this.board = b.board;
        this.enPassantTarget = b.enPassantTarget;
        this.isWhiteToMove = b.isWhiteToMove;
        this.whiteCastlingQueen = b.whiteCastlingQueen;
        this.whiteCastlingKing = b.whiteCastlingKing;
        this.blackCastlingKing = b.blackCastlingKing;
        this.blackCastlingQueen = b.blackCastlingQueen;
        this.pieceMap = b.pieceMap;
        this.game = b.game;
    }

    public void playMove(Move move){
        ArrayList<Move> legalMoves = MoveGenerator.generateLegalMoves(isWhiteToMove, this);

        if(legalMoves.contains(move)){
            makeMove(move);
            System.out.println("The move " + move + " was played.");
        }
        else{
            throw new IllegalArgumentException("Illegal move");
        }
    }
    public void whiteCastlingKing(){
        board[6] = Piece.king|Piece.white;
        board[4] = Piece.none;
        board[7] = Piece.none;
        board[5] = Piece.rook|Piece.white;
        whiteCastlingKing = false;
        whiteCastlingQueen = false;
    }
    public void whiteCastlingQueen(){
        board[4] = Piece.none;
        board[0] = Piece.none;
        board[2] = Piece.king|Piece.white;
        board[3] = Piece.rook|Piece.white;
        whiteCastlingKing = false;
        whiteCastlingQueen = false;

    }
    public void blackCastlingKing(){
        board[62] = Piece.king|Piece.black;
        board[60] = Piece.none;
        board[63] = Piece.none;
        board[61] = Piece.rook|Piece.black;
        blackCastlingKing = false;
        blackCastlingQueen = false;
    }
    public void blackCastlingQueen(){
        board[60] = Piece.none;
        board[56] = Piece.none;
        board[58] = Piece.king|Piece.black;
        board[59] = Piece.rook|Piece.black;
        blackCastlingKing = false;
        blackCastlingQueen = false;
    }

    public void makeMove(Move move){
        //Board helpBoard = new Board(this.board, this.enPassantTarget, this.isWhiteToMove, this.whiteCastlingQueen, this.whiteCastlingKing, this.blackCastlingKing, this.blackCastlingQueen, this.pieceMap);

        if(move.getPieceIndex()==4){
            whiteCastlingQueen = false;
            whiteCastlingKing = false;
        }
        else if(move.getPieceIndex() == 7){
            whiteCastlingKing = false;
        }
        else if(move.getPieceIndex() == 0){
            whiteCastlingQueen = false;
        }
        else if(move.getPositionIndex() == 0){
            whiteCastlingQueen = false;
        }
        else if(move.getPositionIndex() == 7){
            whiteCastlingKing = false;
        }
        else if(move.getPositionIndex() == 56){
            blackCastlingQueen = false;
        }
        else if(move.getPositionIndex() == 63){
            blackCastlingKing = false;
        }
        else if(move.getPieceIndex() == 60){
            blackCastlingKing = false;
            blackCastlingQueen = false;
        }
        else if(move.getPieceIndex() == 56){
            blackCastlingQueen = false;
        }
        else if(move.getPieceIndex() == 63){
            blackCastlingKing = false;
        }

        if(move.getCastling()==1){
            if(isWhiteToMove){
                whiteCastlingKing();
            }
            else{
                blackCastlingKing();
            }
        }
        else if(move.getCastling()==2){
            if(isWhiteToMove){
                whiteCastlingQueen();
            }
            else{
                blackCastlingQueen();
            }
        }

        board[move.getPositionIndex()] = board[move.getPieceIndex()];
        board[move.getPieceIndex()] = Piece.none;
        if(move.getPromotion()!=Piece.none){
            board[move.getPositionIndex()] = move.getPromotion();
        }

        else if (move.isEnPassant) {
            int capturedPawnIndex = move.getPositionIndex() + (Piece.getColor(board[move.getPositionIndex()]) ? -8 : 8);
            board[capturedPawnIndex] = Piece.none;
        }

        // Update en passant target square
        enPassantTarget = -1;
        int pieceType = board[move.getPositionIndex()] & 0b111;
        if (pieceType == Piece.pawn) {
            int startX = Main.getFile(move.getPieceIndex());
            int endX = Main.getFile(move.getPositionIndex());
            int startY = Main.getRank(move.getPieceIndex());
            int endY = Main.getRank(move.getPositionIndex());

            if (Math.abs(endY - startY) == 2) {
                enPassantTarget = Main.getIndex(startX, (startY + endY) / 2);
            }
        }
        isWhiteToMove = !isWhiteToMove;
        game.add(move);
        //return helpBoard;
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
        this.board = new int[64];
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
            }
        }
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
