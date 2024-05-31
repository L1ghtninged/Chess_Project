package AI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {
    @Test
    public void testMoveGeneration(){
        Board board = new Board(Board.startPosition);
        int result = Main.moveGenerationTest(4, board);
        assertEquals(result, 197281);

    }
    @Test
    public void testFindBestMove(){ // Testing AI to find the obvious best move
        ChessGame game = new ChessGame();
        game.playMove(new Move("e2","e4"));
        game.playMove(new Move("e7","e5"));
        game.playMove(new Move("g1", "f3"));
        game.playMove(new Move("d8", "h4"));

        Move result = AI.findBestMove(game, 4); // Taking the free queen
        assertEquals(result, new Move("f3", "h4"));

    }

    @Test
    public void testGetSquareString(){
        String result = Main.getSquare(28);
        assertEquals(result, "e4");

    }
    @Test
    public void testGetSquareIndex(){
        int result = Main.getIndex("e4");
        assertEquals(result, 28);
    }
    @Test
    public void testGetFile(){
        int result = Main.getFile(28);
        assertEquals(result, 4);
    }

}
