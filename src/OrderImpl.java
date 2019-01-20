import java.util.ArrayList;

/**
 * @author amir hosein Created on 19 May 2018
 */
public class OrderImpl implements Order {
    private ArrayList<Item> items;
    private ArrayList<Cart> cart = new ArrayList<Cart>();

    /**
     * Constructor
     */
    public OrderImpl(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Take orders information using RawCart and fill cart ArrayList
     *
     * @param rawCart
     * @return string which is about the situation of the order
     */
    public String takeItemsToMakeCart(RawCart rawCart) {
        int pointer = -1;
        Boolean duplicate;

        if (rawCart.id == 0) {
            return "exit";
        }

        duplicate = checkDuplicate(rawCart.id, rawCart.amount);
        if (duplicate) {
            return "duplicate";
        }

        // find the item position in items
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == rawCart.id) {
                pointer = i;
                break;
            }
        }

        if (pointer == -1) {
            return "doesn's exist";
        } else {
            cart.add(new Cart(items.get(pointer), rawCart.amount, items.get(pointer).getPrice()));
            return "added";
        }
    }

    /**
     * @param id
     * @param amount Check that do we have this item in our cart or not if we have
     *               we add amount to our cart
     * @return duplicate (if it's be true it's mean that we don't need to
     * continue the loop process for the current input in "takeItemsToMakeCart"
     * function)
     */
    private Boolean checkDuplicate(int id, int amount) {
        boolean duplicate = false;
        for (Cart aCart : cart) {
            if (aCart.getItem().getId() == id) {
                aCart.setAmount(aCart.getAmount() + amount);
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }

    /**
     * @param pricingPolicies this tell us about the policies of price calculating
     * @return Calculated Price of the OrderImpl regarding the pricingPolicies
     */
    public double calculatePrice(ArrayList<PricingPolicy> pricingPolicies) {
        double price = 0;

        for (PricingPolicy pricingPolicy : pricingPolicies) {
            cart = pricingPolicy.updatePrices(cart);
        }

        for (Cart currentItem : cart) {
            price += currentItem.getPrice() * currentItem.getAmount();
        }

        return price;
    }

    /**
     *
     */
    public ArrayList<Cart> getCart() {
        return cart;
    }

}
