package ConnectFour;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public int currentPlayer;

    private final char[][] gameBoard;

    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final char PLAYER = 'X';
    public static final char BOT = 'O';
    public static final char EMPTY = ' ';

    public Board() {
        gameBoard = new char[ROWS][COLUMNS];
        currentPlayer = 0;

        for (char[] row : gameBoard) {
            Arrays.fill(row, EMPTY);
        }
    }

    public Board(Board other) {
        gameBoard = new char[ROWS][COLUMNS];
        currentPlayer = other.currentPlayer;

        for (int r = 0; r < ROWS; r++) {
            System.arraycopy(other.gameBoard[r], 0, gameBoard[r], 0, COLUMNS);
        }
    }

    public ArrayList<Integer> getValidMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            if (verifyPosition(i)) {
                moves.add(i);
            }
        }
        return moves;
    }

    public boolean verifyPosition(int column) {
        return gameBoard[0][column] == EMPTY;
    }

    public void nextTurn() {
        currentPlayer = (currentPlayer == 0) ? 1 : 0;
    }

    public void placePiece(int column) {
        if (verifyPosition(column)) {
            gameBoard[0][column] = getCharByPlayer();
            gravity();
            nextTurn();
        }
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void placePiece(int column, int player) {
        if (verifyPosition(column)) {
            gameBoard[0][column] = indexToPlayerCharacter(player);
            gravity();
            nextTurn();
        }
    }

    public void gravity() {
        boolean moved;
        do {
            moved = false;
            for (int row = 0; row < gameBoard.length - 1; row++) {
                for (int column = 0; column < gameBoard[0].length; column++) {
                    if (gameBoard[row][column] != EMPTY && gameBoard[row + 1][column] == EMPTY) {
                        moveTile(new Vec2(row, column), new Vec2(row + 1, column));
                        moved = true;
                    }
                }
            }
        } while (moved);
    }

    public boolean checkTie() {
        for (char[] row : gameBoard) {
            for (char c : row) {
                if (c == EMPTY) return false;
            }
        }
        return true;
    }

    public char indexToPlayerCharacter(int player) {
        return ((player == 0) ? PLAYER : BOT);
    }

    public boolean checkWin(char player) {
        if (player != 'X' && player != 'O') {
            throw new Error("Invalid Player");
        }

        // check horizontal
        for (char[] row : gameBoard) {
            for (int i = 0; i < COLUMNS - 3; i++) {
                if (row[i] == player && row[i + 1] == player && row[i + 2] == player && row[i + 3] == player) {
                    return true;
                }
            }
        }
        // check vertical
        for (int c = 0; c < COLUMNS; c++) {
            for (int r = 0; r < ROWS - 3; r++) {
                if (gameBoard[r][c] == player && gameBoard[r + 1][c] == player && gameBoard[r + 2][c] == player && gameBoard[r + 3][c] == player) {
                    return true;
                }
            }
        }

        // check diagonal down-right
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLUMNS - 3; c++) {
                if (gameBoard[r][c] == player && gameBoard[r + 1][c + 1] == player && gameBoard[r + 2][c + 2] == player && gameBoard[r + 3][c + 3] == player) {
                    return true;
                }
            }
        }

        // check diagonal down-left
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 3; c < COLUMNS; c++) {
                if (gameBoard[r][c] == player && gameBoard[r + 1][c - 1] == player && gameBoard[r + 2][c - 2] == player && gameBoard[r + 3][c - 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard() {
        for (int i = 1; i <= gameBoard[0].length; i++) {
            if (!verifyPosition(i - 1)) Utility.Console.printColorCode(Utility.Console.Colors.RED);
            System.out.print("   " + i + "  ");
            Utility.Console.printColorCode(Utility.Console.Colors.RESET);
        }
        Utility.Console.printColorCode(Utility.Console.Colors.BLUE);
        System.out.println("\n┌" + "─────┬".repeat(6) + "─────┐");
        int r = 0;
        for (char[] row : gameBoard) {
            r++;
            System.out.print("|");
            for (char c : row) {
                // perhaps if the position is a winning state, make the characters green?
                System.out.print("  " + getColoredPlayerChar(c, Utility.Console.Colors.BLUE) + "  |");
            }
            if (r != gameBoard.length) System.out.println("\n├─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
            else System.out.println();
        }
        System.out.println("└" + "─────┴".repeat(6) + "─────┘\n\n");
        Utility.Console.printColorCode(Utility.Console.Colors.RESET);
    }

    private void moveTile(Vec2 origPos, Vec2 newPos) {
        char temp = gameBoard[origPos.row][origPos.column];
        gameBoard[origPos.row][origPos.column] = ' ';
        gameBoard[newPos.row][newPos.column] = temp;
    }

    private char getCharByPlayer() {
        return (currentPlayer == 0) ? 'X' : 'O';
    }

    private String getColoredPlayerChar(char c, String endingColorOverride) {
        if (Utility.Console.hasANSISupport()) {
            return ((c == 'X') ? Utility.Console.Colors.RED : Utility.Console.Colors.YELLOW) + c + endingColorOverride;
        } else {
            return String.valueOf(c);
        }
    }
}
