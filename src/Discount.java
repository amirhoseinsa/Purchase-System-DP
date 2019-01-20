import java.util.ArrayList;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
public class Discount implements PricingPolicy {
    private int discountPercent;

    public Discount(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     *
     * @param carts
     * @return the carts with the updated price
     * This function, update the prices regarding the discount
     */
    public ArrayList<Cart> updatePrices(ArrayList<Cart> carts) {
        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setPrice(carts.get(i).getPrice() * (1 - (discountPercent / 100.)));
        }
        return carts;
    }
}
