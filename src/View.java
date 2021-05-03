import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class View {

    public void printMenu() {
        System.out.println(" _________  ___  ________ _________  ________  ________ _________  ________  _______      ");
        System.out.println("|\\___   ___\\\\  \\|\\   ____\\\\___   ___\\\\   __  \\|\\   ____\\\\___   ___\\\\   __  \\|\\  ___ \\     ");
        System.out.println("\\|___ \\  \\_\\ \\  \\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|    ");
        System.out.println("     \\ \\  \\ \\ \\  \\ \\  \\       \\ \\  \\ \\ \\   __  \\ \\  \\       \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|/__  ");
        System.out.println("      \\ \\  \\ \\ \\  \\ \\  \\____   \\ \\  \\ \\ \\  \\ \\  \\ \\  \\____   \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|\\ \\ ");
        System.out.println("       \\ \\__\\ \\ \\__\\ \\_______\\  \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\  \\ \\__\\ \\ \\_______\\ \\_______\\");
        System.out.println("        \\|__|  \\|__|\\|_______|   \\|__|  \\|__|\\|__|\\|_______|   \\|__|  \\|_______|\\|_______|");
        System.out.println();
    }

    public void printField(Field f) {
        int field = f.getArea();

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
