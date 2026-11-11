package util;

import java.util.Locale;

/**
 * Formats amounts of money and other numbers the same way in every exercise:
 * thousands separated by commas, no decimals for whole numbers, e.g. 17,000 VND.
 */
public final class Money {

    private Money() {
        // only static methods
    }

    // A number with thousand separators and at most 2 decimals:
    // 1350000 -> "1,350,000", 2.5 -> "2.5", 1333333.333 -> "1,333,333.33".
    public static String plain(double value) {
        String text = String.format(Locale.US, "%,.2f", value);
        if (text.endsWith("0")) {
            text = text.substring(0, text.length() - 1);    // 2.50 -> 2.5
        }
        if (text.endsWith(".0")) {
            text = text.substring(0, text.length() - 2);    // 17,000.0 -> 17,000
        }
        return text;
    }

    // An amount of money in Vietnamese dong, e.g. "17,000 VND".
    public static String vnd(double value) {
        return plain(value) + " VND";
    }
}
