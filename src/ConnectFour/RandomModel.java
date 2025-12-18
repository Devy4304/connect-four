package ConnectFour;

import java.util.ArrayList;
import java.util.Random;

import static ConnectFour.Utility.Console.colorCode;

public class RandomModel implements Model {
    public static final String NAME = "Random Model";
    public static final String DIFFICULTY = "Easy";
    public static final String SPEED = "Fast";

    private final Game game;
    private final Random rng = new Random();

    public RandomModel(Game game) {
        this.game = game;
    }

    public static String getDetails() {
        String name = NAME;
        String difficulty = DIFFICULTY;
        name += " ".repeat(20 - NAME.length());
        difficulty += " ".repeat(13 - DIFFICULTY.length());
        return name + colorCode(Utility.Console.Colors.GREEN) + difficulty + colorCode(Utility.Console.Colors.BLUE) + SPEED + colorCode(Utility.Console.Colors.RESET);
    }

    @Override
    public int getMove() {
        ArrayList<Integer> moves = game.board.getValidMoves();
        if (!moves.isEmpty()) {
            int rand = rng.nextInt(moves.size());
            return moves.get(rand);
        } else return -1;
    }
}

