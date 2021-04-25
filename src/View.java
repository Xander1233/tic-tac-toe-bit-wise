import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class View {
    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.printMenu();

        Scanner sc = new Scanner(System.in);

        while (controller.getField().getWinFlag() == WinStatusFlag.NONE) {
            System.out.println();

            for (int i = 0; i < 2 && controller.getField().getWinFlag() == WinStatusFlag.NONE; i++) {

                PlayerFlags currentPlayer =
                        i == 0
                                ? PlayerFlags.PLAYER_1
                                : PlayerFlags.PLAYER_2;

                controller.printField();

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
                        x = controller.isValidInput(sc.next());
                    } while (x > 2 || x < 0);

                    pos[j] = x;
                }

                boolean isPositionInserted = controller.doTurn(pos, currentPlayer);

                if (!isPositionInserted) {
                    i--;
                    System.out.println("This field is already owned by a player! Try again");
                    continue;
                }
                controller.checkWin(currentPlayer);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("---------------------- Game end ----------------------");
        System.out.println();

        controller.printField();

        switch (controller.getField().getWinFlag()) {
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
}
