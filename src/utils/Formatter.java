package src.utils;

public class Formatter {
    private Formatter() {} // ? Eliminar el constructor

    private static final boolean IS_WINDOWS = System.getProperty("os.name", "").startsWith("Windows");
    private static final ProcessBuilder WINDOWS_CLS = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
    private static final ProcessBuilder LINUX_CLS = new ProcessBuilder("clear").inheritIO();

    private static final String ESC = "\u001B";

    // #region Format i colors
    // * Format
    public static final String RESET = String.format("%s[0m", ESC);

    public static final String STRONG = String.format("%s[1m", ESC);
    public static final String UN_STRONG = String.format("%s[22m", ESC);

    // * Colors
    public static final String RED = String.format("%s[31m", ESC);
    public static final String GREEN = String.format("%s[32m", ESC);
    public static final String BLUE = String.format("%s[34m", ESC);
    public static final String GRAY = String.format("%s[90m", ESC);

    // #endregion

    // * Colors i format
    public static String getGR(boolean condition, String value1, String value2) {
        // * Declaració de variables
        String format;

        // * Format
        if (condition) {
            format = GREEN + value1;
        } else {
            format = RED + value2;
        }

        format = STRONG + format + RESET;
        return format;
    }

    public static String getStrong(String text) {
        return STRONG + text + UN_STRONG;
    }

    // * Consola
    public static void clear(int aux) {
        try {
            if (IS_WINDOWS) {
                WINDOWS_CLS.start().waitFor();
            } else {
                LINUX_CLS.start().waitFor();
            }

        } catch (Exception e) {
            System.out.println("\n".repeat(aux - 1));
        }
    }

    public static void clear() {
        clear(3);
    }
}
