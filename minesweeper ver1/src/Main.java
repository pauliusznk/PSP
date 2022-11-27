import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int[][] visibleSquare = new int[10][10];
    static int[][] hiddenSquare = new int[10][10];

    public static boolean coordinatesCheck(int x, int y){
        if (x >= 10) return false;
        if (y >= 10) return false;
        if (y < 1) return false;
        if (x < 1) return false;
        return true;
    }
    public static void fillTable(int numberOfBombs, int x, int y) {
        while (numberOfBombs > 0) {
            Random rand = new Random();
            int tempX = rand.nextInt(10);
            int tempY = rand.nextInt(10);
            if (tempX != x && tempY != y && tempX > 0 && tempY > 0 && hiddenSquare[tempY][tempX] != 99) {
                hiddenSquare[tempY][tempX] = 99;
                System.out.println(tempX + " " + tempY);
                numberOfBombs--;
            }
        }
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                if(hiddenSquare[j][i] == 99) {
                    if (coordinatesCheck(j + 1, i) && hiddenSquare[j + 1][i] != 99)
                        hiddenSquare[j + 1][i]++;
                    if (coordinatesCheck(j + 1, i - 1) && hiddenSquare[j + 1][i - 1] != 99)
                        hiddenSquare[j + 1][i - 1]++;
                    if (coordinatesCheck(j + 1, i + 1) && hiddenSquare[j + 1][i + 1] != 99)
                        hiddenSquare[j + 1][i + 1]++;
                    if (coordinatesCheck(j - 1, i) && hiddenSquare[j - 1][i] != 99)
                        hiddenSquare[j - 1][i]++;
                    if (coordinatesCheck(j - 1, i - 1) && hiddenSquare[j - 1][i - 1] != 99)
                        hiddenSquare[j - 1][i - 1]++;
                    if (coordinatesCheck(j - 1, i + 1) && hiddenSquare[j - 1][i + 1] != 99)
                        hiddenSquare[j - 1][i + 1]++;
                    if (coordinatesCheck(j, i - 1) && hiddenSquare[j][i - 1] != 99)
                        hiddenSquare[j][i - 1]++;
                    if (coordinatesCheck(j, i + 1) && hiddenSquare[j][i + 1] != 99)
                        hiddenSquare[j][i + 1]++;
                }
            }
        }
    }

    public static void printVisibleTable() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0) System.out.print(0 + "\t");
                else if (i == 0) System.out.print(j + "\t");
                else if (j == 0) System.out.print(i + "\t");
                else if (visibleSquare[i][j] == 111) System.out.print("F" + "\t");
                else System.out.print(visibleSquare[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void printHiddenTable() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0) System.out.print(0 + "\t");
                else if (i == 0) System.out.print(j + "\t");
                else if (j == 0) System.out.print(i + "\t");
                else if (hiddenSquare[i][j] == 111) System.out.print("F" + "\t");
                else System.out.print(hiddenSquare[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static boolean inputCheck(int x, int y, String symbol) {
        if (!coordinatesCheck(x, y) || (!symbol.toLowerCase().equals("f") && !symbol.toLowerCase().equals("p"))) return false;
        return true;
    }

    public static void clearTiles(int x, int y){
        if(hiddenSquare[y][x] != 0) {
            if(coordinatesCheck(x + 1, y) && hiddenSquare[y][x + 1] < 9){
                visibleSquare[y][x + 1] = hiddenSquare[y][x + 1];
                hiddenSquare[y][x + 1] = 999;
            }
            if(coordinatesCheck(x + 1, y - 1) && hiddenSquare[y - 1][x + 1] < 9){
                visibleSquare[y - 1][x + 1] = hiddenSquare[y - 1][x + 1];
                hiddenSquare[y - 1][x + 1] = 999;
            }
            if(coordinatesCheck(x + 1, y + 1) && hiddenSquare[y + 1][x + 1] < 9){
                visibleSquare[y + 1][x + 1] = hiddenSquare[y + 1][x + 1];
                hiddenSquare[y + 1][x + 1] = 999;
            }
            if (coordinatesCheck(x - 1, y) && hiddenSquare[y][x - 1] < 9) {
                visibleSquare[y][x - 1] = hiddenSquare[y][x - 1];
                hiddenSquare[y][x - 1] = 999;
            }
            if(coordinatesCheck(x - 1, y - 1) && hiddenSquare[y - 1][x - 1] < 9){
                visibleSquare[y - 1][x - 1] = hiddenSquare[y - 1][x - 1];
                hiddenSquare[y - 1][x - 1] = 999;
            }
            if(coordinatesCheck(x - 1, y + 1) && hiddenSquare[y + 1][x - 1] < 9){
                visibleSquare[y + 1][x - 1] = hiddenSquare[y + 1][x - 1];
                hiddenSquare[y + 1][x - 1] = 999;
            }
            if(coordinatesCheck(x, y - 1) && hiddenSquare[y - 1][x] < 9){
                visibleSquare[y - 1][x] = hiddenSquare[y - 1][x];
                hiddenSquare[y - 1][x] = 999;
            }
            if(coordinatesCheck(x, y + 1) && hiddenSquare[y + 1][x] < 9){
                visibleSquare[y + 1][x] = hiddenSquare[y + 1][x];
                hiddenSquare[y + 1][x] = 999;
            }
            return;
        }
        visibleSquare[y][x] = hiddenSquare[y][x];
        hiddenSquare[y][x] = 999;
        if (coordinatesCheck(x + 1, y)) clearTiles(x + 1, y);
        if (coordinatesCheck(x + 1, y + 1)) clearTiles(x + 1, y + 1);
        if (coordinatesCheck(x + 1, y - 1)) clearTiles(x + 1, y - 1);
        if (coordinatesCheck(x - 1, y)) clearTiles(x - 1, y);
        if (coordinatesCheck(x - 1, y + 1)) clearTiles(x - 1, y + 1);
        if (coordinatesCheck(x - 1, y - 1)) clearTiles(x - 1, y - 1);
        if (coordinatesCheck(x, y - 1)) clearTiles(x, y - 1);
        if (coordinatesCheck(x, y + 1)) clearTiles(x, y + 1);
    }
        public static boolean gameWin(){
        for(int i = 1; i < 10; i++)
        {
            for(int j = 1; j < 10; j++){
                if(hiddenSquare[i][j] == 0) return false;
            }
        }
        return true;
        }
    public static void main(String[] args) {

        boolean gameIsGoing = true;
        boolean gameEnd = false;
        int moveCount = 0;
        int numberOfBombs = 9;
        while (gameIsGoing) {

            printHiddenTable();
            printVisibleTable();
            Scanner scan = new Scanner(System.in);
            System.out.println("Flag (F) or press (P) the tile");
            String answer = scan.nextLine();
            System.out.println("X:");
            int x = Integer.parseInt(scan.nextLine());
            System.out.println("Y:");
            int y = Integer.parseInt(scan.nextLine());

            if(!inputCheck(x, y, answer)){
                System.out.println("Wrong input");
            }
            else{
                if(moveCount == 0) fillTable(numberOfBombs, x, y);
                if(answer.toLowerCase().equals("f")) visibleSquare[y][x] = 111;
                else{
                    if(hiddenSquare[y][x] == 99) gameEnd = true;
                    else if(moveCount > 0){
                        clearTiles(x, y);
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


//            Random rand = new Random();
//            int tempX = rand.nextInt(9);
//            int tempY = rand.nextInt(9);
//            if (hiddenSquare[tempY][tempX] != 99) {
//                hiddenSquare[tempY][tempX] = 99;
//                //System.out.println(tempX + " " + tempY);
//                generatedBombs++;
//            }
//        for(int i = 0; i < 9; i++){
//            for(int j = 0; j < 9; j++){
//                if(hiddenSquare[i][j] == 99)
//                {
//                    if(i > 0 && hiddenSquare[i - 1][j] != 99) hiddenSquare[i - 1][j]++;
//                    if(i < 8 && hiddenSquare[i + 1][j] != 99) hiddenSquare[i + 1][j]++;
//                    if(j > 0 && hiddenSquare[i][j - 1] != 99) hiddenSquare[i][j - 1]++;
//                    if(j < 8 && hiddenSquare[i][j + 1] != 99) hiddenSquare[i][j + 1]++;
//                    if(i > 0 && j > 0 && hiddenSquare[i - 1][j - 1] != 99) hiddenSquare[i - 1][j - 1]++;
//                    if(i > 0 && j < 8 && hiddenSquare[i - 1][j + 1] != 99) hiddenSquare[i - 1][j + 1]++;
//                    if(i < 8 && j > 0 && hiddenSquare[i + 1][j - 1] != 99) hiddenSquare[i + 1][j - 1]++;
//                    if(i < 8 && j < 8 && hiddenSquare[i + 1][j + 1] != 99) hiddenSquare[i + 1][j + 1]++;
//                }
//            }
//        }
//        while (gameIsGoing) {
//            for(int i = 0; i < 10; i++) {
//                for(int j = 0; j < 10; j++) {
//                    if(i == 0 && j == 0) System.out.print(0 + "\t");
//                    else if(i == 0) System.out.print(j + "\t");
//                    else if(j == 0) System.out.print(i + "\t");
//                    else if(visibleSquare[i - 1][j - 1] == 111) System.out.print("F" + "\t");
//                    else System.out.print(visibleSquare[i - 1][j - 1] + "\t");
//                }
//                System.out.print("\n");
//            }
//            //hidden
//            for(int i = 0; i < 10; i++) {
//                for(int j = 0; j < 10; j++) {
//                    if(i == 0 && j == 0) System.out.print(0 + "\t");
//                    else if(i == 0) System.out.print(j + "\t");
//                    else if(j == 0) System.out.print(i + "\t");
//                    else if(hiddenSquare[i - 1][j - 1] == 111) System.out.print("F" + "\t");
//                    else System.out.print(hiddenSquare[i - 1][j - 1] + "\t");
//                }
//                System.out.print("\n");
//            }
//            //hidden
//            System.out.print("\n");
//            Scanner scan = new Scanner(System.in);
//            System.out.println("Flag (F) or press (P) the tile");
//            String answer = scan.nextLine();
//            System.out.println("X:");
//            int x = Integer.parseInt(scan.nextLine());
//            System.out.println("Y:");
//            int y = Integer.parseInt(scan.nextLine());
//            if(x < 1 || y < 1 || x > 9 || y > 9 || !(answer.toLowerCase().equals("p") || answer.toLowerCase().equals("f"))) {
//                System.out.println("Wrong input");
//            }
//            else
//            {
//                if(answer.toLowerCase().equals("f")) {
//                    visibleSquare[y - 1][x - 1] = 111;
//                }
//                if(answer.toLowerCase().equals("p")) {
//                    if(hiddenSquare[y - 1][x - 1] == 99) gameEnd = true;
//                    else{
//                        clearTitles(y - 1, x - 1);
//                    }
//                }
//            }
//            if(hiddenSquare[y - 1][x - 1] == 99)
//            if (gameEnd) {
//                System.out.println("Game Over");
//                gameIsGoing = false;
//            }
//            if (gameWin) {
//                System.out.println("Game Won");
//                gameIsGoing = false;
//            }
//        }
    }
}