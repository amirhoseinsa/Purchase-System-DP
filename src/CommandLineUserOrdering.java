import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author amir hosein Created on 20 Jan 2019
 */
public class CommandLineUserOrdering implements UserOrdering {
    /**
     * @param items
     * @return -
     * This function, print the itmes of DB for user
     */
    public void printItems(ArrayList<Item> items) {
        for (int i = 1; i < items.size(); i++) {
            Item currentItem = items.get(i);
            System.out.println(currentItem.getId() + "_ "
                    + currentItem.getName() + " : " + currentItem.getPrice()
                    + "$");
        }
        System.out
                .println(items.get(0).getId() + "_ " + items.get(0).getName());
    }

    /**
     * @param message this message is about the situation of last order
     * @return the id and amount of the current order
     */
    public RawCart receiveOrder(String message) {
        Scanner scanner = new Scanner(System.in);

        switch (message) {
            case "start":
                printMessages("let's start ordering");
                break;
            case "duplicate":
                printMessages("Duplicate Item, added to the bill");
                break;
            case "doesn's exist":
                printMessages("Item doesn't exist");
                break;
            case "added":
                printMessages("Item added to the bill");
                break;
        }

        int id, amount = 0;
        id = scanner.nextInt();

        if (id != 0)
            amount = scanner.nextInt();

        return new RawCart(id, amount);

    }

    /**
     * @param cart
     * @return -
     */
    public void printCart(ArrayList<Cart> cart) {
        for (Cart currentItem : cart) {
            System.out.println("Name: " + currentItem.getItem().getName()
                    + " _ Amount: " + currentItem.getAmount());
        }
    }

    /**
     * @param price
     * @return -
     */
    public void printPrice(double price) {
        printMessages("Price: " + price);
    }

    /**
     * @param message
     * @return -
     */
    private void printMessages(String message) {
        System.out.println(message);
    }
}
