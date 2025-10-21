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
     * Actualitza l'estat de la llum (encesa/apagada) de l'habitació proporcionada.
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
        String msg = (before) ? "encendreda" : "apagada";

        if (!(before ^ newStatus)) {
            System.out.printf("La llum d'aquesta habitació ja está %s.%n", msg);
            return;
        }

        // * Actualització
        status.put(room, newStatus);

        msg = (before) ? "enceses" : "encendret";
        System.out.printf("S'ha %s la llum d'aquesta habitació.%n", msg);
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
        String msg = (newState) ? "encendret" : "enceses";
        System.out.printf("S'han %s totes les llums.%n", msg);
    }

    /**
     * Obte i escriu l'estat de les llums.
     */
    public static void getStatus() {
        System.out.println("Estat de les llums:");

        for (String room : Menus.ROOMS) { // ? Itera sobre l'array de llums
            boolean value = status.get(room);
            String state = (value) ? "encendredes" : "apagades";

            System.out.printf("%s: Llums %s%n", room, state);
        }
    }
    
    // #endregion
}
