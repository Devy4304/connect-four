package ConnectFour;

public class Main {
    public static void main(String[] args) {
        Utility.Console.checkForANSI();
        Utility.Console.printColorCode(Utility.Console.Colors.YELLOW);
        System.out.println("\n  /$$$$$$                                                      /$$   /$$   /$$\n /$$__  $$                                                    | $$  | $$  | $$\n| $$  \\__/  /$$$$$$  /$$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$| $$  | $$\n| $$       /$$__  $$| $$__  $$| $$__  $$ /$$__  $$ /$$_____/|_  $$_/| $$$$$$$$\n| $$      | $$  \\ $$| $$  \\ $$| $$  \\ $$| $$$$$$$$| $$        | $$  |_____  $$\n| $$    $$| $$  | $$| $$  | $$| $$  | $$| $$_____/| $$        | $$ /$$    | $$\n|  $$$$$$/|  $$$$$$/| $$  | $$| $$  | $$|  $$$$$$$|  $$$$$$$  |  $$$$/    | $$\n \\______/  \\______/ |__/  |__/|__/  |__/ \\_______/ \\_______/   \\___/      |__/\n\n\n");
        Utility.Console.printColorCode(Utility.Console.Colors.RESET);
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
