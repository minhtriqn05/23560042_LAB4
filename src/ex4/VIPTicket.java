package ex4;

public class VIPTicket extends Ticket {
    private static final double UNIT_PRICE = 150000;
    private static final int MIN_QUANTITY_FOR_DISCOUNT = 3;
    private static final int DISCOUNT_PERCENT = 15;

    public VIPTicket(String ticketId, String movieTitle, int quantity) {
        super(ticketId, movieTitle, quantity);
    }

    @Override
    public double getUnitPrice() {
        return UNIT_PRICE;
    }

    @Override
    public String getType() {
        return "VIP";
    }

    // 15% off when 3 or more tickets are bought, otherwise no discount.
    @Override
    public double calculateTotalPrice() {
        int percent = (getQuantity() >= MIN_QUANTITY_FOR_DISCOUNT) ? DISCOUNT_PERCENT : 0;
        return getSubtotal() * (100 - percent) / 100;
    }
}
