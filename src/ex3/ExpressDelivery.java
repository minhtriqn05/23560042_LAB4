package ex3;

public class ExpressDelivery extends Delivery {
    private static final double BASE_FEE = 30000;
    private static final double FEE_PER_KM = 5000;
    private static final double FEE_PER_KG = 4000;

    public ExpressDelivery(String orderId, String customerName, double distance,
                           double weight) {
        super(orderId, customerName, distance, weight);
    }

    // 30,000 + 5,000 x distance + 4,000 x weight
    @Override
    public double calculateShippingFee() {
        return BASE_FEE + FEE_PER_KM * getDistance() + FEE_PER_KG * getWeight();
    }

    @Override
    public String getType() {
        return "Express";
    }
}
