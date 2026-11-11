package ex4;

import java.util.ArrayList;

/**
 * Keeps every ticket purchase in one list of the abstract type Ticket.
 */
public class Cinema {
    private String name;                       // name of the cinema
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Cinema(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addTicket(Ticket t) {
        if (findById(t.getTicketId()) != null) {
            return false;
        }
        tickets.add(t);
        return true;
    }

    public Ticket findById(String ticketId) {
        for (Ticket t : tickets) {
            if (t.getTicketId().equalsIgnoreCase(ticketId.trim())) {
                return t;
            }
        }
        return null;
    }

    // All purchases of one movie; the title is not case-sensitive.
    public ArrayList<Ticket> findByMovie(String movieTitle) {
        ArrayList<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getMovieTitle().equalsIgnoreCase(movieTitle.trim())) {
                result.add(t);
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    public static void printHeader() {
        System.out.println("ID     | Type    | Movie                | Qty |  Unit price "
                + "|    Discount |   Final total");
    }

    public static void printList(ArrayList<Ticket> list) {
        printHeader();
        for (Ticket t : list) {
            t.displayInfo();                   // calls the subclass's calculateTotalPrice()
        }
    }

    public void displayAll() {
        printList(tickets);
    }

    // Overloading 1: revenue of all purchases.
    public double totalRevenue() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.calculateTotalPrice();
        }
        return total;
    }

    // Overloading 2: revenue of one movie (all its purchases added together).
    public double totalRevenue(String movieTitle) {
        double total = 0;
        for (Ticket t : findByMovie(movieTitle)) {
            total += t.calculateTotalPrice();
        }
        return total;
    }

    // Movie title with the highest total revenue; null if there is no purchase.
    public String findBestMovie() {
        String best = null;
        double bestRevenue = -1;
        for (Ticket t : tickets) {
            double revenue = totalRevenue(t.getMovieTitle());
            if (revenue > bestRevenue) {
                best = t.getMovieTitle();
                bestRevenue = revenue;
            }
        }
        return best;
    }

    // A copy of the list sorted from the highest to the lowest final total
    // (selection sort, written by hand); the stored list keeps its order.
    public ArrayList<Ticket> sortedByTotalDescending() {
        ArrayList<Ticket> sorted = new ArrayList<>(tickets);
        for (int i = 0; i < sorted.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < sorted.size(); j++) {
                if (sorted.get(j).calculateTotalPrice()
                        > sorted.get(maxIndex).calculateTotalPrice()) {
                    maxIndex = j;
                }
            }
            Ticket temp = sorted.get(i);       // swap positions i and maxIndex
            sorted.set(i, sorted.get(maxIndex));
            sorted.set(maxIndex, temp);
        }
        return sorted;
    }
}
