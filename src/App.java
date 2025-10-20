package src;

import java.util.Scanner;

import src.utils.Menus;

public class App {
    public static Scanner scanner;

    public static void main(String[] args) {
        // #region Declaració de variables
        // * Bucle principal
        boolean running = true;
        int option;
        
        // #endregion

        // * Crear i inicialitzar scanner
        scanner = new Scanner(System.in);
        Menus.setScanner(scanner);

        // #region Bucle principal
        while (running) {
            option = Menus.getOption(Menus.MAIN_MENU_OPTIONS);

            switch (option) {
                case 5:
                    running = false;
                    break;
            }
        }

        // #endregion

        scanner.close();
    }
}
