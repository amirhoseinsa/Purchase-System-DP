import java.util.ArrayList;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
interface UserOrdering {
    void printItems(ArrayList<Item> items);
    RawCart receiveOrder(String message);
    void printCart(ArrayList<Cart> cart);
    void printPrice(double price);
}
