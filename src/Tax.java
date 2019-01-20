import java.util.ArrayList;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
public class Tax implements PricingPolicy {
    private int taxPercent;

    public Tax(int taxPercent) {
        this.taxPercent = taxPercent;
    }

    /**
     *
     * @param carts
     * @return the carts with the updated price
     * This function, update the prices regarding the tax
     */
    public ArrayList<Cart> updatePrices(ArrayList<Cart> carts) {
        for (int i = 0; i < carts.size(); i++) {
            carts.get(i).setPrice(carts.get(i).getPrice() * (1 + (taxPercent / 100.)));
        }
        return carts;
    }
}
