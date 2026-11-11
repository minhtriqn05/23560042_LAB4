package ex3;

import java.util.ArrayList;
import java.util.Locale;

import util.Money;

/**
 * Keeps Standard and Express orders together in one list of type Delivery.
 */
public class DeliveryService {
    private String name;                       // name of the store
    private ArrayList<Delivery> orders = new ArrayList<>();

    public DeliveryService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addOrder(Delivery d) {
        if (findById(d.getOrderId()) != null) {
            return false;
        }
        orders.add(d);
        return true;
    }

    public Delivery findById(String orderId) {
        for (Delivery d : orders) {
            if (d.getOrderId().equalsIgnoreCase(orderId.trim())) {
                return d;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public static void printHeader() {
        System.out.println("Order  | Type     | Customer           | Distance   "
                + "| Weight    |   Shipping fee");
    }

    // displayInfo() and calculateShippingFee() are called through a Delivery reference.
    public static void printRow(Delivery d) {
        d.displayInfo();
        System.out.println(String.format(Locale.US, " | %14s",
                Money.vnd(d.calculateShippingFee())));
    }

    // Overloading 1: print every order.
    public void printOrders() {
        printHeader();
        for (Delivery d : orders) {
            printRow(d);
        }
    }

    // Overloading 2: print only the orders whose fee is greater than the threshold;
    // returns how many orders were printed.
    public int printOrders(double threshold) {
        int printed = 0;
        for (Delivery d : orders) {
            if (d.calculateShippingFee() > threshold) {
                if (printed == 0) {
                    printHeader();
                }
                printRow(d);
                printed++;
            }
        }
        return printed;
    }

    public int count(String type) {
        int result = 0;
        for (Delivery d : orders) {
            if (d.getType().equals(type)) {
                result++;
            }
        }
        return result;
    }

    public int size() {
        return orders.size();
    }

    public double totalRevenue() {
        double total = 0;
        for (Delivery d : orders) {
            total += d.calculateShippingFee();
        }
        return total;
    }
}
