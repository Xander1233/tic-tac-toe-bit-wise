public class Field {

    private int area = 0b0; // First 9 bits => player 1 | Next 9 bits => player 2

    private int fieldsEmpty = 0b1001;

    /**
     * Win possibility array with references to bit addresses
     */
    private int[][] winPossibilities = {
            { 0, 1, 2 },
            { 0, 4, 8 },
            { 2, 4, 6 },
            { 6, 7, 8 },
            { 3, 4, 5 },
            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 }
    };

    private WinStatusFlag winFlag = WinStatusFlag.NONE; /* => Ongoing game (No winner yet) */;

    public boolean Turn(int pos, PlayerFlags player) {

        if (!isAddressEmpty(pos)) return false;

        if (player == PlayerFlags.PLAYER_2) {
            pos += 9;
        }

        this.area += 1 << (pos);
        this.fieldsEmpty--;
        return true;
    }

    public void checkWin(PlayerFlags player) {
        boolean won = false;

        for (int[] winPossibility : this.winPossibilities) {
            won = true;

            for (int j = 0; j < winPossibility.length; j++) {

                if (this.isAddressEmpty(winPossibility[j])) {
                    won = false;
                    break;
                }

                if (player == PlayerFlags.PLAYER_1) {
                    if ((area & (1 << winPossibility[j])) == 0) {
                        won = false;
                        break;
                    }
                    continue;
                }
                if ((area & (1 << (winPossibility[j] + 9))) == 0) {
                    won = false;
                    break;
                }
            }
            if (won)
                this.winFlag =
                        player == PlayerFlags.PLAYER_1
                                ? WinStatusFlag.PLAYER_1
                                : WinStatusFlag.PLAYER_2;
        }

        if (this.fieldsEmpty == 0 && this.winFlag == WinStatusFlag.NONE)
            this.winFlag = WinStatusFlag.DRAW;
    }

    private boolean isAddressEmpty(int pos) {
        return !((this.area & (1 << pos)) > 0 || (this.area & (1 << (pos + 9))) > 0);
    }

    public WinStatusFlag getWinFlag() {
        return winFlag;
    }

    public int getArea() {
        return area;
    }
}
