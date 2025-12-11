package ConnectFour;

public class Main {
    public static void main(String[] args) {
        Utility.Console.checkForANSI();
        Utility.Console.printTitle();

        Game game = new Game();

        // ---- testing code ----
        for (int i = 0; i < 7; i++) {
            game.board.placePiece(game, 0);
            game.board.printBoard();
        }
        game.board.placePiece(game, 1);
        game.board.printBoard();
    }
}
