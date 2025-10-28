package src.utils;

import java.util.Map;
import java.util.HashMap;

public class Thermostat {
    private Thermostat() {}  // ? Eliminar el constructor

    // * Regles
    public static final int MIN_TEMPERATURE = 10;
    public static final int MAX_TEMPERATURE = 30;

    // #region Termòstats
    private static Map<String, Boolean> status = new HashMap<>(Map.of(
        "Sala d'estar", true,
        "Lavabo", true,
        "Cuina", true,
        "h1", true,
        "h2", true,
        "h3", true
    ));

    private static Map<String, Integer> temperature = new HashMap<>(Map.of(
        "Sala d'estar", 24,
        "Lavabo", 24,
        "Cuina", 24,
        "h1", 24,
        "h2", 24,
        "h3", 24
    ));

    // #endregion

    public static boolean getStatus(String room) {
        return status.get(room);
    }

    /**
     * Dona format a la temperatura amb color blau, negreta i afegint "ºC".
     * 
     * @param room Habitació de la qual s'utilitza la temperatura.
     */
    private static String formatTemperature(String room) {
        int temperatureRoom = temperature.get(room);
        return Formatter.STRONG + Formatter.BLUE + temperatureRoom + "ºC" + Formatter.RESET;
    }

    // #region Mètodes individuals
    /**
     * Actualitza l'estat (encès/apagat) del termòstat.
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus}).
     */
    public static void turnThermostat(String room, boolean newState) {
        // * Validacions
        if (!status.containsKey(room)) {
            return;
        }

        boolean currentState = status.get(room);
        String msg;

        // Nou estat equival a l'estat actual.
        if (!(currentState ^ newState)) {
            msg = Formatter.getGR(currentState, "encès", "apagat");
            System.out.printf("El termòstat d'aquesta habitació ja està %s.%n", msg);
            return;
        }

        // * Actualització
        status.put(room, newState);

        msg = Formatter.getGR(newState, "encès", "apagat");
        System.out.printf("S'ha %s el termòstat d'aquesta habitació.%n", msg);
    }

    /**
     * Canvia la temperatura del termòstat ({@value Thermostat#MIN_TEMPERATURE}-{@value Thermostat#MAX_TEMPERATURE}ºC).
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus}).
     */
    public static void changeTemperature(String room, int newTemperature) {
        // * Validacions
        if (!status.containsKey(room)) {
            return;
        }

        String msg;
        boolean currentState = status.get(room);
        if (!currentState) { // ? Está apagat
            msg = Formatter.getGR(false, "", "apagat");
            System.out.printf("No es pot canviar la temperatura; el termòstat d'aquesta habitació està %s.%n", msg);
            return;
        }

        if (newTemperature < MIN_TEMPERATURE || newTemperature > MAX_TEMPERATURE) { // ? Temperatura fora del rang
            return;
        }

        // * Actualització
        temperature.put(room, newTemperature);

        msg = formatTemperature(room);
        System.out.printf("Sha establit la temperatura en %s.%n", msg);
    }

   /**
     * Mostra l'estat del termòstat (encès/apagat) - (temperatura).
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus}).
     */
    public static void showThermStatus(String room) {
        // * Declaració de variables
        String msg;

        boolean currentState;
        String temperatureString;
        
        // * Cap
        msg = String.format("Estat del termòstat (%s):%n", room);
        System.out.println(Formatter.getStrong(msg));

        // * Cos
        currentState = status.get(room);
        msg = Formatter.getGR(currentState, "Encès", "Apagat");
        temperatureString = formatTemperature(room);
        
        System.out.printf("%s - %s.%n", msg, temperatureString);
    }

    // #endregion

    // #region Mètodes globals
    /**
     * Actualitza l'estat (encès/apagat) de tots els termòstats.
     */
    public static void turnAllThermostats(boolean newState) {
        for (String room : Menus.ROOMS) {
            status.put(room, newState);
        }

        String msg = Formatter.getGR(newState, "encès", "apagat");
        System.out.printf("S'ha %s tots els termòstats.%n", msg);
    }

    /**
     * Mostra l'estat de tots els termòstats (encès/apagat) - (temperatura).
     */
    public static void showAllThermsStatus() {
        // * Declaració de variables
        String msg;
        String roomFormatted;

        boolean currentState;
        String temperatureString;
        
        // * Cap
        System.out.println(Formatter.getStrong("Estat dels termòstats:"));

        // * Cos
        for (String room : Menus.ROOMS) {
            currentState = status.get(room);

            msg = Formatter.getGR(currentState, "Encès", "Apagat");
            temperatureString = formatTemperature(room);
            roomFormatted = Formatter.getStrong(room);
            
            System.out.printf("%s: %s - %s.%n", roomFormatted, msg, temperatureString);
        }
    }

    // #endregion
}
