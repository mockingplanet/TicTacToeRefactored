import java.util.Objects;

public class Board {
    public static final String EMPTY = ".";
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;
    private String[][] board;

    public Board() {
        board = new String[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    @Override
    public String toString() {
        String s = "  ";
        for (int i = 1; i <= board[0].length; i++) {
            s += i + " ";
        }
        s += "\n";

        for (int row = 0; row < board.length; row++) {
            s += (row + 1) + " ";
            for (int col = 0; col < board[0].length; col++) {
                s += board[row][col] + " ";
            }
            s += "\n";
        }
        return s;
    }
    /*
        public boolean correctShoot(Shot shot, Player player) {
            if (shot.row < 0 || shot.row >= NUM_ROWS ||
                    shot.col < 0 || shot.col >= NUM_COLS) {
                System.out.println("Wrong ROW or COL");
                return false;
            }
            String symbol = player.getSymbol();

            if (Objects.equals(board[shot.row][shot.col], EMPTY)) {
                board[shot.row][shot.col] = symbol;
                return true;
            } else {
                System.out.println("Position already in use");
                return false;
            }
        }
     */
    public boolean isValidShot(Shot shot) {
        return shot.row >= 0 && shot.row < NUM_ROWS
                && shot.col >= 0 && shot.col < NUM_COLS;
    }
    public boolean isPositionEmpty(Shot shot) {
        return Objects.equals(board[shot.row][shot.col], EMPTY);
    }
    public boolean correctShoot(Shot shot, Player player) {
        if (!isValidShot(shot)) {
            System.out.println("Wrong ROW or COL");
            return false;
        }
        String symbol = player.getSymbol();
        if (isPositionEmpty(shot)) {
            board[shot.row][shot.col] = symbol;
            return true;
        } else {
            System.out.println("Position already in use");
            return false;
        }
    }


    public boolean wins() {
        // Check rows
        if (checkRows()) return true;
        // Check columns
        if (checkColumns()) return true;

        // Check diagonal 1
        if (checkDiagonal1()) return true;
        String symbol;

        // Check diagonal 2
        return checkDiagonal2();
    }

    private boolean checkRows() {
        for (int row = 0; row < board.length; row++) {
            String symbol = board[row][0];
            if (symbol != EMPTY) {
                int count = 1;
                for (int col = 1; col < board[0].length; col++) {
                    if (Objects.equals(board[row][col], symbol)) {
                        count++;
                    }
                }
                if (count == NUM_COLS) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < board[0].length; col++) {
            String symbol = board[0][col];
            if (symbol != EMPTY) {
                int count = 1;
                for (int row = 1; row < board.length; row++) {
                    if (board[row][col] == symbol) {
                        count++;
                    }
                }
                if (count == NUM_ROWS) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean checkDiagonal1() {
        String symbol = board[0][0];
        if (!Objects.equals(symbol, EMPTY)) {
            int count = 1;
            for (int i = 1; i < board.length; i++) {
                if (board[i][i] == symbol) {
                    count++;
                }
            }
            if (count == NUM_ROWS) {
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonal2() {
        String symbol;
        symbol = board[0][NUM_COLS - 1];
        if (symbol != EMPTY) {
            int count = 1;
            int row = 1;
            for (int i = NUM_COLS - 2; i >= 0; i--) {
                if (board[row][i] == symbol) {
                    count++;
                    row ++;
                }
            }
            if (count == NUM_COLS) {
                return true;
            }
        }
        return false;
    }


    public boolean checkDraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }


}
