package src.utils;

import java.util.Map;
import java.util.HashMap;

public class Blinds {
    private Blinds() {} // ? Eliminar constructor

    // #region Persianes
    // ? true → Pujades
    // ? false → Baixades
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
     * Actualitza l'estat de les persianes (pujades/baixades) de l'habitació proporcionada.
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus})
     */
    public static void turnBlinds(String room, boolean newStatus) {
        // * Validacions
        if (!status.containsKey(room)) {
            return;
        }

        // Nou estat equival a l'estat equivalent.
        boolean before = status.get(room);
        String msg = Formatter.getGR(before, "pujades", "baixades");

        if (!(before ^ newStatus)) {
            System.out.printf("Les persianes d'aquesta habitació ja estan %s.%n", msg);
            return;
        }

        // * Actualització
        status.put(room, newStatus);

        msg = Formatter.getGR(!before, "pujat", "baixat");
        System.out.printf("S'ha %s les persianes d'aquesta habitació.%n", msg);
    }

    /**
     * Actualitza l'estat de les persianes (pujades/baixades) de totes les habitacions.
     */
    public static void globalTurnBlinds(boolean newState) {
        // * Actualitza tots els estats
        for (String key : status.keySet()) { // ? Itera sobre les claus de status
            status.put(key, newState);
        }

        // * Mostra missatge
        String msg = Formatter.getGR(newState, "pujat", "baixat");
        System.out.printf("S'han %s totes les persianes.%n", msg);
    }

    /**
     * Obte i escriu l'estat de les persianes.
     */
    public static void getStatus() {
        System.out.println(Formatter.getStrong("Estat de les persianes:"));

        for (String room : Menus.ROOMS) {
            String roomFormatted = Formatter.getStrong(room);

            boolean value = status.get(room);
            String state = Formatter.getGR(value, "pujades", "baixades");

            System.out.printf("%s: Persianes %s%n", roomFormatted, state);
        }
    }
    
    // #endregion
}
