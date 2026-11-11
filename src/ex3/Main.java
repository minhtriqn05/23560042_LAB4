package ex3;

import java.util.Scanner;

import util.ConsoleInput;
import util.Money;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DeliveryService service = new DeliveryService("UIT Online Store");
        int choice;
        do {
            System.out.println();
            System.out.println("===== ONLINE ORDER DELIVERY MANAGEMENT - "
                    + service.getName() + " =====");
            System.out.println("1. Add orders | 2. Display all | 3. Search by order ID"
                    + " | 4. Revenue report");
            System.out.println("5. Orders above a fee | 6. Object counter | 0. Exit");
            choice = ConsoleInput.readInt(sc, "Your choice: ", 0, 6);
            if (choice >= 2 && choice <= 5 && service.isEmpty()) {
                System.out.println("There are no orders yet, please add orders first.");
                continue;
            }
            switch (choice) {
                case 1:
                    addOrders(sc, service);
                    break;
                case 2:
                    service.printOrders();
                    break;
                case 3:
                    search(sc, service);
                    break;
                case 4:
                    showRevenue(service);
                    break;
                case 5:
                    showAboveThreshold(sc, service);
                    break;
                case 6:
                    showCounter(service);
                    break;
                default:
                    System.out.println("Goodbye!");
            }
        } while (choice != 0);
        sc.close();
    }

    private static void addOrders(Scanner sc, DeliveryService service) {
        int n = ConsoleInput.readInt(sc, "Number of orders to add: ", 1, 100);
        for (int i = 1; i <= n; i++) {
            System.out.println("--- Order " + i + " ---");
            String type = ConsoleInput.readChoice(sc, "Type (S = Standard, E = Express): ",
                    "S", "E");
            String id = readNewId(sc, service);
            String customer = ConsoleInput.readText(sc, "Customer name: ");
            double distance = ConsoleInput.readPositive(sc, "Distance (km): ");
            double weight = ConsoleInput.readPositive(sc, "Weight (kg): ");
            Delivery d;                        // superclass type
            if (type.equals("S")) {
                d = new StandardDelivery(id, customer, distance, weight);
            } else {
                d = new ExpressDelivery(id, customer, distance, weight);
            }
            service.addOrder(d);
            System.out.println("  Added " + d.getType() + " order " + d.getOrderId()
                    + ", shipping fee = " + Money.vnd(d.calculateShippingFee()));
        }
    }

    private static String readNewId(Scanner sc, DeliveryService service) {
        while (true) {
            String id = ConsoleInput.readText(sc, "Order ID: ");
            if (service.findById(id) == null) {
                return id;
            }
            System.out.println("  Order " + id + " already exists, please enter"
                    + " another ID.");
        }
    }

    private static void search(Scanner sc, DeliveryService service) {
        String id = ConsoleInput.readText(sc, "Order ID to search: ");
        Delivery d = service.findById(id);
        if (d == null) {
            System.out.println("  No order has the ID " + id + ".");
        } else {
            DeliveryService.printHeader();
            DeliveryService.printRow(d);
        }
    }

    private static void showRevenue(DeliveryService service) {
        System.out.println("Standard orders       : " + service.count("Standard"));
        System.out.println("Express orders        : " + service.count("Express"));
        System.out.println("Total orders          : " + service.size());
        System.out.println("Total shipping revenue: " + Money.vnd(service.totalRevenue()));
    }

    private static void showAboveThreshold(Scanner sc, DeliveryService service) {
        double threshold = ConsoleInput.readDouble(sc, "Fee threshold (VND): ", 0);
        int printed = service.printOrders(threshold);
        if (printed == 0) {
            System.out.println("  No order has a fee above " + Money.vnd(threshold) + ".");
        } else {
            System.out.println("  " + printed + " order(s) have a fee above "
                    + Money.vnd(threshold) + ".");
        }
    }

    private static void showCounter(DeliveryService service) {
        System.out.println("Delivery objects created: " + Delivery.getCreatedCount());
        System.out.println("Orders in the list      : " + service.size());
        System.out.println("createdCount is static: it belongs to the Delivery class,");
        System.out.println("so there is only one copy, shared by all orders, and it is");
        System.out.println("read with the class name: Delivery.getCreatedCount().");
    }
}
