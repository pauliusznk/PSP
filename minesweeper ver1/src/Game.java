import java.beans.Visibility;
import java.util.Scanner;

public class Game {
    private static HiddenBoard hiddenBoard;
    private static VisibleBoard visibleBoard;

    public static void start() {
        visibleBoard = new VisibleBoard();
        int[][] hiddenSquare = hiddenBoard.getSquare();
        int[][] visibleSquare = visibleBoard.getSquare();
        boolean gameIsGoing = true;
        boolean gameEnd = false;
        int moveCount = 0;
        int numberOfBombs = 10;
        while (gameIsGoing) {
            visibleBoard.print(hiddenSquare);
            Scanner scan = new Scanner(System.in);
            System.out.println("Flag (F) or press (P) the tile");
            String answer = scan.nextLine();
            System.out.println("X:");
            int x = Integer.parseInt(scan.nextLine());
            System.out.println("Y:");
            int y = Integer.parseInt(scan.nextLine());

            if (!inputCheck(x, y, answer)) {
                System.out.println("Wrong input");
            } else {
                if (moveCount == 0) {
                    hiddenBoard = new HiddenBoard(numberOfBombs, x, y);
                    if (hiddenSquare[y][x] > 0 && hiddenSquare[y][x] < 9) {
                        visibleSquare[y][x] = hiddenSquare[y][x];
                        hiddenSquare[y][x] = 999;
                    } else {
                        clearTiles(x, y);
                    }
                }
                if (answer.toLowerCase().equals("f")) {
                    if (visibleSquare[y][x] == 111) visibleSquare[y][x] = 0;
                    else visibleSquare[y][x] = 111;
                } else {
                    if (hiddenSquare[y][x] == 99) gameEnd = true;
                    else if (hiddenSquare[y][x] == 999 && moveCount > 0) {
                        System.out.println("Square X: " + x + "  Y: " + y + " is already revealed.");
                    } else if (moveCount > 0) {
                        if (hiddenSquare[y][x] > 0 && hiddenSquare[y][x] < 9) {
                            visibleSquare[y][x] = hiddenSquare[y][x];
                            hiddenSquare[y][x] = 999;
                        } else {
                            clearTiles(x, y);
                        }
                    }
                }
            }

            if (gameEnd) {
                System.out.println("Game Over");
                gameIsGoing = false;
            }
            if (gameWin()) {
                System.out.println("Game Won");
                gameIsGoing = false;
            }
            moveCount++;
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
        visibleSquare[y][x] = hiddenSquare[y][x];
        hiddenSquare[y][x] = 999;
        setBoards(visibleSquare, hiddenSquare);
        if (coordinatesCheck(x + 1, y) && hiddenSquare[y][x + 1] > 0 && hiddenSquare[y][x + 1] < 9) {
            visibleSquare[y][x + 1] = hiddenSquare[y][x + 1];
            hiddenSquare[y][x + 1] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x + 1, y + 1) && hiddenSquare[y + 1][x + 1] > 0 && hiddenSquare[y + 1][x + 1] < 9) {
            visibleSquare[y + 1][x + 1] = hiddenSquare[y + 1][x + 1];
            hiddenSquare[y + 1][x + 1] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x + 1, y - 1) && hiddenSquare[y - 1][x + 1] > 0 && hiddenSquare[y - 1][x + 1] < 9) {
            visibleSquare[y - 1][x + 1] = hiddenSquare[y - 1][x + 1];
            hiddenSquare[y - 1][x + 1] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x - 1, y) && hiddenSquare[y][x - 1] > 0 && hiddenSquare[y][x - 1] < 9) {
            visibleSquare[y][x - 1] = hiddenSquare[y][x - 1];
            hiddenSquare[y][x - 1] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x - 1, y + 1) && hiddenSquare[y + 1][x - 1] > 0 && hiddenSquare[y + 1][x - 1] < 9) {
            visibleSquare[y + 1][x - 1] = hiddenSquare[y + 1][x - 1];
            hiddenSquare[y + 1][x - 1] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x - 1, y - 1) && hiddenSquare[y - 1][x - 1] > 0 && hiddenSquare[y - 1][x - 1] < 9) {
            visibleSquare[y - 1][x - 1] = hiddenSquare[y - 1][x - 1];
            hiddenSquare[y - 1][x - 1] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x, y - 1) && hiddenSquare[y - 1][x] > 0 && hiddenSquare[y - 1][x] < 9) {
            visibleSquare[y - 1][x] = hiddenSquare[y - 1][x];
            hiddenSquare[y - 1][x] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x, y + 1) && hiddenSquare[y + 1][x] > 0 && hiddenSquare[y + 1][x] < 9) {
            visibleSquare[y + 1][x] = hiddenSquare[y + 1][x];
            hiddenSquare[y + 1][x] = 999;
            setBoards(visibleSquare, hiddenSquare);
        }
        if (coordinatesCheck(x + 1, y) && hiddenSquare[y][x + 1] == 0) clearTiles(x + 1, y);
        if (coordinatesCheck(x + 1, y + 1) && hiddenSquare[y + 1][x + 1] == 0) clearTiles(x + 1, y + 1);
        if (coordinatesCheck(x + 1, y - 1) && hiddenSquare[y - 1][x + 1] == 0) clearTiles(x + 1, y - 1);
        if (coordinatesCheck(x - 1, y) && hiddenSquare[y][x - 1] == 0) clearTiles(x - 1, y);
        if (coordinatesCheck(x - 1, y + 1) && hiddenSquare[y + 1][x - 1] == 0) clearTiles(x - 1, y + 1);
        if (coordinatesCheck(x - 1, y - 1) && hiddenSquare[y - 1][x - 1] == 0) clearTiles(x - 1, y - 1);
        if (coordinatesCheck(x, y - 1) && hiddenSquare[y - 1][x] == 0) clearTiles(x, y - 1);
        if (coordinatesCheck(x, y + 1) && hiddenSquare[y + 1][x] == 0) clearTiles(x, y + 1);
    }

    public static void setBoards(int[][] visibleSquare, int[][] hiddenSquare) {
        hiddenBoard.setSquare(hiddenSquare);
        visibleBoard.setSquare(visibleSquare);

    }
}
