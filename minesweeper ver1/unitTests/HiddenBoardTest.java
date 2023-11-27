import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HiddenBoardTest {

    @BeforeEach
    public void setUp() {
        HiddenBoard.setSquare(new int[10][10]);
    }

    @Test
    public void testCalculateNeighboursWithNoBombs() {
        HiddenBoard board = new HiddenBoard(0, 0, 0);
        board.calculateNeighbours();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                assertEquals(0, HiddenBoard.getSquare()[row][col], "Cell should not be incremented");
            }
        }
    }

    @Test
    public void testCalculateNeighboursWithSingleBombCenter() {
        placeBomb(5, 5);
        HiddenBoard board = new HiddenBoard(0, 0, 0);
        board.calculateNeighbours();
        checkIncrementForBomb(5, 5);
    }

//    @Test
//    public void testCalculateNeighboursWithSingleBombCorner() {
//        placeBomb(0, 0);
//        HiddenBoard board = new HiddenBoard(0, 0, 0);
//        board.calculateNeighbours();
//        checkIncrementForBomb(0, 0);
//    }

//    @Test
//    public void testCalculateNeighboursWithSingleBombEdge() {
//        placeBomb(0, 5);
//        HiddenBoard board = new HiddenBoard(0, 0, 0);
//        board.calculateNeighbours();
//        checkIncrementForBomb(0, 5);
//    }

    @Test
    public void testCalculateNeighboursWithMultipleBombs() {
        placeBomb(2, 3);
        placeBomb(7, 7);
        HiddenBoard board = new HiddenBoard(0, 0, 0);
        board.calculateNeighbours();
        checkIncrementForBomb(2, 3);
        checkIncrementForBomb(7, 7);
    }

    @Test
    public void testCalculateNeighboursWithAdjacentBombs() {
        placeBomb(4, 4);
        placeBomb(4, 5);
        HiddenBoard board = new HiddenBoard(0, 0, 0);
        board.calculateNeighbours();
        checkIncrementForBomb(4, 4);
        checkIncrementForBomb(4, 5);
    }

    private void placeBomb(int row, int col) {
        HiddenBoard.getSquare()[row][col] = 99; // 99 represents a bomb
    }

    private void checkIncrementForBomb(int bombRow, int bombCol) {
        int[][] square = HiddenBoard.getSquare();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue; // Skip the bomb cell itself
                int row = bombRow + dx;
                int col = bombCol + dy;
                if (row >= 0 && row < 10 && col >= 0 && col < 10 && square[row][col] != 99) {
                    assertTrue(square[row][col] > 0, "Cell [" + row + "," + col + "] should be incremented");
                }
            }
        }
    }
}
