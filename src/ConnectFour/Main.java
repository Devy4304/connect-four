package ConnectFour;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.printBoard();
        game.placePiece(0);
        game.printBoard();
        game.placePiece(1);
        game.printBoard();
        game.placePiece(0);
        game.printBoard();
        game.placePiece(2);
        game.printBoard();
    }
}
