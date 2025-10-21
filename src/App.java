package src;

import src.utils.Lights;
import src.utils.Menus;

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
            option = Menus.getOption(Menus.MAIN_MENU_OPTIONS, "");
            System.out.println("\n\n");

            switch (option) {
                case 1: // ? Llums
                    lightProcess();
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

    public static void lightProcess() {
        // * Declaració de varriables
        // Menú
        boolean running = true;
        int option;

        // Input
        int room;
        String roomString;
        boolean newState;

        do {
            option = Menus.getOption(Menus.LIGHT_MENU_OPTIONS, "Métode de control");
            System.out.println("\n\n");

            switch (option) {
                case 1: // ? Individual
                    room = Menus.getOption(Menus.ROOMS, "Habitació a controlar");
                    roomString = Menus.ROOMS[room-1];
                    System.out.println();

                    newState = Menus.getBool("Nou estat de la llum", "On", "Off");
                    System.out.println();

                    Lights.turnLight(roomString, newState);
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
}
