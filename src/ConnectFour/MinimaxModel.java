package ConnectFour;

/*
    This was a pain to get working.
    But, it is now decent enough, much better than the random one :)
 */


public class MinimaxModel implements Model{
    public static final String NAME = "Minimax Model";
    public static final String DIFFICULTY = "Medium";
    public static final String SPEED = "Slow";

    private static final int DEFAULT_DEPTH = 6;
    private static final int MAXIMUM = 1000000;
    private static final int MINIMUM = -1000000;

    private final Game game;

    public MinimaxModel(Game game) {
        this.game = game;
    }

    public static String getDetails() {
        String name = NAME;
        String difficulty = DIFFICULTY;
        name += " ".repeat(20 - NAME.length());
        difficulty += " ".repeat(13 - DIFFICULTY.length());
        return name + difficulty + SPEED;
    }

    @Override
    public int getMove() {
        Board currentBoard = game.board;
        java.util.List<Integer> validMoves = currentBoard.getValidMoves();

        for (int col : validMoves) {
            Board temp = new Board(currentBoard);
            temp.placePiece(col, Board.BOT);
            if (temp.checkWin(Board.BOT)) return col;
        }

        for (int col : validMoves) {
            Board temp = new Board(currentBoard);
            temp.placePiece(col, Board.PLAYER);
            if (temp.checkWin(Board.PLAYER)) return col;
        }

        int bestScore = MINIMUM;
        int bestMove = validMoves.get(validMoves.size() / 2);

        for (int col : validMoves) {
            Board temp = new Board(currentBoard);
            temp.placePiece(col, Board.BOT);
            int score = minimax(temp, DEFAULT_DEPTH - 1, MINIMUM, MAXIMUM, false);

            if (score > bestScore) {
                bestScore = score;
                bestMove = col;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        if (board.checkWin(Board.BOT)) return 1000000 - depth;
        if (board.checkWin(Board.PLAYER)) return -1000000 - depth;
        
        java.util.List<Integer> validMoves = board.getValidMoves();
        if (validMoves.isEmpty() || depth == 0) return evaluateBoard(board.getGameBoard());

        if (isMaximizing) {
            int maxEval = MINIMUM;
            for (int col : validMoves) {
                Board temp = new Board(board);
                temp.placePiece(col, Board.BOT);
                // Alternate to false
                int eval = minimax(temp, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = MAXIMUM;
            for (int col : validMoves) {
                Board temp = new Board(board);
                temp.placePiece(col, Board.PLAYER);
                int eval = minimax(temp, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        }
    }

    public int evaluateBoard(char[][] board) {
        int score = 0;

        int centerCol = Board.COLUMNS / 2;
        int centerCount = 0;
        for (int r = 0; r < Board.ROWS; r++) {
            if (board[r][centerCol] == Board.BOT) centerCount++;
        }
        score += centerCount * 3;

        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS - 3; c++) {
                char[] window = {board[r][c], board[r][c+1], board[r][c+2], board[r][c+3]};
                score += evaluateWindow(window);
            }
        }

        for (int c = 0; c < Board.COLUMNS; c++) {
            for (int r = 0; r < Board.ROWS - 3; r++) {
                char[] window = {board[r][c], board[r+1][c], board[r+2][c], board[r+3][c]};
                score += evaluateWindow(window);
            }
        }

        for (int r = 0; r < Board.ROWS - 3; r++) {
            for (int c = 0; c < Board.COLUMNS - 3; c++) {
                char[] posWindow = {board[r][c], board[r+1][c+1], board[r+2][c+2], board[r+3][c+3]};
                char[] negWindow = {board[r+3][c], board[r+2][c+1], board[r+1][c+2], board[r][c+3]};
                score += evaluateWindow(posWindow) + evaluateWindow(negWindow);
            }
        }

        return score;
    }

    private int evaluateWindow(char[] window) {
        int aiCount = 0;
        int playerCount = 0;
        int emptyCount = 0;

        for (char c : window) {
            if (c == Board.BOT) aiCount++;
            else if (c == Board.PLAYER) playerCount++;
            else if (c == Board.EMPTY) emptyCount++;
        }

        int score = 0;

        if (aiCount == 3 && emptyCount == 1) score += 500;
        if (playerCount == 3 && emptyCount == 1) score -= 500;

        if (aiCount == 2 && emptyCount == 2) score += 50;
        if (playerCount == 2 && emptyCount == 2) score -= 50;

        return score;
    }
}
