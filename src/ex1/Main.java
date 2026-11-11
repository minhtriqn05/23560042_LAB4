package ex1;

import java.util.Scanner;

import util.ConsoleInput;
import util.Money;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library("UIT Library");
        int choice;
        do {
            System.out.println();
            System.out.println("===== LIBRARY BORROWING FEE MANAGEMENT - "
                    + library.getName() + " =====");
            System.out.println("1. Add items | 2. Display all | 3. Search by ID"
                    + " | 4. Statistics | 0. Exit");
            choice = ConsoleInput.readInt(sc, "Your choice: ", 0, 4);
            if (choice >= 2 && library.isEmpty()) {
                System.out.println("The list is empty, please add items first.");
                continue;
            }
            switch (choice) {
                case 1:
                    addItems(sc, library);
                    break;
                case 2:
                    library.displayAll();
                    break;
                case 3:
                    search(sc, library);
                    break;
                case 4:
                    showStatistics(library);
                    break;
                default:
                    System.out.println("Goodbye!");
            }
        } while (choice != 0);
        sc.close();
    }

    // Reads N items of mixed types and adds them to the library.
    private static void addItems(Scanner sc, Library library) {
        int n = ConsoleInput.readInt(sc, "Number of items to add: ", 1, 100);
        for (int i = 1; i <= n; i++) {
            System.out.println("--- Item " + i + " ---");
            String type = ConsoleInput.readChoice(sc,
                    "Type (B = PrintedBook, M = Magazine): ", "B", "M");
            String id = readNewId(sc, library);
            String title = ConsoleInput.readText(sc, "Title: ");
            String author = ConsoleInput.readText(sc, "Author: ");
            int days = ConsoleInput.readInt(sc, "Days borrowed: ", 1, 365);
            LibraryItem item;                  // declared with the superclass type
            if (type.equals("B")) {
                int pages = ConsoleInput.readInt(sc, "Number of pages: ", 1, 100000);
                item = new PrintedBook(id, title, author, days, pages);
            } else {
                int issue = ConsoleInput.readInt(sc, "Issue number: ", 1, 100000);
                item = new Magazine(id, title, author, days, issue);
            }
            library.addItem(item);
            System.out.println("  Added " + item.getType() + " " + item.getId()
                    + ", fee = " + Money.vnd(item.calculateFee()));
        }
    }

    // Asks again until the user types an ID that is not used yet.
    private static String readNewId(Scanner sc, Library library) {
        while (true) {
            String id = ConsoleInput.readText(sc, "Item ID: ");
            if (library.findById(id) == null) {
                return id;
            }
            System.out.println("  ID " + id + " already exists, please enter another ID.");
        }
    }

    private static void search(Scanner sc, Library library) {
        String id = ConsoleInput.readText(sc, "Item ID to search: ");
        LibraryItem item = library.findById(id);
        if (item == null) {
            System.out.println("  No item has the ID " + id + ".");
        } else {
            Library.printHeader();
            Library.printItem(item);
        }
    }

    private static void showStatistics(Library library) {
        System.out.println("Number of items        : " + library.count()
                + " (PrintedBook: " + library.count("PrintedBook")
                + ", Magazine: " + library.count("Magazine") + ")");
        System.out.println("Total borrowing revenue: " + Money.vnd(library.totalRevenue()));
        LibraryItem top = library.findHighestFee();
        System.out.println("Highest fee            : " + top.getId() + " - "
                + top.getTitle() + " (" + top.getType() + "), "
                + Money.vnd(top.calculateFee()));
    }
}
