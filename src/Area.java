public class Area {

    private int field = 0b0; // 1 << pos // Player 1 // 1 << (pos + 9) // Player 2

    private int fieldsEmpty = 9;

    private int[][] winPos = { { 0, 1, 2 }, { 0, 4, 8 }, { 2, 4, 6 }, { 6, 7, 8 }, { 3, 4, 5 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 } };

    private WinStatusFlag winFlag = WinStatusFlag.NONE;

    public boolean Turn(int pos, int player /* 0 = player 1 // 9 = player 2 */) {

        // Check for empty field
        if ((field & (1 << pos)) > 0 || (field & (1 << (pos + 9))) > 0) return false;

        if (player == 0) {
            field += 1 << pos;
        } else {
            field += 1 << (pos + 9);
        }

        this.fieldsEmpty--;
        return true;
    }

    public void checkWin(int player) {
        boolean won = false;

        for (int[] winPo : winPos) {
            won = true;
            for (int j = 0; j < winPo.length; j++) {
                if (player == 0) {
                    if ((field & (1 << winPo[j])) == 0) {
                        won = false;
                        break;
                    }
                } else {
                    if ((field & (1 << (winPo[j] + 9))) == 0) {
                        won = false;
                        break;
                    }
                }
            }
            if (won) {
                this.winFlag = player == 0 ? WinStatusFlag.PLAYER_1 : WinStatusFlag.PLAYER_2;
            }
        }

        if (this.fieldsEmpty == 0 && !won) this.winFlag = WinStatusFlag.DRAW;
    }

    public WinStatusFlag getWinFlag() {
        return winFlag;
    }

    public int getField() {
        return field;
    }
}
