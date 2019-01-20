import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
class TestModule {
    /**
     *
     * @return sample of OrderImpls
     */
    OrderImpl createSampleOrders(){
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(0,"monitor",20));
        items.add(new Item(1,"monitor",20));
        items.add(new Item(2,"TV",50));
        items.add(new Item(3,"LapTop",200));

        OrderImpl orderImpl = new OrderImpl(items);

        orderImpl.takeItemsToMakeCart(new RawCart(1,1));
        orderImpl.takeItemsToMakeCart(new RawCart(2,2));
        orderImpl.takeItemsToMakeCart(new RawCart(1,3));
        orderImpl.takeItemsToMakeCart(new RawCart(0,0));

        return orderImpl;
    }

    /**
     *
     * @return sample of carts
     */
    ArrayList<Cart> createSampleCart(){
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(0,"monitor",20));
        items.add(new Item(1,"monitor",20));
        items.add(new Item(2,"TV",50));
        items.add(new Item(3,"LapTop",200));

        ArrayList<Cart> cart = new ArrayList<Cart>();
        cart.add(new Cart(items.get(1), 4, items.get(1).getPrice()));
        cart.add(new Cart(items.get(2), 2, items.get(2).getPrice()));

        return cart;
    }

    /**
     * test the price calculator with 10 percent tax
     */
    @Test
    void taxPriceCalculator() {
        ArrayList<PricingPolicy> pricingPolicies = new ArrayList<PricingPolicy>();
        pricingPolicies.add(new Tax(10));

        assertEquals(198., createSampleOrders().calculatePrice(pricingPolicies), "with 10 percent tax");
    }

    /**
     * test the price calculator with 10 percent discount
     */
    @Test
    void discountPriceCalculator() {
        ArrayList<PricingPolicy> pricingPolicies = new ArrayList<PricingPolicy>();
        pricingPolicies.add(new Discount(10));

        assertEquals(162, createSampleOrders().calculatePrice(pricingPolicies), "with 10 percent discount");
    }

    /**
     * test the price calculator with no price policy
     */
    @Test
    void normalPriceCalculator() {
        ArrayList<PricingPolicy> pricingPolicies = new ArrayList<PricingPolicy>();

        assertEquals(180, createSampleOrders().calculatePrice(pricingPolicies), "with nothing");
    }

    /**
     * test the cart making procedure
     */
    @Test
    void testCartArray() {
        assertEquals(createSampleOrders().getCart(), createSampleCart());
    }
}
