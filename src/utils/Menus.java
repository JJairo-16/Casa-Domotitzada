package src.utils;

import java.util.Scanner;

public class Menus {
    // #region Scanner
    private static Scanner scanner;

    public static void setScanner(Scanner scannerInput) {
        scanner = scannerInput;
    }

    // #endregion

    // #region Opcions
    public final static String[] MAIN_MENU_OPTIONS= new String[] {
        "Controlar les llums",
        "Controlar la temperatura",
        "Controlar les persianes",
        "Controlar els ventiladors",
        "Sortir"
    };

    // #endregion

    // #region Métodes
    private static void showMenu(String[] menu, final int MAX_OPTION) {
        System.out.println("== MENÚ ==");
        for (int i = 0; i < MAX_OPTION; i++) {
            int optionNum = i + 1;
            System.out.printf("%d. %s%n", optionNum, menu[i]);
        }
        System.out.println();
    }

    public static int getOption(String[] menu) {
        // * Declaració de variables
        // Input
        String input;
        int option = 0;

        // Menu
        boolean running = true;
        
        // * Regles
        final int MIN_OPTION = 1;
        final int MAX_OPTION = menu.length;

        // * Menú principal
        while (running) {
            // * Mostrar menú
            showMenu(menu, MAX_OPTION);

            // * Obtenir opció cruda
            System.out.print("Si us plau, introdueixi una opció: ");
            input = scanner.nextLine();

            // * Validacions
            if (input.isBlank()) {
                System.out.println("L'opció no pot estar en blanc. Si us plau, torni a intentar-ho.\n\n");
                continue;
            }

            try {
                option = Integer.parseInt(input);
                
                if (option >= MIN_OPTION && option <= MAX_OPTION) {
                    return option; // <-- salir cuando es válida
                } else {
                    System.out.printf("L'opció ha d'estar entre %d i %d.%n%n", MIN_OPTION, MAX_OPTION);
                }

            } catch (java.lang.NumberFormatException e) {
                System.out.println("L'opció ha de ser un nombre enter. Si us plau, torni a intentar-ho.\n\n");
            
            } catch (Exception e) {
                System.out.println("Algú ha sortit malament. Si us plau, torni a intentar-ho.\n\n");
            }
        }
        
        return option;
    }
    // #endregion
}
