package ex1;

import java.util.Locale;

/**
 * Exercise 1: common part of every material the library lends.
 * It is abstract because every real item is either a PrintedBook or a Magazine.
 */
public abstract class LibraryItem {

    private String id;
    private String title;
    private String author;
    private int days;                          // number of days borrowed

    public LibraryItem(String id, String title, String author, int days) {
        if (days < 1) {
            throw new IllegalArgumentException("Days must be >= 1.");
        }
        this.id = requireText(id, "ID");
        this.title = requireText(title, "Title");
        this.author = requireText(author, "Author");
        this.days = days;
    }

    // Stops an object from being created with an empty ID, title or author.
    private static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getDays() {
        return days;
    }

    // Every type has its own fee formula, so the superclass only declares it.
    public abstract double calculateFee();

    // "PrintedBook" or "Magazine", used for counting and printing.
    public abstract String getType();

    // Prints the common columns of the row (no line break);
    // each subclass overrides it to add its own column.
    public void displayInfo() {
        System.out.print(String.format(Locale.US, "%-6s | %-11s | %-20s | %-16s | %2d days",
                id, getType(), title, author, days));
    }
}
