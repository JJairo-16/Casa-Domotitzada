package src.utils;

public class Colors {
    private Colors() {} // ? Eliminar constructor

    private static final String ESC = "\u001B";

    // #region Format i colors
    // * Format
    public static final String RESET = String.format("%s[0m", ESC);
    public static final String STRONG = String.format("%s[1m", ESC);

    // * Colors
    public static final String RED = String.format("%s[31m", ESC);
    public static final String GREEN = String.format("%s[32m", ESC);

    // #endregion

    public static String getGR(boolean condition, String value1, String value2) {
        // * Declaració de variables
        String format = STRONG;

        // * Format
        if (condition) {
            format += GREEN + value1;
        } else {
            format += RED + value2;
        }

        format += RESET;
        return format;
    }
}
