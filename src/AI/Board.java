package AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Board implements Cloneable{
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
        this.board = Main.loadFromFEN(fen);
        initializeMap();
    }
    public Board(){
        this.board = Main.loadFromFEN(startPosition);
        initializeMap();
    }
    public Board(int[] board){
        this.board = board;
        initializeMap();
    }

    public Board(Board other) {
            this.board = other.board.clone();
            this.enPassantTarget = other.enPassantTarget;
            this.isWhiteToMove = other.isWhiteToMove;
            this.whiteCastlingQueen = other.whiteCastlingQueen;
            this.whiteCastlingKing = other.whiteCastlingKing;
            this.blackCastlingKing = other.blackCastlingKing;
            this.blackCastlingQueen = other.blackCastlingQueen;
            this.pieceMap = new HashMap<>(other.pieceMap);
    }

    public void playMove(Move move){
        ArrayList<Move> legalMoves = MoveGenerator.generateLegalMoves(isWhiteToMove, this);

        if(legalMoves.contains(move)){
            makeMove(move);
        }
        else{
            throw new IllegalArgumentException("Illegal move");
        }
    }
    public void whiteCastlingKing(){
        board[6] = Piece.king| Piece.white;
        board[4] = Piece.none;
        board[7] = Piece.none;
        board[5] = Piece.rook| Piece.white;
        whiteCastlingKing = false;
        whiteCastlingQueen = false;
    }
    public void whiteCastlingQueen(){
        board[4] = Piece.none;
        board[0] = Piece.none;
        board[2] = Piece.king| Piece.white;
        board[3] = Piece.rook| Piece.white;
        whiteCastlingKing = false;
        whiteCastlingQueen = false;

    }
    public void blackCastlingKing(){
        board[62] = Piece.king| Piece.black;
        board[60] = Piece.none;
        board[63] = Piece.none;
        board[61] = Piece.rook| Piece.black;
        blackCastlingKing = false;
        blackCastlingQueen = false;
    }
    public void blackCastlingQueen(){
        board[60] = Piece.none;
        board[56] = Piece.none;
        board[58] = Piece.king| Piece.black;
        board[59] = Piece.rook| Piece.black;
        blackCastlingKing = false;
        blackCastlingQueen = false;
    }

    public void makeMove(Move move){
        //AI.Board helpBoard = new AI.Board(this.board, this.enPassantTarget, this.isWhiteToMove, this.whiteCastlingQueen, this.whiteCastlingKing, this.blackCastlingKing, this.blackCastlingQueen, this.pieceMap);

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
        else{
            board[move.getPositionIndex()] = board[move.getPieceIndex()];
            board[move.getPieceIndex()] = Piece.none;
        }
        if(move.getPromotion()!= Piece.none){
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
        isWhiteToMove = !isWhiteToMove;;
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
        pieceMap.put(Piece.white| Piece.king, 'K');
        pieceMap.put(Piece.white| Piece.queen, 'Q');
        pieceMap.put(Piece.white| Piece.rook, 'R');
        pieceMap.put(Piece.white| Piece.bishop, 'B');
        pieceMap.put(Piece.white| Piece.knight, 'N');
        pieceMap.put(Piece.white| Piece.pawn, 'P');
        pieceMap.put(Piece.black| Piece.king, 'k');
        pieceMap.put(Piece.black| Piece.queen, 'q');
        pieceMap.put(Piece.black| Piece.rook, 'r');
        pieceMap.put(Piece.black| Piece.bishop, 'b');
        pieceMap.put(Piece.black| Piece.knight, 'n');
        pieceMap.put(Piece.black| Piece.pawn, 'p');
    }
    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();

            // Deep copy of the board array
            clone.board = this.board.clone();
            // Deep copy of the piece map
            clone.pieceMap = new HashMap<>(this.pieceMap);

            // Copy the remaining primitive fields
            clone.enPassantTarget = this.enPassantTarget;
            clone.isWhiteToMove = this.isWhiteToMove;
            clone.whiteCastlingQueen = this.whiteCastlingQueen;
            clone.whiteCastlingKing = this.whiteCastlingKing;
            clone.blackCastlingKing = this.blackCastlingKing;
            clone.blackCastlingQueen = this.blackCastlingQueen;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
