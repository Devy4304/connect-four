package ConnectFour;

public class Game {
    public int currentPlayer;
    public Board board;

    public Game() {
        board = new Board();
        currentPlayer = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextTurn() {
        currentPlayer = (currentPlayer == 0) ? 1 : 0;
    }
}
