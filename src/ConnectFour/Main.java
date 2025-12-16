package ConnectFour;

public class Main {
    public static void main(String[] args) {
        Utility.Console.checkForANSI();

        Game game = new Game();
        String[] modelDetails = {RandomModel.getDetails(), MinimaxModel.getDetails()};

        // Select model
        System.out.println("    MODEL NAME          DIFFICULTY   SPEED");
        System.out.println("===========================================");
        for (int i = 0; i < modelDetails.length; i++) {
            System.out.println("[" + (i + 1) + "] " + modelDetails[i]);
        }

        int selection = Utility.Console.getNumericalInput(1, modelDetails.length);

        Model model;

        switch (selection) {
            case 1 -> {
                model = new RandomModel(game);
            }
            default -> {
                model = new MinimaxModel(game);
            }
        }

        Utility.Console.printTitle();

        game.board.printBoard();
        while (true) {
            int pos = Utility.Console.getNumericalInput(1, 7, game.board.getValidMoves(), -1);
            game.board.placePiece(pos - 1, 0);
            if (game.board.checkWin(Board.PLAYER)) {
                System.out.println("Player win");
                break;
            }
            game.board.placePiece(model.getMove(), 1);
            game.board.printBoard();
            if (game.board.checkWin(Board.BOT)) {
                System.out.println("Bot win");
                break;
            }
        }
    }
}
