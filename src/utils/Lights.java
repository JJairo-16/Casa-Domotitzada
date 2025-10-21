package src.utils;

import java.util.Map;
import java.util.HashMap;

public class Lights {
    private Lights() {} // ? Eliminar constructor

    // #region Llums
    private static Map<String, Boolean> status = new HashMap<>(Map.of(
        "Sala d'estar", false,
        "Lavabo", false,
        "Cuina", false,
        "h1", false,
        "h2", false,
        "h3", false
    ));

    // #endregion

    // #region Métodes
    /**
     * Actualitza l'estat de les llums (encesa/apagada) de l'habitació proporcionada.
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus})
     */
    public static void turnLight(String room, boolean newStatus) {
        // * Validacions
        if (!status.containsKey(room)) {
            return;
        }

        // Nou estat equival a l'estat equivalent.
        boolean before = status.get(room);
        String msg = Colors.getGR(before, "enceses", "apagades");

        if (!(before ^ newStatus)) {
            System.out.printf("Les llums d'aquesta habitació ja estan %s.%n", msg);
            return;
        }

        // * Actualització
        status.put(room, newStatus);

        msg = Colors.getGR(!before, "ences", "apagat");
        System.out.printf("S'ha %s les llums d'aquesta habitació.%n", msg);
    }

    /**
     * Actualitza l'estat de les llums (enceses/apagades) de totes les habitacions.
     */
    public static void globalTurnLigths(boolean newState) {
        // * Actualitza tots els estats
        for (String key : status.keySet()) { // ? Itera sobre les claus de status
            status.put(key, newState);
        }

        // * Mostra missatge
        String msg = Colors.getGR(newState, "ences", "apagat");
        System.out.printf("S'han %s totes les llums.%n", msg);
    }

    /**
     * Obte i escriu l'estat de les llums.
     */
    public static void getStatus() {
        System.out.printf("%sEstat de les llums:%s%n", Colors.STRONG, Colors.RESET);

        for (String room : Menus.ROOMS) {
            boolean value = status.get(room);
            String state = Colors.getGR(value, "enceses", "apagades");

            System.out.printf("%s%s%s: Llums %s%n", Colors.STRONG, room, Colors.RESET, state);
        }
    }
    
    // #endregion
}
