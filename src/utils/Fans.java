package src.utils;

import java.util.Map;
import java.util.HashMap;

public class Fans {
    private Fans() {} // ? Eliminar constructor

    // #region Ventiladors
    public static final int MIN_SPEED = 0;
    public static final int MAX_SPEED = 2; 

    private static final Map<Integer, String> FAN_SPEEDS = new HashMap<>(Map.of(
        0, "Apagat",
        1, "Baix",
        2, "Alt"
    ));

    private static Map<String, Integer> speedMap = new HashMap<>(Map.of(
        "Sala d'estar", 0,
        "Lavabo", 0,
        "Cuina", 0,
        "h1", 0,
        "h2", 0,
        "h3", 0
    ));

    // #endregion

    // #region Mètodes de menú
    /**
     * Mostra les diferents velocitats en el format "<nivell>. <velocitat>"
     */
    public static void showSpeeds() {
        System.out.println(Formatter.getStrong("== Velocitats =="));
        for (Map.Entry<Integer, String> pair : FAN_SPEEDS.entrySet()) {
            int number = pair.getKey();
            String speed = pair.getValue();

            System.out.printf("%d. %s%n", number, speed);
        }
    }

    /**
     * Transforma el nivell de velocitat en una cadena de text amb format.
     */
    private static String formatSpeed(int speed) {
        if (speed == 0) {
            return Formatter.getGR(false, "", "apagat");
        }

        return Formatter.getGR(true, FAN_SPEEDS.get(speed).toLowerCase(), "");
    }

    // #endregion

    // #region Mètodes
    /**
     * Actualitza la velocitat del ventilador de l'habitació proporcionada.
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus}).
     */
    public static void setSpeed(String room, int newSpeed) {
        // * Validacions
        if (!FAN_SPEEDS.containsKey(newSpeed)) {
            return;
        }

        if (!speedMap.containsKey(room)) {
            return;
        }
        
        int currentSpeed = speedMap.get(room);
        String formattedSpeed = formatSpeed(newSpeed);

        if (currentSpeed == newSpeed && newSpeed == 0) {
            System.out.printf("El ventilador d'aquesta habitació ja està %s.%n", formattedSpeed);
            return;
        }

        // * Actualització
        speedMap.put(room, newSpeed);

        if (newSpeed == 0) {
            System.out.printf("S'ha %s el ventilador d'aquesta habitació.%n", formattedSpeed);
            return;
        }

        System.out.printf("La velocitat del ventilador d'aquesta habitació s'ha ajustat a %s.%n", formattedSpeed);
    }
    
    /**
     * Actualitza la velocitat dels ventiladors de totes les habitacions.
     */
    public static void setGlobalSpeed(int newSpeed) {
        // * Validacions
        if (!FAN_SPEEDS.containsKey(newSpeed)) {
            return;
        }

        // * Actualització
        for (String room : Menus.ROOMS) {
            speedMap.put(room, newSpeed);
        }

        String formattedSpeed = formatSpeed(newSpeed);
        if (newSpeed == 0) {
            System.out.printf("S'ha %s tots els ventilador.%n", formattedSpeed);
            return;
        }

        System.out.printf("La velocitat de tots els ventiladors s'ha ajustat a %s.%n", formattedSpeed);
    }

    /**
     * Obte i escriu l'estat dels ventiladors en formato "<habitació>: Ventilador".
     */
    public static void showStatus() {
        System.out.println(Formatter.getStrong("Estat dels ventiladors:"));

        for (String room : Menus.ROOMS) {
            String formattedRoom = Formatter.getStrong(room);
            int speed = speedMap.get(room);

            String formattedSpeed = formatSpeed(speed);

            if (speed == 0) {
                System.out.printf("%s: Ventilador %s%n", formattedRoom, formattedSpeed);
            } else {
                System.out.printf("%s: Nivell de velocitat --> %s%n", formattedRoom, formattedSpeed);
            }
        }
    }

    // #endregion
}
