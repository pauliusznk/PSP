public class VisibleBoard{
    protected static int[][] Square = new int[10][10];
    public void print(int[][] hiddenSquare){
        printColumnHeaders();

        for (int i = 1; i < 10; i++) {
            System.out.print(i + "\t");

            for (int j = 1; j < 10; j++) {
                printCell(Square[i][j], hiddenSquare[i][j]);
            }
            System.out.print("\n");
        }
    }
    private void printColumnHeaders() {
        System.out.print("\t");
        for (int j = 1; j < 10; j++) {
            System.out.print(j + "\t");
        }
        System.out.print("\n");
    }
    private void printCell(int cellValue, int hiddenCellValue) {
        if (cellValue == 111) {
            System.out.print("F\t");
        } else if (cellValue == 0 && hiddenCellValue != 999) {
            System.out.print(" \t");
        } else {
            System.out.print(cellValue + "\t");
        }
    }
    public static int[][] getSquare() {
        return Square;
    }
    public static void setSquare(int[][] square) {
        Square = square;
    }
}
