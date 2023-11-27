import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GameTestUpdated {

    @Test
    public void testClearTiles_NoBombs() {
        HiddenBoard hiddenBoard = new HiddenBoard(0, 0, 0);
        VisibleBoard visibleBoard = new VisibleBoard();
        Game.setBoards(visibleBoard.getSquare(), hiddenBoard.getSquare());

        // Call the method you want to test
        Game.clearTiles(1, 1);

        // Make assertions based on the expected outcome
        // In a scenario with no bombs, clearTiles should reveal the tile at (1, 1)
        assertEquals(1, visibleBoard.getSquare()[1][1]);
    }

    public class GameTest {

        @Test
        public void testClearTiles_Bomb() throws NoSuchFieldException, IllegalAccessException {
            // Create a test scenario
            HiddenBoard hiddenBoard = new HiddenBoard(1, 0, 0); // One bomb at (0, 0)
            VisibleBoard visibleBoard = new VisibleBoard();
            Game.setBoards(visibleBoard.getSquare(), hiddenBoard.getSquare());

            // Use reflection to access the private static variable
            try {
                Field field = Game.class.getDeclaredField("gameEnd");
                field.setAccessible(true);

                // Call the method you want to test
                Game.clearTiles(1, 1);

                // Make assertions based on the expected outcome
                // In a scenario with a bomb, clearTiles should end the game
                assertTrue((boolean) field.get(null));
            } catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
            }
        }
    }

    @Test
    public void testClearTile_ZeroNeighboringBombs() {
        HiddenBoard hiddenBoard = new HiddenBoard(0, 0, 0);
        VisibleBoard visibleBoard = new VisibleBoard();
        Game.setBoards(visibleBoard.getSquare(), hiddenBoard.getSquare());

        // Call the method you want to test
        Game.clearTile(1, 1, hiddenBoard.getSquare(), visibleBoard.getSquare());

        // Make assertions based on the expected outcome
        // In a scenario with no neighboring bombs, clearTile should reveal the tile at (1, 1)
        assertEquals(1, visibleBoard.getSquare()[1][1]);
    }


    @Test
    public void testCoordinatesCheck() {
        assertTrue(Game.coordinatesCheck(5, 5));
        assertFalse(Game.coordinatesCheck(10, 5));
        assertFalse(Game.coordinatesCheck(5, 10));
        assertFalse(Game.coordinatesCheck(-1, 5));
        assertFalse(Game.coordinatesCheck(5, -1));
    }

    @Test
    public void testInputCheck() {
        assertTrue(Game.inputCheck(5, 5, "f"));
        assertTrue(Game.inputCheck(5, 5, "p"));
        assertFalse(Game.inputCheck(10, 5, "f"));
        assertFalse(Game.inputCheck(5, 10, "p"));
        assertFalse(Game.inputCheck(5, 5, "x"));
    }

    @Test
    public void testGameWin() {
        int[][] hiddenSquare = new int[10][10];
        int[][] visibleSquare = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 5 && j == 5) {
                    hiddenSquare[i][j] = 99;  // Bomb
                } else {
                    hiddenSquare[i][j] = 1;   // Non-bomb square
                    visibleSquare[i][j] = 1;  // Revealed
                }
            }
        }

        Game.setBoards(visibleSquare, hiddenSquare);
        assertFalse(Game.gameWin());
    }

    @Test
    public void testToggleFlag() {
        int[][] visibleSquare = new int[3][3];

        Game.setBoards(visibleSquare, new int[3][3]);
        Game.toggleFlag(1, 1);

        int[][] expectedVisibleSquare = {
                {0, 0, 0},
                {0, 111, 0},
                {0, 0, 0}
        };

        assertArrayEquals(expectedVisibleSquare, Game.visibleBoard.getSquare());
    }

    @Test
    public void testRevealTile() {
        int[][] hiddenSquare = {
                {0, 1, 99},
                {1, 1, 1},
                {99, 1, 0}
        };
        int[][] visibleSquare = new int[3][3];

        Game.setBoards(visibleSquare, hiddenSquare);
        Game.revealTile(1, 1);

        int[][] expectedVisibleSquare = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        assertArrayEquals(expectedVisibleSquare, Game.visibleBoard.getSquare());
    }
}
