public class Game {
    Tile[][] board;
    char[][] boardDisplay;
    final char avail = 'X';
    final char enemy = 'E';
    final char pc = 'C';
    final char vertWall = '|';
    final char horWall = '—';
    public Game() {
        this.board =  new Tile[10][10];
        this.boardDisplay = new char[10][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Tile(avail);
                /*
                Need to check if the Tile has a Character on it and display the appropriate char
                 */
                boardDisplay[i][j] = board[i][j].display; //create the visual field
            }
        }
    }


    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                ret += boardDisplay[i][j] + "  ";
                if (j == board.length - 1) {
                    ret += "\n";
                }
            }
        }
        return ret;
    }
}
