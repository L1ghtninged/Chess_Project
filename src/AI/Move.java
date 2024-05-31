package AI;

import java.util.Objects;

/**
 * Structure class for a chess move, contains a pieceIndex(FROM) and a positionIndex(TO)
 */
public class Move{
    private int pieceIndex;
    private int positionIndex;
    private int promotion = 0;
    private int castling = 0; // 1 if king-side, 2 if queen-side
    public boolean isEnPassant;

    public Move(int pieceIndex, int positionIndex, boolean isEnPassant){
        this.pieceIndex = pieceIndex;
        this.positionIndex = positionIndex;
        this.isEnPassant = isEnPassant;
    }

    public Move(int castling){
        this.castling = castling;
    }
    public Move(String castling){
        if(castling.equals("0-0")){
            this.castling=1;
        }
        if(castling.equals("0-0-0")){
            this.castling = 2;
        }
        else{
            throw new IllegalArgumentException("Wrong castling format");
        }
    }
    public Move(int pieceIndex, int positionIndex){
        this.pieceIndex = pieceIndex;
        this.positionIndex = positionIndex;
    }

    public int getCastling() {
        return castling;
    }

    public void setCastling(int castling) {
        this.castling = castling;
    }

    public Move(int pieceIndex, int positionIndex, int promotion){
        this.pieceIndex = pieceIndex;
        this.positionIndex = positionIndex;
        this.promotion = promotion;
    }
    public Move(String piece, String position){
        this.pieceIndex = Main.getIndex(piece);
        this.positionIndex = Main.getIndex(position);
    }
    public Move(String piece, String position, boolean isEnPassant) {
        this.pieceIndex = Main.getIndex(piece);
        this.positionIndex = Main.getIndex(position);
        this.isEnPassant = isEnPassant;
    }
    public Move(String piece, String position, int promotion){
        this.pieceIndex = Main.getIndex(piece);
        this.positionIndex = Main.getIndex(position);
        this.promotion = promotion;
    }
    public int getPromotion() {
        return promotion;
    }

    public int getPieceIndex() {
        return pieceIndex;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public String toString(){
        if(castling == 1){
            return "0-0";
        }
        if(castling == 2){
            return "0-0-0";
        }
        int filePiece = Main.getFile(pieceIndex); // 4
        int rankPiece = Main.getRank(pieceIndex); // 1
        int filePosition = Main.getFile(positionIndex); //4
        int rankPosition = Main.getRank(positionIndex); //2
        if(promotion==0){
            return Main.getSquare(Main.getIndex(filePiece, rankPiece))+"-"+Main.getSquare(Main.getIndex(filePosition, rankPosition));
        }

        return Main.getSquare(Main.getIndex(filePiece, rankPiece))+"-"+Main.getSquare(Main.getIndex(filePosition, rankPosition))+"="+promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return pieceIndex == move.pieceIndex && positionIndex == move.positionIndex && promotion == move.promotion && castling == move.castling && isEnPassant == move.isEnPassant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceIndex, positionIndex, promotion, castling, isEnPassant);
    }


}
