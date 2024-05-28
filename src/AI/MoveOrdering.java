package AI;

import java.util.ArrayList;

public class MoveOrdering {
    public void sortMoves(ArrayList<Move> moves, Board board){
        ArrayList<Move> captures = new ArrayList<>();
        //ArrayList<Move> checks = new ArrayList<>();
        ArrayList<Move> others = new ArrayList<>();

        for(Move move : moves){
            if(isCapture(move, board)){
                captures.add(move);
            }
            else{
                others.add(move);
            }
        }
        captures.sort((move1, move2)-> getMVVLVA(move2, board) - getMVVLVA(move1, board));
        moves.clear();
        moves.addAll(captures);
        //moves.addAll(checks);
        moves.addAll(others);
    }

    public int getMVVLVA(Move move, Board board){
        int victimValue = Offsets.getPieceValue(board.board[move.getPositionIndex()]);
        int attackerValue = Offsets.getPieceValue(board.board[move.getPieceIndex()]);

        return victimValue * 10 - attackerValue;
    }
    public boolean isCheck(Move move, Board board){
        Board tmp = new Board(board);
        tmp.playMove(move);
        tmp.setUpBlackAttackMap();
        tmp.setUpWhiteAttackMap();
        return isKingAttacked(tmp, tmp.isWhiteToMove);
    }
    public boolean isKingAttacked(Board board, boolean color){
        int index = findKing(board, color);
        if(color){
            return board.blackAttackMap[index].size() != 0;
        }
        return board.whiteAttackMap[index].size() != 0;

    }
    private boolean isCapture(Move move, Board board){
        return board.board[move.getPositionIndex()] != 0;
    }
    public int findKing(Board board, boolean color){
        for(int i = 0; i < board.board.length; i++){
            if((board.board[i] & 0b111) == Piece.king && Piece.getColor(board.board[i] ) == color){
                return board.board[i];
            }
        }
        return -1;
    }





}
