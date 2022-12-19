import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @org.junit.jupiter.api.Test
    void coordinatesCheckRight() {
        Game game = new Game();
        int x, y;
        x = 1;
        y = 1;
        assertEquals(true, game.coordinatesCheck(x, y));
    }

    @org.junit.jupiter.api.Test
    void coordinatesCheckWrong1() {
        Game game = new Game();
        int x, y;
        x = 0;
        y = 10;
        assertEquals(false, game.coordinatesCheck(x, y));
    }

    @org.junit.jupiter.api.Test
    void coordinatesCheckWrong2() {
        Game game = new Game();
        int x, y;
        x = 2;
        y = 10;
        assertEquals(false, game.coordinatesCheck(x, y));
    }

    void inputCheckCorrect() {
        Game game = new Game();
        String input = "P";
        int x = 2;
        int y = 2;
        assertEquals(false, game.inputCheck(x, y, input));
    }

    @org.junit.jupiter.api.Test
    void inputCheckWrong() {
        Game game = new Game();
        String input = "s";
        int x = 2;
        int y = 2;
        assertEquals(false, game.inputCheck(x, y, input));
    }

    @org.junit.jupiter.api.Test
    void gameWinTest() {
        Game game = new Game();
        assertEquals(false, game.gameWin());
    }

}