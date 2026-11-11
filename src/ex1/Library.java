package ex1;

import java.util.ArrayList;
import java.util.Locale;

import util.Money;

/**
 * Keeps all borrowed items in one list whose element type is the abstract
 * superclass, so PrintedBook and Magazine objects can be stored together.
 */
public class Library {
    private String name;                       // name of the library
    private ArrayList<LibraryItem> items = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Adds the item only if no other item has the same ID.
    public boolean addItem(LibraryItem item) {
        if (findById(item.getId()) != null) {
            return false;
        }
        items.add(item);
        return true;
    }

    // ID search is not case-sensitive; returns null when nothing is found.
    public LibraryItem findById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(id.trim())) {
                return item;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public static void printHeader() {
        System.out.println("ID     | Type        | Title                | Author           "
                + "| Days    | Pages / Issue |          Fee");
    }

    // One row of the table. displayInfo() and calculateFee() are called on a
    // LibraryItem reference, so Java runs the PrintedBook or Magazine version.
    public static void printItem(LibraryItem item) {
        item.displayInfo();
        System.out.println(String.format(Locale.US, "   | %12s",
                Money.vnd(item.calculateFee())));
    }

    public void displayAll() {
        printHeader();
        for (LibraryItem item : items) {
            printItem(item);
        }
    }

    // Overloading: count() counts every item, count(type) only one type.
    public int count() {
        return items.size();
    }

    public int count(String type) {
        int result = 0;
        for (LibraryItem item : items) {
            if (item.getType().equals(type)) {
                result++;
            }
        }
        return result;
    }

    public double totalRevenue() {
        double total = 0;
        for (LibraryItem item : items) {
            total += item.calculateFee();      // polymorphic call, no downcasting
        }
        return total;
    }

    // Item with the highest fee (the first one in a tie); null if the list is empty.
    public LibraryItem findHighestFee() {
        LibraryItem best = null;
        for (LibraryItem item : items) {
            if (best == null || item.calculateFee() > best.calculateFee()) {
                best = item;
            }
        }
        return best;
    }
}
