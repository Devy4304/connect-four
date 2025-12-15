package ConnectFour;

public class CustomModel implements Model{
    public static final String NAME = "Custom Model";
    public static final String DIFFICULTY = "Medium";
    public static final String SPEED = "Slow";

    private static final int DEFAULT_DEPTH = 6;
    private static final int MAXIMUM = Integer.MAX_VALUE / 4;
    private static final int MINIMUM = Integer.MIN_VALUE / 4;

    private final Game game;

    public CustomModel(Game game) {
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
        return 0;
    }

    /*
        To help prioritize central positions the following formula can be used:
        y=floor(-|round(x)-3|+4)

        or in code:

        Math.floor(-Math.abs(move.column - 3) + 4);
     */

    // this will be used when evaluating boards
    private int evaluateWindow(char[] window, int maximizingPlayer) {
        char maximizingChar = game.board.indexToPlayerCharacter(maximizingPlayer);
        char minimizingChar = game.board.indexToPlayerCharacter(1 - maximizingPlayer);
        int numMaximizingChar = 0;
        int numMinimizingChar = 0;

        for (char c : window) {
            if (c == maximizingChar) numMaximizingChar++;
            else if (c == minimizingChar) numMinimizingChar++;
        }

        double score = 0;

        if (numMinimizingChar == 4) return MINIMUM; // checks for win
        else if (numMaximizingChar == 4) return MAXIMUM;

        if (numMaximizingChar > 0 && numMinimizingChar == 0) {
            score += 2 * (Math.pow(2, numMaximizingChar) - 1);
        }

        if (numMinimizingChar > 0 && numMaximizingChar == 0) {
            score -= 3 * (Math.pow(2, numMinimizingChar) - 1);
        }

        return (int) score;
    }
}
