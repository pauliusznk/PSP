import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GameTestUpdated {

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
    public void testMakeMove() {
        // TODO: Write tests for the makeMove method
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
