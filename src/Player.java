/**
 * @author alu10711158
 * @version 2.0
 * @since Version 1.1
 */
public class Player {
    /**
     * Estas son variables para el jugador
     */
    private String name;

    /**
     * Variable del símbolo
     */
    private String symbol;

    /**
     * variable del nombre de jugadores
     */
    private static int numPlayers = 0;

    /**
     * Inicializamos las variables en un constructor
     * @param name nombre del jugador
     */
    public Player(String name) {
        this.name = name;
        if (numPlayers == 0) {
            symbol = "O";
        } else {
            symbol = "X";
        }
        numPlayers ++;
    }

    /**
     * Es un getter del nombre
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Método toString para imprimir el nombre del jugador
     * @return name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * getter del simbolo
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * método que devuelve el número de jugadores
     * @return numPlayers
     */
    public static int getNumPlayers() {
        return numPlayers;
    }
}

