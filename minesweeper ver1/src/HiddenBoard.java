import java.util.Random;

public class HiddenBoard {
    protected static int[][] Square = new int[10][10];

    public HiddenBoard(int numberOfBombs, int firstMoveX, int firstMoveY) {
        fillTableWithBombs(numberOfBombs, firstMoveX, firstMoveY);
        calculateNeighbours();
    }

    public static void fillTableWithBombs(int numberOfBombs, int x, int y) {
        while (numberOfBombs > 0) {
            Random rand = new Random();
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
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (Square[j][i] == 99) {
                    if (Game.coordinatesCheck(j + 1, i) && Square[j + 1][i] != 99)
                        Square[j + 1][i]++;
                    if (Game.coordinatesCheck(j + 1, i - 1) && Square[j + 1][i - 1] != 99)
                        Square[j + 1][i - 1]++;
                    if (Game.coordinatesCheck(j + 1, i + 1) && Square[j + 1][i + 1] != 99)
                        Square[j + 1][i + 1]++;
                    if (Game.coordinatesCheck(j - 1, i) && Square[j - 1][i] != 99)
                        Square[j - 1][i]++;
                    if (Game.coordinatesCheck(j - 1, i - 1) && Square[j - 1][i - 1] != 99)
                        Square[j - 1][i - 1]++;
                    if (Game.coordinatesCheck(j - 1, i + 1) && Square[j - 1][i + 1] != 99)
                        Square[j - 1][i + 1]++;
                    if (Game.coordinatesCheck(j, i - 1) && Square[j][i - 1] != 99)
                        Square[j][i - 1]++;
                    if (Game.coordinatesCheck(j, i + 1) && Square[j][i + 1] != 99)
                        Square[j][i + 1]++;
                }
            }
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
