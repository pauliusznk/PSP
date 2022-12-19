public class VisibleBoard{
    protected static int[][] Square = new int[10][10];

    public void print(){
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
    public static int[][] getSquare() {
        return Square;
    }

    public static void setSquare(int[][] square) {
        Square = square;
    }
}
