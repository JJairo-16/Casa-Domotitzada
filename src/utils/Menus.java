package src.utils;

import java.util.Scanner;

public class Menus {
    private Menus() {} // ? Eliminar constructor

    // #region Scanner
    private static Scanner scanner;

    public static void startScanner() {
        scanner = new Scanner(System.in);
    }

    public static void closeScanner() {
        scanner.close();
    }

    // #endregion

    // #region Opcions
    public static final String[] MAIN_MENU_OPTIONS = new String[] {
        "Controlar les llums",
        "Controlar les persianes",
        "Controlar la temperatura",
        "Controlar els ventiladors",
        "Sortir"
    };

    public static final String[] ROOMS = new String[] {
        "Sala d'estar",
        "Lavabo",
        "Cuina",
        "h1",
        "h2",
        "h3"
    };

    public static final String[] LIGHT_MENU_OPTIONS = new String[] {
        "Controlar les llums de forma individual",
        "Controlar les llums de forma global",
        "Mostrar l'estat de les llums",
        "Sortir"
    };

    public static final String[] BLINDS_MENU_OPTIONS =  new String[] {
        "Controlar les persianes de forma individual",
        "Controlar les persianes de forma global",
        "Mostra l'estat de les persianes",
        "Sortir"
    };

    public static final String[] THERMOSTAT__MAIN_MENU_OPTIONS = new String[] {
        "Controlar el termòstat de forma individual",
        "Controlar la temperatura de forma global",
        "Sortir"
    };

    public static final String[] THERMOSTAT_INDIVIDUAL_MENU_OPTIONS = new String[] {
        "Encendre o apagar",
        "Canviar temperatura",
        "Veure estat",
        "Tornar enrere"
    };

    public static final String[] THERMOSTAT_GLOBAL_MENU_OPTIONS = new String[] {
        "Encendre o apagar",
        "Veure estat",
        "Tornar enrere"
    };

    public static final String[] FANS_MENU_OPTIONS = new String[] {
        "Establir velocitat dels ventiladors de forma individual",
        "Establir velocitat dels ventiladors de forma global",
        "Veure estat",
        "Sortir"
    };

    // #endregion

    // #region Métodes
    public static void pause() {
        System.out.print("\nPrem enter per continuar. ");
        scanner.nextLine();
    }

    /**
     * Mostra el menú proporcionat.
     * 
     * @param menu String[] amb el menú.
     * @param field String amb un subtítol.
     */
    private static void showMenu(String[] menu, String field) {
        System.out.println(Formatter.getStrong("== MENÚ =="));

        if (!field.isBlank()) {
            System.out.printf("%s-- %s:%s%n", Formatter.GRAY, field, Formatter.RESET);
        }

        for (int i = 0; i < menu.length ; i++) {
            int optionNum = i + 1;
            System.out.printf("%d. %s%n", optionNum, menu[i]);
        }
        System.out.println();
    }

    /**
     * Obte l'opció de l'usuari de forma cruda.
     */
    public static int getUserOption(int minOption, int maxOption) {
        // * Declaració de variables
        boolean running = true;

        String input;
        int option = 0;

        // * Menú principal
        do {
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

                if (option >= minOption && option <= maxOption) { // ? Validació de rang
                    running = false;
                } else {
                    System.out.printf("L'opció ha d'estar entre %d i %d.%n%n", minOption, maxOption);
                }

            } catch (java.lang.NumberFormatException e) {
                System.out.println("L'opció ha de ser un nombre enter. Si us plau, torni a intentar-ho.\n\n");
            
            } catch (Exception e) {
                System.out.println("Algú ha sortit malament. Si us plau, torni a intentar-ho.\n\n");
            }
        } while (running);
        
        return option;
    }

    /**
     * Mostra el menú proporcionat i obte l'opció de l'usuari.
     * 
     * @param menu String[] amb el menú.
     * @param field String amb un subtítol.
     */
    public static int getOption(String[] menu, String field) {
        // * Regles
        final int MIN_OPTION = 1;
        final int MAX_OPTION = menu.length;

        // * Mostrar menú
        showMenu(menu, field);

        return getUserOption(MIN_OPTION, MAX_OPTION);
    }

    public static int getOption(String[] menu) {
        return getOption(menu, "");
    }

    /**
     * Obté del usuari la decisió de dos opcions.
     * 
     * @param field Prompt a preguntar.
     * @param optionB Opció 1.
     * @param optionA Opció 2.
     * 
     * @return {@code true} si la opció 1 és elegida; {@code false} del contrarri (opció 2).
     */
    public static boolean getBool(String field, String optionA, String optionB) {
        // * Declaració de variables
        // Input
        String input;
        String prompt;

        // Menú
        boolean running = true;

        // * Preparació
        // Normlització
        optionA = optionA.trim();
        optionB = optionB.trim();

        prompt = String.format("%s (%s/%s): ", field, optionA, optionB);

        // * Bucle
        do {
            // * Input
            System.out.print(prompt);
            input = scanner.nextLine();

            // * Validació
            if (input.isBlank()) {
                System.out.println("L'opció no pot estar en blanc. Si us plau, torni a intentar-ho:\n");
                continue;
            }

            input = input.trim(); // ? Normalització

            if (input.equalsIgnoreCase(optionA) || input.equalsIgnoreCase(optionB)) { // ? Opció correcta?
                running = false;
            }
        } while (running);

        return (input.equals(optionA));
        
    }

    public static int getTemperature(int min, int max) {
        // * Declaració de variables
        String input;
        int newTemperature = 0;

        boolean running = true;

        // * Bucle
        do {
            // * Obtenir nombre (String)
            System.out.print("Si us plau, introdueixi una temperatura (ºC): ");
            input = scanner.nextLine();

            // * Validacions
            if (input.isBlank()) {
                System.out.println("La temperatura no pot estar en blanc. Si us plau, torni a intentar-ho.\n\n");
                continue;
            }

            try {
                newTemperature = Integer.parseInt(input);

                if (newTemperature >= min && newTemperature <= max) { // ? Validació de rang
                    running = false;
                } else {
                    System.out.printf("La temperatura ha d'estar entre els %d i %d graus.%n%n", min, max);
                }

            } catch (java.lang.NumberFormatException e) {
                System.out.println("La temperatura ha de ser un nombre enter. Si us plau, torni a intentar-ho.\n\n");
            
            } catch (Exception e) {
                System.out.println("Algú ha sortit malament. Si us plau, torni a intentar-ho.\n\n");
            }
        } while (running);
        
        return newTemperature;
    }

    // #endregion
}
