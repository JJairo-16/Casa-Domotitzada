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
    public static void turnLight(String room, boolean newStatus) {
        // * Validacions
        if (!status.containsKey(room)) {
            return;
        }

        boolean before = status.get(room);
        String msg = (before) ? "engegada" : "apagada";

        if (!(before ^ newStatus)) {
            System.out.printf("La llum d'aquesta habitació ja está %s.%n", msg);
            return;
        }

        // * Actualització
        status.put(room, newStatus);

        msg = (before) ? "apagat" : "engegat";
        System.out.printf("S'ha %s la llum d'aquesta habitació.%n", msg);
    }

    public static void globalTurnLigths(boolean newState) {
        for (String key : status.keySet()) {
            status.put(key, newState);
        }

        String msg = (newState) ? "engegat" : "apagat";
        System.out.printf("S'han %s totes les llums.%n", msg);
    }

    public static void getStatus() {
        System.out.println("Estat de les llums:");
        for (String room : Menus.ROOMS) {
            boolean value = status.get(room);
            String state = (value) ? "engegades" : "apagades";

            System.out.printf("%s: Llums %s%n", room, state);
        }
    }
    
    // #endregion
}
