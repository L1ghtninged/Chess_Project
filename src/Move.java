import java.util.HashMap;
import java.util.Objects;

public class Move {
    private int pieceIndex;
    private int positionIndex;
    private int promotion = 0;

    public Move(int pieceIndex, int positionIndex){
        this.pieceIndex = pieceIndex;
        this.positionIndex = positionIndex;
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
        return pieceIndex == move.pieceIndex && positionIndex == move.positionIndex && promotion == move.promotion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceIndex, positionIndex, promotion);
    }
}
