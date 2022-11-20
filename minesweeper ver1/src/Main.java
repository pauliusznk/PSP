import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int[][] visibleSquare = new int[9][9];
    static int[][] hiddenSquare = new int[9][9];
    public static void clearTitles(int y, int x){
        if(hiddenSquare[y][x] != 99 && hiddenSquare[y][x] != 111){
            visibleSquare[y][x] = visibleSquare[y][x];
            hiddenSquare[y][x] = 999;
            if(y > 1 && hiddenSquare[y - 1][x] < 9)clearTitles(y - 1, x);
            if(y > 1 && x > 1 && hiddenSquare[y - 1][x - 1] < 9)clearTitles(y - 1, x - 1);
            if(y > 1 && x < 8 && hiddenSquare[y - 1][x + 1] < 9)clearTitles(y - 1, x + 1);
            if(y < 8 && hiddenSquare[y + 1][x] < 9)clearTitles(y + 1, x);
            if(y < 8 && x > 1 && hiddenSquare[y + 1][x - 1] < 9)clearTitles(y + 1, x - 1);
            if(y < 8 && x < 8 && hiddenSquare[y + 1][x + 1] < 9)clearTitles(y + 1, x + 1);
            if(x > 1 && hiddenSquare[y][x - 1] < 9)clearTitles(y, x - 1);
            if(x < 8 && hiddenSquare[y][x + 1] < 9)clearTitles(y, x + 1);
        }
    }
    public static void main(String[] args) {

        boolean gameIsGoing = true;
        boolean gameEnd = false;
        boolean gameWin = false;

        int generatedBombs = 0;
        while(generatedBombs < 10)
        {
            Random rand = new Random();
            int tempX = rand.nextInt(9);
            int tempY = rand.nextInt(9);
            if (hiddenSquare[tempY][tempX] != 99) {
                hiddenSquare[tempY][tempX] = 99;
                //System.out.println(tempX + " " + tempY);
                generatedBombs++;
            }
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(hiddenSquare[i][j] == 99)
                {
                    if(i > 0 && hiddenSquare[i - 1][j] != 99) hiddenSquare[i - 1][j]++;
                    if(i < 8 && hiddenSquare[i + 1][j] != 99) hiddenSquare[i + 1][j]++;
                    if(j > 0 && hiddenSquare[i][j - 1] != 99) hiddenSquare[i][j - 1]++;
                    if(j < 8 && hiddenSquare[i][j + 1] != 99) hiddenSquare[i][j + 1]++;
                    if(i > 0 && j > 0 && hiddenSquare[i - 1][j - 1] != 99) hiddenSquare[i - 1][j - 1]++;
                    if(i > 0 && j < 8 && hiddenSquare[i - 1][j + 1] != 99) hiddenSquare[i - 1][j + 1]++;
                    if(i < 8 && j > 0 && hiddenSquare[i + 1][j - 1] != 99) hiddenSquare[i + 1][j - 1]++;
                    if(i < 8 && j < 8 && hiddenSquare[i + 1][j + 1] != 99) hiddenSquare[i + 1][j + 1]++;
                }
            }
        }
        while (gameIsGoing) {
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    if(i == 0 && j == 0) System.out.print(0 + "\t");
                    else if(i == 0) System.out.print(j + "\t");
                    else if(j == 0) System.out.print(i + "\t");
                    else if(visibleSquare[i - 1][j - 1] == 111) System.out.print("F" + "\t");
                    else System.out.print(visibleSquare[i - 1][j - 1] + "\t");
                }
                System.out.print("\n");
            }
            //hidden
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    if(i == 0 && j == 0) System.out.print(0 + "\t");
                    else if(i == 0) System.out.print(j + "\t");
                    else if(j == 0) System.out.print(i + "\t");
                    else if(hiddenSquare[i - 1][j - 1] == 111) System.out.print("F" + "\t");
                    else System.out.print(hiddenSquare[i - 1][j - 1] + "\t");
                }
                System.out.print("\n");
            }
            //hidden
            System.out.print("\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("Flag (F) or press (P) the tile");
            String answer = scan.nextLine();
            System.out.println("X:");
            int x = Integer.parseInt(scan.nextLine());
            System.out.println("Y:");
            int y = Integer.parseInt(scan.nextLine());
            if(x < 1 || y < 1 || x > 9 || y > 9 || !(answer.toLowerCase().equals("p") || answer.toLowerCase().equals("f"))) {
                System.out.println("Wrong input");
            }
            else
            {
                if(answer.toLowerCase().equals("f")) {
                    visibleSquare[y - 1][x - 1] = 111;
                }
                else {
                    if(hiddenSquare[y - 1][x - 1] == 99) gameEnd = true;
                    else if (hiddenSquare[y - 1][x - 1] == 0){
                        clearTitles(y - 1, x - 1);
                    }
                    else {
                        visibleSquare[y - 1][x - 1] = hiddenSquare[y - 1][x - 1];
                    }
                }
            }
            if(hiddenSquare[y - 1][x - 1] == 99)
            if (gameEnd) {
                System.out.println("Game Over");
                gameIsGoing = false;
            }
            if (gameWin) {
                System.out.println("Game Won");
                gameIsGoing = false;
            }
        }
    }
}