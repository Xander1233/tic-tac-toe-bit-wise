import static java.lang.Integer.parseInt;

public class Controller {

    Field field;

    Controller() {
        field = new Field();
    }

    public Field getField() {
        return field;
    }

    public boolean doTurn(int[] pos, PlayerFlags currentPlayer) {
        if (pos.length != 2) throw new IndexOutOfBoundsException("'pos' must have 2 values (X and Y)");

        return this.field.Turn(pos[1] * 3 + pos[0], currentPlayer);
    }

    public void checkWin(PlayerFlags currentPlayer) {
        this.field.checkWin(currentPlayer);
    }

    public void printField() {
        int field = this.getField().getArea();

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

    public int isValidInput(String input) {
        if (input.matches("[0-2]")) return parseInt(input);
        return 3;
    }

    public static void printMenu() {
        System.out.println(" _________  ___  ________ _________  ________  ________ _________  ________  _______      ");
        System.out.println("|\\___   ___\\\\  \\|\\   ____\\\\___   ___\\\\   __  \\|\\   ____\\\\___   ___\\\\   __  \\|\\  ___ \\     ");
        System.out.println("\\|___ \\  \\_\\ \\  \\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\___\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|    ");
        System.out.println("     \\ \\  \\ \\ \\  \\ \\  \\       \\ \\  \\ \\ \\   __  \\ \\  \\       \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|/__  ");
        System.out.println("      \\ \\  \\ \\ \\  \\ \\  \\____   \\ \\  \\ \\ \\  \\ \\  \\ \\  \\____   \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\_|\\ \\ ");
        System.out.println("       \\ \\__\\ \\ \\__\\ \\_______\\  \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\  \\ \\__\\ \\ \\_______\\ \\_______\\");
        System.out.println("        \\|__|  \\|__|\\|_______|   \\|__|  \\|__|\\|__|\\|_______|   \\|__|  \\|_______|\\|_______|");
        System.out.println("");
    }
}
