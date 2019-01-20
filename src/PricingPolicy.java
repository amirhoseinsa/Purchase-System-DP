import java.util.ArrayList;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
public interface PricingPolicy {
    ArrayList<Cart> updatePrices (ArrayList<Cart> carts);
}
