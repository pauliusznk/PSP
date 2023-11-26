import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VisibleBoardTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void resetBoard() {
        VisibleBoard.setSquare(new int[10][10]);
    }

    @Test
    public void testPrintWithEmptyBoard() {
        VisibleBoard board = new VisibleBoard();
        int[][] hiddenSquare = new int[10][10];

        board.print(hiddenSquare);
        String expectedOutput = "1\t2\t3\t4\t5\t6\t7\t8\t9\t\n" +
                "1\t \t \t \t \t \t \t \t \t \t\n" +
                "2\t \t \t \t \t \t \t \t \t \t\n" +
                "3\t \t \t \t \t \t \t \t \t \t\n" +
                "4\t \t \t \t \t \t \t \t \t \t\n" +
                "5\t \t \t \t \t \t \t \t \t \t\n" +
                "6\t \t \t \t \t \t \t \t \t \t\n" +
                "7\t \t \t \t \t \t \t \t \t \t\n" +
                "8\t \t \t \t \t \t \t \t \t \t\n" +
                "9"; // Define expected output for an empty board
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void testPrintWithFlaggedCells() {
        VisibleBoard board = new VisibleBoard();
        int[][] testSquare = new int[10][10];
        testSquare[1][1] = 111; // Setting a cell as flagged
        VisibleBoard.setSquare(testSquare);

        int[][] hiddenSquare = new int[10][10];

        board.print(hiddenSquare);
        String expectedOutput = "1\t2\t3\t4\t5\t6\t7\t8\t9\t\n" +
                "1\tF\t \t \t \t \t \t \t \t \t\n" +
                "2\t \t \t \t \t \t \t \t \t \t\n" +
                "3\t \t \t \t \t \t \t \t \t \t\n" +
                "4\t \t \t \t \t \t \t \t \t \t\n" +
                "5\t \t \t \t \t \t \t \t \t \t\n" +
                "6\t \t \t \t \t \t \t \t \t \t\n" +
                "7\t \t \t \t \t \t \t \t \t \t\n" +
                "8\t \t \t \t \t \t \t \t \t \t\n" +
                "9"; // Define expected output for flagged cells
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void testPrintWithZeroAndNon999HiddenCells() {
        VisibleBoard board = new VisibleBoard();
        int[][] testSquare = new int[10][10];
        testSquare[1][1] = 0; // Setting a cell as zero
        VisibleBoard.setSquare(testSquare);

        int[][] hiddenSquare = new int[10][10];
        hiddenSquare[1][1] = 100; // Non-999 hidden cell

        board.print(hiddenSquare);
        String expectedOutput = "1\t2\t3\t4\t5\t6\t7\t8\t9\t\n" +
                "1\t \t \t \t \t \t \t \t \t \t\n" +
                "2\t \t \t \t \t \t \t \t \t \t\n" +
                "3\t \t \t \t \t \t \t \t \t \t\n" +
                "4\t \t \t \t \t \t \t \t \t \t\n" +
                "5\t \t \t \t \t \t \t \t \t \t\n" +
                "6\t \t \t \t \t \t \t \t \t \t\n" +
                "7\t \t \t \t \t \t \t \t \t \t\n" +
                "8\t \t \t \t \t \t \t \t \t \t\n" +
                "9"; // Define expected output
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void testPrintWithNonSpecialCells() {
        VisibleBoard board = new VisibleBoard();
        int[][] testSquare = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                testSquare[i][j] = i * 10 + j; // Filling with distinct values
            }
        }
        VisibleBoard.setSquare(testSquare);

        int[][] hiddenSquare = new int[10][10]; // Could be filled similarly if needed

        board.print(hiddenSquare);
        String expectedOutput = "1\t2\t3\t4\t5\t6\t7\t8\t9\t\n" +
                "1\t11\t12\t13\t14\t15\t16\t17\t18\t19\t\n" +
                "2\t21\t22\t23\t24\t25\t26\t27\t28\t29\t\n" +
                "3\t31\t32\t33\t34\t35\t36\t37\t38\t39\t\n" +
                "4\t41\t42\t43\t44\t45\t46\t47\t48\t49\t\n" +
                "5\t51\t52\t53\t54\t55\t56\t57\t58\t59\t\n" +
                "6\t61\t62\t63\t64\t65\t66\t67\t68\t69\t\n" +
                "7\t71\t72\t73\t74\t75\t76\t77\t78\t79\t\n" +
                "8\t81\t82\t83\t84\t85\t86\t87\t88\t89\t\n" +
                "9\t91\t92\t93\t94\t95\t96\t97\t98\t99"; // Define expected output
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void testPrintEdgeCases() {
        VisibleBoard board = new VisibleBoard();
        int[][] testSquare = new int[10][10];
        // Setting edge cases, e.g., first and last row/column
        // Populate testSquare for edge cases here
        VisibleBoard.setSquare(testSquare);

        int[][] hiddenSquare = new int[10][10];
        // Optionally set up hiddenSquare for edge cases

        board.print(hiddenSquare);
        String expectedOutput = "1\t2\t3\t4\t5\t6\t7\t8\t9\t\n" +
                "1\t \t \t \t \t \t \t \t \t \t\n" +
                "2\t \t \t \t \t \t \t \t \t \t\n" +
                "3\t \t \t \t \t \t \t \t \t \t\n" +
                "4\t \t \t \t \t \t \t \t \t \t\n" +
                "5\t \t \t \t \t \t \t \t \t \t\n" +
                "6\t \t \t \t \t \t \t \t \t \t\n" +
                "7\t \t \t \t \t \t \t \t \t \t\n" +
                "8\t \t \t \t \t \t \t \t \t \t\n" +
                "9"; // Define expected output
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
