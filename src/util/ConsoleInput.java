package util;

import java.util.Scanner;

/**
 * Helper used by all exercises to read console input.
 * Every method keeps asking until the user types a valid value,
 * so wrong input never reaches the objects.
 */
public final class ConsoleInput {
    // Run with "java -Decho=true ... < file" to test with a file of input:
    // every value read is printed, so the output looks like typed input.
    private static final boolean ECHO = Boolean.getBoolean("echo");

    private ConsoleInput() {
        // only static methods, so no object is needed
    }

    // Reads a line that is not empty.
    public static String readText(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (ECHO) {
                System.out.println(line);
            }
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  This field cannot be empty.");
        }
    }

    // Reads an integer from min to max.
    public static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            String line = readText(sc, prompt);
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // not an integer, the message below is printed
            }
            System.out.println("  Please enter an integer from " + min + " to " + max
                    + ".");
        }
    }

    // Reads a real number that is at least min (min itself is allowed).
    public static double readDouble(Scanner sc, String prompt, double min) {
        while (true) {
            String line = readText(sc, prompt);
            try {
                double value = Double.parseDouble(line);
                if (value >= min && !Double.isInfinite(value)) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // not a number, the message below is printed
            }
            System.out.println("  Please enter a number >= " + Money.plain(min) + ".");
        }
    }

    // Reads a real number that must be strictly greater than 0 (distance, weight).
    public static double readPositive(Scanner sc, String prompt) {
        while (true) {
            String line = readText(sc, prompt);
            try {
                double value = Double.parseDouble(line);
                if (value > 0 && !Double.isInfinite(value)) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // not a number, the message below is printed
            }
            System.out.println("  Please enter a number greater than 0.");
        }
    }

    // Reads one of the options (not case-sensitive) and returns that option.
    public static String readChoice(Scanner sc, String prompt, String... options) {
        while (true) {
            String line = readText(sc, prompt);
            for (String option : options) {
                if (option.equalsIgnoreCase(line)) {
                    return option;
                }
            }
            System.out.println("  Please choose one of: " + String.join(", ", options)
                    + ".");
        }
    }
}
