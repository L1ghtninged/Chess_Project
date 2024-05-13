import java.util.HashMap;

public class Move {
    private int pieceIndex;
    private int positionIndex;


    public Move(int pieceIndex, int positionIndex){
        this.pieceIndex = pieceIndex;
        this.positionIndex = positionIndex;

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
        return Main.getSquare(Main.getIndex(filePiece, rankPiece))+"-"+Main.getSquare(Main.getIndex(filePosition, rankPosition));
    }
}
