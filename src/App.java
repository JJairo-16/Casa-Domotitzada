package src;

import src.utils.Formatter;
import src.utils.Menus;

import src.utils.Lights;
import src.utils.Blinds;
import src.utils.Thermostat;

public class App {
    public static void main(String[] args) {
        // #region Declaració de variables        
        // * Bucle principal        
        boolean running = true;
        int option;
        
        // #endregion        

        // * Inicialitzar scanner        
        Menus.startScanner();

        // #region Bucle principal        
        do {
            Formatter.clear(0);
            option = Menus.getOption(Menus.MAIN_MENU_OPTIONS, "");
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

                case 5: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);

        // #endregion        

        // * Tencar scanner        
        Menus.closeScanner();
    }

    public static String getRoomString() {
        // * Declaració de variables        
        int room;
        String roomString;

        // * Menú        
        room = Menus.getOption(Menus.ROOMS, "Habitació a controlar");
        roomString = Menus.ROOMS[room-1];

        return roomString;
    }

    // #region Funcionament de les opcions    
    public static void lightProcess() {
        // * Declaració de varriables        
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
            option = Menus.getOption(Menus.LIGHT_MENU_OPTIONS, "Métode de control");
            Formatter.clear();

            // Processament            
            switch (option) {
                case 1: // ? Individual
                    room = getRoomString();
                    System.out.println();

                    newState = Menus.getBool("Nou estat de la llum", "On", "Off");
                    System.out.println();

                    Lights.turnLight(room, newState);
                    Menus.pause();
                    
                    System.out.println("\n\n");
                    break;
            
                case 2: // ? Global
                    newState = Menus.getBool("Nou estat de les llums", "On", "Off");
                    System.out.println();

                    Lights.globalTurnLigths(newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;
                
                case 3: // ? Veure estat
                    Lights.getStatus();
                    System.out.println();

                    Menus.pause();
                    break;

                case 4: // ? Sortir
                    running = false;
                    break;
            }
        } while (running);
    }

    public static void blindProcess() {
        // * Declaració de varriables        
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
            option = Menus.getOption(Menus.BLINDS_MENU_OPTIONS, "Métode de control");
            Formatter.clear();

            // Processament            
            switch (option) {
                case 1: // ? Individual
                    room = getRoomString();
                    System.out.println();
                    
                    newState = Menus.getBool("Pujar o baixar les persianes", "P", "B");
                    System.out.println();

                    Blinds.turnBlinds(room, newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;
                
                case 2: // ? Global
                    newState = Menus.getBool("Pujar o baixar les persianes", "P", "B");
                    System.out.println();

                    Blinds.globalTurnBlinds(newState);
                    Menus.pause();

                    System.out.println("\n\n");
                    break;
            
                case 3: // ? Veure estat
                    Blinds.getStatus();
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
    public static void thermostatProcess() {
        // * Declaració de varriables        
        // Menú        
        boolean running = true;
        int option;

        // Input        
        String room;

        // * Menú
        do {
            // Input            
            Formatter.clear(0);
            option = Menus.getOption(Menus.THERMOSTAT__MAIN_MENU_OPTIONS, "Métode de control");
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

    public static void thermostatIndividualProcess(String room) {
        // * Declaració de varriables        
        // Menú        
        boolean running = true;
        int option;
        
        String field = String.format("Métode de control individual (%s)", room);

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

    public static void thermostatGlobalProcess() {
        // * Declaració de varriables        
        // Menú        
        boolean running = true;
        int option;

        // Input
        boolean newState;

        // * Menú
        do {
            // Input
            Formatter.clear(0);
            option = Menus.getOption(Menus.THERMOSTAT_GLOBAL_MENU_OPTIONS, "Métode de control global");
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

    // #endregion    
}
