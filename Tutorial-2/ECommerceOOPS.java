
import java.util.*;

class ECommerce {

    private double prices;

    public ECommerce(double prices) {
        this.prices = prices;
            }

    public double getPrices() {
        return prices;
    }
}

public class ECommerceOOPS {

    public static void main(String args[]) {
        ECommerce[] ecom = {
            new ECommerce(4746.8),
            new ECommerce(8474.7),
            new ECommerce(48634.4)
        };
        double total = 0;
        for (ECommerce ec : ecom) {
            total += ec.getPrices();
        }
        System.out.println("TOTAL PRICE OF ORDERS : " + total);
    }
}
