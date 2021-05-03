import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Controller {

    Field field;
    View view;

    Controller() {
        field = new Field();
        view = new View();
    }

    public Field getField() {
        return field;
    }

    public void execute() {

        view.printMenu();

        Scanner sc = new Scanner(System.in);

        while (this.getField().getWinFlag() == WinStatusFlag.NONE) {
            System.out.println();

            for (int i = 0; i < 2 && this.getField().getWinFlag() == WinStatusFlag.NONE; i++) {

                PlayerFlags currentPlayer =
                        i == 0
                                ? PlayerFlags.PLAYER_1
                                : PlayerFlags.PLAYER_2;

                view.printField(this.field);

                System.out.println(
                        (currentPlayer == PlayerFlags.PLAYER_1
                                ? "Player 1"
                                : "Player 2"
                        )
                                + "'s turn!"
                );

                int[] pos = new int[2];

                for (int j = 0; j < pos.length; j++) {
                    int x = 3;
                    do {
                        System.out.println("Please insert a valid "
                                + (j == 0 ? "X" : "Y")
                                + " Coordinate ("
                                + (j == 0 ? "Vertical" : "Horizontal")
                                + ") (0 - 2) -> ");
                        x = this.isValidInput(sc.next());
                    } while (x > 2 || x < 0);

                    pos[j] = x;
                }

                boolean isPositionInserted = this.doTurn(pos, currentPlayer);

                if (!isPositionInserted) {
                    i--;
                    System.out.println("This field is already owned by a player! Try again");
                    continue;
                }
                this.checkWin(currentPlayer);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("---------------------- Game end ----------------------");
        System.out.println();

        view.printField(this.field);

        switch (this.getField().getWinFlag()) {
            case DRAW -> {
                System.out.println("Draw! Nobody won...");
            }
            case PLAYER_1 -> {
                System.out.println("Player 1 won! \uD83C\uDF89");
            }
            case PLAYER_2 -> {
                System.out.println("Player 2 won! \uD83C\uDF89");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("Restart the program to start a new session...");
        System.out.println("Exit...");
    }

    public boolean doTurn(int[] pos, PlayerFlags currentPlayer) {
        if (pos.length != 2) throw new IndexOutOfBoundsException("'pos' must have 2 values (X and Y)");

        return this.field.Turn(pos[1] * 3 + pos[0], currentPlayer);
    }

    public void checkWin(PlayerFlags currentPlayer) {
        this.field.checkWin(currentPlayer);
    }

    public int isValidInput(String input) {
        if (input.matches("[0-2]")) return parseInt(input);
        return 3;
    }
}
