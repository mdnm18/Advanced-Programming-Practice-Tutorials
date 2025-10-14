
public class ECommerceProcedural {

    public static void main(String[] args) {
        double[] orderPrices = {599.99, 899.50, 1299.00, 250.75};
        double total = 0;
        for (double price : orderPrices) {
            total += price;
        }
        System.out.println("Total Order Value: ₹" + total);
    }
}
