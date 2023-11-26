import java.util.Random;

public class HiddenBoard {
    protected static int[][] Square = new int[10][10];

    public HiddenBoard(int numberOfBombs, int firstMoveX, int firstMoveY) {
        fillTableWithBombs(numberOfBombs, firstMoveX, firstMoveY);
        calculateNeighbours();
    }

    public static void fillTableWithBombs(int numberOfBombs, int x, int y) {
        Random rand = new Random();
        while (numberOfBombs > 0) {
            int tempX = rand.nextInt(10);
            int tempY = rand.nextInt(10);
            if (tempX != x && tempY != y && tempX > 0 && tempY > 0 && Square[tempY][tempX] != 99) {
                Square[tempY][tempX] = 99;
                System.out.println(tempX + " " + tempY);
                numberOfBombs--;
            }
        }
    }

public void calculateNeighbours() {
    for (int row = 1; row < 10; row++) {
        for (int col = 1; col < 10; col++) {
            if (Square[col][row] == 99) {
                incrementNeighbouringCells(col, row);
            }
        }
    }
}

    private void incrementNeighbouringCells(int col, int row) {
        incrementIfValid(col + 1, row);
        incrementIfValid(col + 1, row - 1);
        incrementIfValid(col + 1, row + 1);
        incrementIfValid(col - 1, row);
        incrementIfValid(col - 1, row - 1);
        incrementIfValid(col - 1, row + 1);
        incrementIfValid(col, row - 1);
        incrementIfValid(col, row + 1);
    }

    private void incrementIfValid(int col, int row) {
        if (Game.coordinatesCheck(col, row) && Square[col][row] != 99) {
            Square[col][row]++;
        }
    }
    public static int[][] getSquare() {
        return Square;
    }

    public static void setSquare(int[][] square) {
        Square = square;
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0) System.out.print(0 + "\t");
                else if (i == 0) System.out.print(j + "\t");
                else if (j == 0) System.out.print(i + "\t");
                else if (Square[i][j] == 111) System.out.print("F" + "\t");
                else System.out.print(Square[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
