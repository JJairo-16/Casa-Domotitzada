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

        status.put(room, newStatus);

        msg = (before) ? "apagat" : "engegat";
        System.out.printf("S'ha %s la llum d'aquesta habitació.%n", msg);
    }
    
    // #endregion
}
