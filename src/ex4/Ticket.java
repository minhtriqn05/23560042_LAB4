package ex4;

import java.util.Locale;

import util.Money;

/**
 * Exercise 4: one ticket purchase (one ID, one movie, a number of tickets).
 * Price and discount depend on the ticket type, so Ticket is abstract.
 */
public abstract class Ticket {

    private String ticketId;
    private String movieTitle;
    private int quantity;

    public Ticket(String ticketId, String movieTitle, int quantity) {
        if (ticketId == null || ticketId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be empty.");
        }
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be empty.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be >= 1.");
        }
        this.ticketId = ticketId.trim();
        this.movieTitle = movieTitle.trim();
        this.quantity = quantity;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract double getUnitPrice();

    // "Regular" or "VIP"
    public abstract String getType();

    // Final total after the discount; every type has its own discount rule.
    public abstract double calculateTotalPrice();

    // Price before the discount.
    public double getSubtotal() {
        return getUnitPrice() * quantity;
    }

    // The discount is whatever the subclass took off the subtotal.
    public double getDiscountAmount() {
        return getSubtotal() - calculateTotalPrice();
    }

    public void displayInfo() {
        System.out.println(String.format(Locale.US,
                "%-6s | %-7s | %-20s | %3d | %11s | %11s | %13s",
                ticketId, getType(), movieTitle, quantity, Money.vnd(getUnitPrice()),
                Money.vnd(getDiscountAmount()), Money.vnd(calculateTotalPrice())));
    }
}
