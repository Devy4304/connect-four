package ConnectFour;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private char[][] gameBoard;
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private int currentPlayer;

    public Game() {
        gameBoard = new char[ROWS][COLUMNS];
        for (char[] row : gameBoard) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 0;
    }

    public void gravity() {
        for (int row = 0; row < gameBoard.length - 1; row++) {
            for (int column = 0; column < gameBoard[0].length; column++) {
                if (gameBoard[row][column] != ' ' && gameBoard[row + 1][column] == ' ') {
                    moveTile(new Vec2(row, column), new Vec2(row + 1, column));
                }
            }
        }
    }

    public boolean verifyPosition(int column) {
        return gameBoard[0][column] == ' ';
    }

    public int getCurrentPlayer() {
        return currentPlayer;
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

    public boolean placePiece(int column) {
        if (verifyPosition(column)) {
             gameBoard[0][column] = getCharByPlayer();
             nextTurn();
             gravity();
             return true;
        } else return false;
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

    private String getColoredPlayerChar(char c, String endingColorOveride) {
        if (Utility.Console.hasANSISupport()) {
            return ((c == 'X') ? Utility.Console.Colors.RED : Utility.Console.Colors.YELLOW) + c + endingColorOveride;
        } else {
            return String.valueOf(c);
        }
    }

    private String getColoredPlayerChar(char c) {
        return ((c == 'X') ? Utility.Console.Colors.RED : Utility.Console.Colors.YELLOW) + c + Utility.Console.Colors.RESET;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer == 0) ? 1 : 0;
    }
}
