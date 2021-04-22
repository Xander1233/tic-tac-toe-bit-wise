import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Area area = new Area();

        WinStatusFlag winFlag = area.getWinFlag();

        System.out.println(" _________  ___  ________ _________  ________  ________ _________  ________  _______      ");
        System.out.println("|\\___   ___\\\\  \\|\\   ____\\\\___   ___\\\\   __  \\|\\   ____\\\\___   ___\\\\   __  \\|\\  ___ \\     ");
        System.out.println("\\|___ \\  \\_\\ \\  \\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|    ");
        System.out.println("     \\ \\  \\ \\ \\  \\ \\  \\       \\ \\  \\ \\ \\   __  \\ \\  \\       \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|/__  ");
        System.out.println("      \\ \\  \\ \\ \\  \\ \\  \\____   \\ \\  \\ \\ \\  \\ \\  \\ \\  \\____   \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|\\ \\ ");
        System.out.println("       \\ \\__\\ \\ \\__\\ \\_______\\  \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\  \\ \\__\\ \\ \\_______\\ \\_______\\");
        System.out.println("        \\|__|  \\|__|\\|_______|   \\|__|  \\|__|\\|__|\\|_______|   \\|__|  \\|_______|\\|_______|");
        System.out.println("");

        Scanner sc = new Scanner(System.in);

        while (area.getWinFlag() == WinStatusFlag.NONE) {
            System.out.println("");

            for (int i = 0; i < 2 && area.getWinFlag() == WinStatusFlag.NONE; i++) {
                printField(area);
                System.out.println((i == 0 ? "Player 1" : "Player 2") + "'s turn");
                int x = 3;
                do {
                    System.out.println("Please insert a valid X Coordinate (0 - 2) -> ");
                    x = checkValidInput(sc.nextLine());
                } while (x > 3 || x < 0);
                int y = 3;
                do {
                    System.out.println("Please insert a valid Y Coordinate (0 - 2) -> ");
                    y = checkValidInput(sc.nextLine());
                } while (y > 3 || y < 0);
                boolean insertFieldResult = area.Turn(y * 3 + x, i == 0 ? 0 : 9);
                if (!insertFieldResult) {
                    i = i - 1;
                    System.out.println("This field is already owned by a player! Try again");
                }
                area.checkWin(i == 0 ? 0 : 9);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("---------------------- Game end ----------------------");

        System.out.println();

        printField(area);

        switch (area.getWinFlag()) {
            case DRAW -> {
                System.out.println("Draw! Nobody won.");
            }
            case PLAYER_1 -> {
                System.out.println("Player 1 won!");
            }
            case PLAYER_2 -> {
                System.out.println("Player 2 won!");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("Restart the program to start a new session");
    }

    public static int checkValidInput(String input) {
        if (input.matches("[0-2]")) return parseInt(input);
        return 4;
    }

    public static void printField(Area area) {

        int field = area.getField();

        System.out.println("      0  |  1  |  2  ");
        System.out.print("   __________________");

        for (int i = 0; i < 9; i++) {
            if ((i + 3) % 3 == 0) System.out.print("\n" + (i / 3) + "  ");
            String output = "-";

            if ((field & (1 << i)) > 0) output = "O";
            else if ((field & (1 << (i + 9))) > 0) output = "X";

            System.out.print("|  " + output + "  ");

            if ((i + 1) % 3 == 0) System.out.print("|\n   ------------------");
        }

        System.out.println();
        System.out.println();
    }
}
