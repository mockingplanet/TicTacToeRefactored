import java.util.Scanner;

/**
 * @author alu10711158
 * @version 2.0
 * @since Version 1.1
 */
public class Main {

    /**
     * scanner para poder recibir los nombres
     */
    static Scanner input = new Scanner(System.in);

    /**
     * Este es el método main que imprimirá el jugador 1, 2, el que juega según el turno, el tablero y el método que comprueba si ganas o no
     * @param args el argumento
     */
    public static void main(String[] args) {
        Player player1, player2, currentPlayer;
        Board board = new Board();
        player1 = getPlayerFromKeyboard();
        player2 = getPlayerFromKeyboard();
        currentPlayer = getRandomPlayer(player1, player2);
        playGame(player1, player2, currentPlayer, board);
    }


    /**
     * Este método sirve para que un jugador pueda colocar su símbolo en una área
     * @param currentPlayer el jugador actual
     * @return la posición que el jugador ha decidido
     */
    public static Shot getShot(Player currentPlayer) {
        System.out.println(currentPlayer.getName() +
                "(" + currentPlayer.getSymbol() + ")" +
                " Enter row and col:");
        System.out.print("Row: ");
        int row = input.nextInt() - 1;
        System.out.print("Column:");
        int col = input.nextInt() - 1;
        return new Shot(row, col);
    }

    /**
     * Este método sirva para turnar a los jugadores y que puedan jugar cada vez uno
     * @param current jugador actual
     * @param player1 jugador 1
     * @param player2 jugador 2
     * @return un jugador
     */
    private static Player changePlayer(Player current, Player player1, Player player2) {
        return current == player1 ? player2 : player1;
    }

    /**
     * Este método sirve para decidir quien empieza primero al azar al poner los nombres de los dos jugadores
     * @param player1 jugador 1
     * @param player2 jugador 2
     * @return jugador aleatorio
     */
    private static Player getRandomPlayer(Player player1, Player player2) {
        int rand = (int)(Math.random() * 2);
        return rand == 0 ? player1 : player2;
    }

    /**
     * "Este método sirve para escribir y almacenar el nombre de los jugadores
     * @return el nombre del jugador
     */
    private static Player getPlayerFromKeyboard() {
        System.out.println("Enter the name of player " + (Player.getNumPlayers() == 0 ? "1" : "2"));
        String name = input.next();
        return new Player(name);
    }

    /**
     *<pre>{@code Este metodo  sirve para comprobar si el jugador a ganado o no
     * }
     *
     *</pre>
     * @param player1 jugador 1
     * @param player2 jugador 2
     * @param currentPlayer jugador actual
     * @param board el tablero
     */
    private static void playGame(Player player1, Player player2, Player currentPlayer, Board board) {
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println(board);
            Shot shot = getShot(currentPlayer);
            if (board.correctShoot(shot, currentPlayer)) {
                if (board.wins()) {
                    System.out.println(board);
                    System.out.println("WINNER: " + currentPlayer);
                    gameOver = true;
                } else {
                    if (board.checkDraw()) {
                        System.out.println(board);
                        System.out.println("IT IS A DRAW: ");
                        gameOver = true;
                    } else {
                        currentPlayer = changePlayer(currentPlayer, player1, player2);
                    }
                }
            }
        }
    }
}
