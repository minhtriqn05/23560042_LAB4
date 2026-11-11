package ex4;

public class RegularTicket extends Ticket {
    private static final double UNIT_PRICE = 80000;
    private static final int MIN_QUANTITY_FOR_DISCOUNT = 5;
    private static final int DISCOUNT_PERCENT = 10;

    public RegularTicket(String ticketId, String movieTitle, int quantity) {
        super(ticketId, movieTitle, quantity);  // constructor chaining to Ticket
    }

    @Override
    public double getUnitPrice() {
        return UNIT_PRICE;
    }

    @Override
    public String getType() {
        return "Regular";
    }

    // 10% off when 5 or more tickets are bought, otherwise no discount.
    @Override
    public double calculateTotalPrice() {
        int percent = (getQuantity() >= MIN_QUANTITY_FOR_DISCOUNT) ? DISCOUNT_PERCENT : 0;
        return getSubtotal() * (100 - percent) / 100;
    }
}
