public class Game {
    char[][] board;
    final char avail = 'X';
    final char occupied = 'E';
    public Game() {
        this.board=  new char[10][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = avail;
            }
        }
    }


    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                ret += board[i][j] + "  ";
                if (j == board.length - 1) {
                    ret += "\n";
                }
            }
        }
        return ret;
    }
}
