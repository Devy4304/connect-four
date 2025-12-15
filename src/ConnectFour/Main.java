package ConnectFour;

public class Main {
    public static void main(String[] args) {
        Utility.Console.checkForANSI();

        Game game = new Game();
        String[] modelDetails = {RandomModel.getDetails(), CustomModel.getDetails()};

        // Select model
        System.out.println("    MODEL NAME          DIFFICULTY   SPEED");
        System.out.println("===========================================");
        for (int i = 0; i < modelDetails.length; i++) {
            System.out.println("[" + (i + 1) + "] " + modelDetails[i]);
        }

        int selection = Utility.Console.getNumericalInput(1, modelDetails.length);

        switch (selection) {
            case 1 -> {
                RandomModel model = new RandomModel(game);
            }
            default -> {
                CustomModel model = new CustomModel(game);
            }
        }

        Utility.Console.printTitle();

        game.board.printBoard();
        int pos = Utility.Console.getNumericalInput(1, 7, game.board.getValidMoves(), -1);
        game.board.placePiece(game, pos - 1);

    }
}
