package ConnectFour;

public class Main {
    public static void main(String[] args) {
        Utility.Console.checkForANSI();
        Utility.Console.printTitle();

        Game game = new Game();

        // ---- testing code ----
        for (int i = 0; i < 7; i++) {
            game.placePiece(0);
            game.printBoard();
        }
        game.placePiece(1);
        game.printBoard();
    }
}
