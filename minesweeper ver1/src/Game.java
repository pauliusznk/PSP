import java.beans.Visibility;
import java.util.Scanner;

public class Game {
    private static HiddenBoard hiddenBoard;
    static VisibleBoard visibleBoard;
    private static boolean gameEnd = false;
    private static int moveCount = 0;



    public static void start() {
        visibleBoard = new VisibleBoard();
        hiddenBoard = new HiddenBoard(10, 0, 0); // Initialize the hidden board with bomb
        int moveCount = 0;

        while (true) {
            visibleBoard.print(hiddenBoard.getSquare());

            if (makeMove(moveCount)) {
                break;
            }

            if (gameEnd) {
                System.out.println("Game Over");
                break;
            }

            if (gameWin()) {
                System.out.println("Game Won");
                break;
            }

            moveCount++;
        }
    }

    private static boolean makeMove(int moveCount) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Flag (F) or press (P) the tile");
        String answer = scan.nextLine();
        System.out.println("X:");
        int x = Integer.parseInt(scan.nextLine());
        System.out.println("Y:");
        int y = Integer.parseInt(scan.nextLine());

        if (!inputCheck(x, y, answer)) {
            System.out.println("Wrong input");
            return false;
        }

        if (moveCount == 0) {
            initializeHiddenBoard(x, y);
        }

        if (answer.toLowerCase().equals("f")) {
            toggleFlag(x, y);
        } else {
            revealTile(x, y);
        }

        return false;
    }

    private static void initializeHiddenBoard(int x, int y) {
        hiddenBoard = new HiddenBoard(10, x, y);
    }

    static void toggleFlag(int x, int y) {
        int[][] visibleSquare = visibleBoard.getSquare();
        visibleSquare[y][x] = (visibleSquare[y][x] == 111) ? 0 : 111;
        visibleBoard.setSquare(visibleSquare);
    }

    static void revealTile(int x, int y) {
        int[][] hiddenSquare = hiddenBoard.getSquare();
        int[][] visibleSquare = visibleBoard.getSquare();

        if (hiddenSquare[y][x] == 99) {
            gameEnd = true;
            return;
        }

        if (hiddenSquare[y][x] == 999 && moveCount > 0) {
            System.out.println("Square X: " + x + "  Y: " + y + " is already revealed.");
            return;
        }

        if (hiddenSquare[y][x] > 0 && hiddenSquare[y][x] < 9) {
            visibleSquare[y][x] = hiddenSquare[y][x];
            hiddenSquare[y][x] = 999;
        } else {
            clearTiles(x, y);
        }
    }


    public static boolean gameWin() {
        int[][] hiddenSquare = hiddenBoard.getSquare();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (hiddenSquare[i][j] != 99 && hiddenSquare[i][j] != 999) return false;
            }
        }
        return true;
    }

    public static boolean coordinatesCheck(int x, int y) {
        if (x >= 10) return false;
        if (y >= 10) return false;
        if (y < 1) return false;
        if (x < 1) return false;
        return true;
    }

    public static boolean inputCheck(int x, int y, String symbol) {
        if (!coordinatesCheck(x, y) || (!symbol.toLowerCase().equals("f") && !symbol.toLowerCase().equals("p")))
            return false;
        return true;
    }

    public static void clearTiles(int x, int y) {
        int[][] hiddenSquare = hiddenBoard.getSquare();
        int[][] visibleSquare = visibleBoard.getSquare();
        clearTile(x, y, hiddenSquare, visibleSquare);

        // Check and clear neighboring tiles
        clearAdjacentTiles(x + 1, y, hiddenSquare, visibleSquare);
        clearAdjacentTiles(x - 1, y, hiddenSquare, visibleSquare);
        clearAdjacentTiles(x, y + 1, hiddenSquare, visibleSquare);
        clearAdjacentTiles(x, y - 1, hiddenSquare, visibleSquare);
    }

    private static void clearTile(int x, int y, int[][] hiddenSquare, int[][] visibleSquare) {
        if (coordinatesCheck(x, y) && hiddenSquare[y][x] == 0) {
            visibleSquare[y][x] = hiddenSquare[y][x];
            hiddenSquare[y][x] = 999;
            setBoards(visibleSquare, hiddenSquare);
            clearTiles(x + 1, y);
            clearTiles(x - 1, y);
            clearTiles(x, y + 1);
            clearTiles(x, y - 1);
        } else if (coordinatesCheck(x, y) && hiddenSquare[y][x] > 0 && hiddenSquare[y][x] < 9) {
            visibleSquare[y][x] = hiddenSquare[y][x];
            hiddenSquare[y][x] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
    }

    private static void clearAdjacentTiles(int x, int y, int[][] hiddenSquare, int[][] visibleSquare) {
        if (coordinatesCheck(x, y) && hiddenSquare[y][x] == 0 && visibleSquare[y][x] != 999) {
            clearTile(x, y, hiddenSquare, visibleSquare);
        } else if (coordinatesCheck(x, y) && hiddenSquare[y][x] > 0 && hiddenSquare[y][x] < 9 && visibleSquare[y][x] != 999) {
            visibleSquare[y][x] = hiddenSquare[y][x];
            hiddenSquare[y][x] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
    }






    public static void setBoards(int[][] visibleSquare, int[][] hiddenSquare) {
        hiddenBoard.setSquare(hiddenSquare);
        visibleBoard.setSquare(visibleSquare);

    }
}
