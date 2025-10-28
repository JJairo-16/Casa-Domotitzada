package src;

import src.utils.Formatter;
import src.utils.Menus;

import src.utils.Lights;
import src.utils.Blinds;
import src.utils.Thermostat;
import src.utils.Fans;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.mainCode();
    }

    public void mainCode() {
        // * Declaració de variables
        boolean running = true;
        int option;

        // * Inicialitzar scanner
        Menus.startScanner();

        // #region Bucle principal
        do {
            Formatter.clear(0);
            option = Menus.getOption(Menus.MAIN_MENU_OPTIONS);
            Formatter.clear();

            switch (option) {
                case 1: // ? Llums
                    lightProcess();
                    break;

                case 2: // ? Persianes
                    blindProcess();
                    break;

                case 3: // ? Termòstat
                    thermostatProcess();
                    break;

                case 4: // ? Ventiladors
                    fanProcess();
                    break;

                case 5: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);

        // #endregion

        // * Tancar scanner
        Menus.closeScanner();
    }

    public String getRoomString() {
        // * Declaració de variables
        int room;
        String roomString;

        // * Menú
        room = Menus.getOption(Menus.ROOMS, "Habitació a controlar");
        roomString = Menus.ROOMS[room-1];

        return roomString;
    }

    // #region Funcionament de les opcions
    public void lightProcess() {
        // * Declaració de variables
        // Menú
        boolean running = true;
        int option;

        // Input
        String room;
        boolean newState;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.LIGHT_MENU_OPTIONS, "Mètode de control");
            Formatter.clear();

            // Processament
            switch (option) {
                case 1: // ? Individual
                    room = getRoomString();
                    System.out.println();

                    newState = Menus.getBool("Encendre o apagar les llums", "On", "Off");
                    System.out.println();

                    Lights.turnLight(room, newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 2: // ? Global
                    newState = Menus.getBool("Encendre o apagar les llums", "On", "Off");
                    System.out.println();

                    Lights.globalTurnLights(newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 3: // ? Veure estat
                    Lights.showStatus();
                    System.out.println();

                    Menus.pause();
                    break;

                case 4: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);
    }

    public void blindProcess() {
        // * Declaració de variables
        // Menú
        boolean running = true;
        int option;

        // Input
        String room;
        boolean newState;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.BLINDS_MENU_OPTIONS, "Mètode de control");
            Formatter.clear();

            // Processament
            switch (option) {
                case 1: // ? Individual
                    room = getRoomString();
                    System.out.println();

                    newState = Menus.getBool("Pujar o baixar les persianes", "Up", "Down");
                    System.out.println();

                    Blinds.turnBlinds(room, newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 2: // ? Global
                    newState = Menus.getBool("Pujar o baixar les persianes", "Up", "Down");
                    System.out.println();

                    Blinds.globalTurnBlinds(newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 3: // ? Veure estat
                    Blinds.showStatus();
                    System.out.println();

                    Menus.pause();
                    break;

                case 4: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);
    }

    // #region Termòstats
    /**
     * Menú principal del termòstat.
     */
    public void thermostatProcess() {
        // * Declaració de variables
        // Menú
        boolean running = true;
        int option;

        // Input
        String room;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.THERMOSTAT__MAIN_MENU_OPTIONS, "Mètode de control");
            Formatter.clear();

            // Processament
            switch (option) {
                case 1: // ? Individual
                    room = getRoomString();
                    thermostatIndividualProcess(room);
                    break;

                case 2: // ? Global
                    thermostatGlobalProcess();
                    break;

                case 3: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);
    }

    /**
     * Submenú (individual) del termòstat.
     * 
     * @param room Ha de ser el nom exacte (Hi ha un array amb aquests a {@link Menus}).
     */
    public void thermostatIndividualProcess(String room) {
        // * Declaració de variables
        // Menú
        boolean running = true;
        int option;

        String field = String.format("Mètode de control individual (%s)", room);

        // Input
        boolean newState;
        int newTemperature;

        // * Regles
        final int MIN_TEMPERATURE = Thermostat.MIN_TEMPERATURE;
        final int MAX_TEMPERATURE = Thermostat.MAX_TEMPERATURE;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.THERMOSTAT_INDIVIDUAL_MENU_OPTIONS, field);
            Formatter.clear();

            // Processament
            switch (option) {
                case 1: // ? Encendre o apagar
                    newState = Menus.getBool("Nou estat del termòstat", "on", "off");
                    System.out.println();

                    Thermostat.turnThermostat(room, newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 2: // ? Establir temperatura
                    if (!Thermostat.getStatus(room)) {
                        Thermostat.changeTemperature(room, 0);
                    } else {
                        newTemperature = Menus.getTemperature(MIN_TEMPERATURE, MAX_TEMPERATURE);
                        System.out.println();

                        Thermostat.changeTemperature(room, newTemperature);
                        System.out.println();
                    }

                    Menus.pause();
                    System.out.println("\n\n");
                    break;

                case 3: // ? Veure estat
                    Thermostat.showThermStatus(room);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 4: // ? Tornar enrere
                    running = false;
                    break;
            }
        } while (running);
    }

    /**
     * Submenú (global) del termòstat.
     */
    public void thermostatGlobalProcess() {
        // * Declaració de variables
        // Menú
        boolean running = true;
        int option;

        // Input
        boolean newState;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.THERMOSTAT_GLOBAL_MENU_OPTIONS, "Mètode de control global");
            Formatter.clear();

            // Processament
            switch (option) {
                case 1: // ? Encendre o apagar
                    newState = Menus.getBool("Nou estat dels termòstats", "on", "off");
                    System.out.println();

                    Thermostat.turnAllThermostats(newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 2: // ? Veure estat
                    Thermostat.showAllThermsStatus();
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 3: // ? Tornar enrere
                    running = false;
                    break;
            }
        } while (running);
    }

    // #endregion

    public void fanProcess() {
        // * Declaració de variables
        // Menú
        boolean running = true;
        int option;

        // Input
        String room;
        int speed;

        // * Regles
        final int MIN_SPEED  = Fans.MIN_SPEED;
        final int MAX_SPEED = Fans.MAX_SPEED;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.FANS_MENU_OPTIONS, "Mètode de control");
            Formatter.clear();

            // Processament
            switch (option) {
                case 1: // ? Individual
                    room = getRoomString();
                    System.out.println();

                    speed = getSpeed(MIN_SPEED, MAX_SPEED);
                    System.out.println();

                    Fans.setSpeed(room, speed);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 2: // ? Global
                    speed = getSpeed(MIN_SPEED, MAX_SPEED);
                    System.out.println();

                    Fans.setGlobalSpeed(speed);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 3: // ? Veure estat
                    Fans.showStatus();
                    Menus.pause();

                    System.out.println("\n\n");
                    break;

                case 4: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);
    }

    private int getSpeed(final int MIN_SPEED, final int MAX_SPEED) {
        int speed;
        Formatter.clear(0);
        Fans.showSpeeds();
        System.out.println();

        speed = Menus.getUserOption(MIN_SPEED, MAX_SPEED);
        return speed;
    }

    // #endregion
}
