package ConnectFour;

import java.util.List;
import java.util.Scanner;

public class Utility {
    public static class Console {
        public static class Colors {
            public static final String RESET = "\u001B[0m";
            public static final String BLACK = "\u001B[30m";
            public static final String RED = "\u001B[31m";
            public static final String GREEN = "\u001B[32m";
            public static final String YELLOW = "\u001B[33m";
            public static final String BLUE = "\u001B[34m";
            public static final String PURPLE = "\u001B[35m";
            public static final String CYAN = "\u001B[36m";
            public static final String WHITE = "\u001B[37m";
            // Background colors
            public static final String BLACK_BACKGROUND = "\u001B[40m";
            public static final String RED_BACKGROUND = "\u001B[41m";
            public static final String GREEN_BACKGROUND = "\u001B[42m";
            public static final String YELLOW_BACKGROUND = "\u001B[43m";
            public static final String BLUE_BACKGROUND = "\u001B[44m";
            public static final String PURPLE_BACKGROUND = "\u001B[45m";
            public static final String CYAN_BACKGROUND = "\u001B[46m";
            public static final String WHITE_BACKGROUND = "\u001B[47m";
        }

        private static final int boxWidth = 40;
        private static final Scanner scanner = new Scanner(System.in);
        private static boolean canDisplayANSICodes = true;
        private static boolean hasCheckedForANSI = false;

        public static void checkForANSI() {
            if (!hasCheckedForANSI) {
                Utility.Console.writeTUIBox("ANSI SUPPORT CHECK;" + "-".repeat(Utility.Console.getBoxWidth() - 4) + ";Is the following text green?;" + Utility.Console.Colors.GREEN + "HOPEFULLY GREEN TEXT" + Utility.Console.Colors.RESET + ";1) Yes;2) No", false, false);
                canDisplayANSICodes = (Utility.Console.getNumericalInput(1, 2) == 1);
                hasCheckedForANSI = true;
            }
        }

        public static boolean hasANSISupport() {
            return canDisplayANSICodes;
        }

        public static void printColorCode(String colorCode) {
            if (canDisplayANSICodes) {
                System.out.print(colorCode);
            }
        }

        public static void printTitle() {
            printColorCode(Colors.YELLOW);
            System.out.println("\n  /$$$$$$                                                      /$$   /$$   /$$\n /$$__  $$                                                    | $$  | $$  | $$\n| $$  \\__/  /$$$$$$  /$$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$| $$  | $$\n| $$       /$$__  $$| $$__  $$| $$__  $$ /$$__  $$ /$$_____/|_  $$_/| $$$$$$$$\n| $$      | $$  \\ $$| $$  \\ $$| $$  \\ $$| $$$$$$$$| $$        | $$  |_____  $$\n| $$    $$| $$  | $$| $$  | $$| $$  | $$| $$_____/| $$        | $$ /$$    | $$\n|  $$$$$$/|  $$$$$$/| $$  | $$| $$  | $$|  $$$$$$$|  $$$$$$$  |  $$$$/    | $$\n \\______/  \\______/ |__/  |__/|__/  |__/ \\_______/ \\_______/   \\___/      |__/\n\n\n");
            printColorCode(Colors.RESET);
        }

        public static int getBoxWidth() {
            return boxWidth;
        }

        public static int getNumericalInput(int min, int max) {
            System.out.println();
            int input;
            do {
                System.out.print("  => ");
                input = scanner.nextInt();
                System.out.println();
            } while (input < min || input > max);
            return input;
        }

        public static int getNumericalInput(int min, int max, List<Integer> validValues, int offset) {
            int input;
            System.out.println();
            do {
                System.out.print("  => ");
                input = scanner.nextInt();
                System.out.println();
            } while (input < min || input > max || !(validValues.contains(input + offset)));
            return input;
        }

        public static String getStringInput() {
            System.out.print("\n  => ");
            scanner.nextLine(); // Clear buffer
            String in = scanner.nextLine();
            System.out.println();
            return in;
        }

        public static void writeTUIBox(String[] innerTextSplit, boolean isBoxBelow, boolean isBoxAbove) {
            // Make the top bar, with or without connectors on top
            if (!isBoxAbove) System.out.println("┌" + repeatString("─", boxWidth - 2) + "┐");
            else System.out.println("├" + repeatString("─", boxWidth - 2) + "┤");


            for (String text : innerTextSplit) {
                // Remove ANSI codes for length calculations
                String filteredText = text.replaceAll("\u001B\\[[;\\d]*m", "");
                // Get the length
                int visibleLength = filteredText.length();
                // Crop it if necessary (should never have to, hopefully)
                if (visibleLength > boxWidth - 4) {
                    filteredText = text.replaceAll("\u001B\\[[;\\d]*m", "");
                }
                String croppedText = getCroppedText(text);
                // Print it out!
                System.out.println("│ " + croppedText + Colors.RESET + repeatString(" ", boxWidth - 4 - filteredText.length()) + " │");
            }
            // Make the bottom bar, with or without connectors on the bottom
            if (!isBoxBelow) System.out.println("└" + repeatString("─", boxWidth - 2) + "┘");
        }

        public static void writeTUIBox(String innerText, boolean isBoxBelow, boolean isBoxAbove) {
            writeTUIBox(innerText.split(";"), isBoxBelow, isBoxAbove);
        }

        private static String getCroppedText(String text) {
            StringBuilder croppedText = new StringBuilder();
            int currentLength = 0; // Current VISIBLE text length (non-ANSI code)
            int i = 0; // Index for iterating through the text

            // Run until it's the right length or done with the text
            while (i < text.length() && currentLength < Console.boxWidth - 4) {
                // Adds it to the output without increasing the currentLength if it's an ansi code
                if (text.charAt(i) == '\u001B') { // ANSI escape character, not a String
                    int codeEnd = text.indexOf('m', i); // Find where the ANSI code ends
                    if (codeEnd != -1) { // If it ends, append only the ANSI code
                        if (canDisplayANSICodes) croppedText.append(text, i, codeEnd + 1);
                        i = codeEnd + 1; // Make the index where the ANSI code ends
                        continue; // Finish this loop cycle early
                    }
                }
                croppedText.append(text.charAt(i)); // Append the letter if it's not an ANSI escape code
                currentLength++; // Increment currentLength
                i++; // Increment Index
            }
            return croppedText.toString(); // Return the output
        }

        public static String repeatString(String string, int numberOfRepeats) {
            if (numberOfRepeats > 0) {
                return string.repeat(numberOfRepeats);
            } else {
                return "";
            }
        }
    }
}
