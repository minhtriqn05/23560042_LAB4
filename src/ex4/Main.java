package ex4;

import java.util.ArrayList;
import java.util.Scanner;

import util.ConsoleInput;
import util.Money;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cinema cinema = new Cinema("Star Cinema");
        int choice;
        do {
            System.out.println();
            System.out.println("===== CINEMA TICKET SALES MANAGEMENT - " + cinema.getName()
                    + " =====");
            System.out.println("1. Add purchases | 2. Display all | 3. Search by movie"
                    + " | 4. Revenue report | 5. Sort by final total | 0. Exit");
            choice = ConsoleInput.readInt(sc, "Your choice: ", 0, 5);
            if (choice >= 2 && cinema.isEmpty()) {
                System.out.println("There are no purchases yet, please add some first.");
                continue;
            }
            switch (choice) {
                case 1:
                    addPurchases(sc, cinema);
                    break;
                case 2:
                    cinema.displayAll();
                    break;
                case 3:
                    searchByMovie(sc, cinema);
                    break;
                case 4:
                    showRevenue(cinema);
                    break;
                case 5:
                    System.out.println("Purchases sorted by final total (high to low):");
                    Cinema.printList(cinema.sortedByTotalDescending());
                    break;
                default:
                    System.out.println("Goodbye!");
            }
        } while (choice != 0);
        sc.close();
    }

    private static void addPurchases(Scanner sc, Cinema cinema) {
        int n = ConsoleInput.readInt(sc, "Number of purchases to add: ", 1, 100);
        for (int i = 1; i <= n; i++) {
            System.out.println("--- Purchase " + i + " ---");
            String type = ConsoleInput.readChoice(sc, "Type (R = Regular, V = VIP): ",
                    "R", "V");
            String id = readNewId(sc, cinema);
            String movie = ConsoleInput.readText(sc, "Movie title: ");
            int quantity = ConsoleInput.readInt(sc, "Quantity: ", 1, 1000);
            Ticket t;                          // superclass type
            if (type.equals("R")) {
                t = new RegularTicket(id, movie, quantity);
            } else {
                t = new VIPTicket(id, movie, quantity);
            }
            cinema.addTicket(t);
            System.out.println("  Added " + t.getType() + " purchase " + t.getTicketId()
                    + ", final total = " + Money.vnd(t.calculateTotalPrice()));
        }
    }

    private static String readNewId(Scanner sc, Cinema cinema) {
        while (true) {
            String id = ConsoleInput.readText(sc, "Ticket ID: ");
            if (cinema.findById(id) == null) {
                return id;
            }
            System.out.println("  Ticket ID " + id + " already exists, please enter"
                    + " another ID.");
        }
    }

    private static void searchByMovie(Scanner sc, Cinema cinema) {
        String movie = ConsoleInput.readText(sc, "Movie title: ");
        ArrayList<Ticket> result = cinema.findByMovie(movie);
        if (result.isEmpty()) {
            System.out.println("  No purchase for the movie \"" + movie + "\".");
        } else {
            Cinema.printList(result);
            System.out.println("  Revenue of this movie: "
                    + Money.vnd(cinema.totalRevenue(movie)));
        }
    }

    private static void showRevenue(Cinema cinema) {
        System.out.println("Total revenue: " + Money.vnd(cinema.totalRevenue()));
        String best = cinema.findBestMovie();
        System.out.println("Movie with the highest revenue: " + best + " ("
                + Money.vnd(cinema.totalRevenue(best)) + ")");
    }
}
