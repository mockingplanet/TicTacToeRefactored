import java.util.Objects;

/**
 * @author alu10711158
 * @version 2.0
 * @since Version 1.1
 */
public class Board {
    /**
     * Estas son las constantes para darle la forma a el tablero
     */
    public static final String EMPTY = ".";
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;
    private String[][] board;

    /**
     * Este constructor inicia el tablero
     */
    public Board() {
        board = new String[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    /**
     * Este constructor hace que imprima el tablero
     * @return devuelve un String s con el tablero
     */
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

    /**
     * Aqui comprobamos si el shot que lanzamos en las filas y columnases válido, es decir, si ya esta ocupado o no
     * @param shot
     * @return devuelve una posición válida de las filas y columnas
     */
    public boolean isValidShot(Shot shot) {
        return shot.row >= 0 && shot.row < NUM_ROWS
                && shot.col >= 0 && shot.col < NUM_COLS;
    }

    /**
     *
     * @param shot
     * @return si la posición del tablero esta vacia o no
     */
    public boolean isPositionEmpty(Shot shot) {
        return Objects.equals(board[shot.row][shot.col], EMPTY);
    }

    /**
     *
     * @param shot
     * @param player
     * @return false si el shot no coincide con el método isValidShot o la posicion ya esta ocupada y true si la posicion no esta ocupada
     */
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


    /**
     *Este método comprueba si el jugador gana o no según como haya puesto los simbolos en las casillas
     * @return true si una columna, fila o alguna de las dos diagonales coinciden con un mismo símbolo
     */
    public boolean wins() {
        if (checkRows()) return true;
        if (checkColumns()) return true;
        if (checkDiagonal1()) return true;
        String symbol;
        return checkDiagonal2();
    }

    /**
     *Este es el método del checkRows del método anterior
     * @return true si una fila coincide en todos un símbolo
     */
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

    /**
     *Este es el método del checkColumns del método anterior
     * @return true si una columna coincide en todos un símbolo
     */
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


    /**
     *Este es el método del checkDiagonal1 del método anterior
     * @return true si diagonal izquierda coincide en todos un símbolo, si no false
     */
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

    /**
     *
     * @return true si la diagonal derecha coincide en todos un símbolo, si no false
     */
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

    /**
     *
     * @return false si no se ha producido un empate, si no true
     */

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
