package ex3;

import java.util.Locale;

import util.Money;

/**
 * Exercise 3: an order that has to be delivered. The fee formula depends on
 * the kind of delivery, so calculateShippingFee() is abstract.
 */
public abstract class Delivery {

    // static: ONE counter that belongs to the class and is shared by all objects
    private static int createdCount = 0;

    private String orderId;
    private String customerName;
    private double distance;                   // km
    private double weight;                     // kg

    public Delivery(String orderId, String customerName, double distance, double weight) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty.");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        if (distance <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Distance and weight must be > 0.");
        }
        this.orderId = orderId.trim();
        this.customerName = customerName.trim();
        this.distance = distance;
        this.weight = weight;
        createdCount++;                        // only reached when all data is valid
    }

    public static int getCreatedCount() {
        return createdCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getDistance() {
        return distance;
    }

    public double getWeight() {
        return weight;
    }

    public abstract double calculateShippingFee();

    // "Standard" or "Express"
    public abstract String getType();

    public void displayInfo() {
        System.out.print(String.format(Locale.US, "%-6s | %-8s | %-18s | %7s km | %6s kg",
                orderId, getType(), customerName,
                Money.plain(distance), Money.plain(weight)));
    }
}
