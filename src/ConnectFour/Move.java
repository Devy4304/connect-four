package ConnectFour;

public class Move {
    public Board board;
    public int score;

    public Move(Board board) {
        this.board = new Board(board);
        this.score = -1;
    }

    public void assignScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void makeMove(int column) {
        board.placePiece(column);
    }

    public Board getBoard(boolean copy) {
        if (copy) {
            return new Board(board);
        } else {
            return board;
        }
    }
}
