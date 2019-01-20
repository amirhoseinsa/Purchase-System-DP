import java.util.ArrayList;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
interface Order {
    String takeItemsToMakeCart(RawCart rawCart);
    double calculatePrice(ArrayList<PricingPolicy> pricingPolicies);
}
