import java.util.ArrayList;

/**
 * 
 * @author amir hosein Created on 19 May 2018
 */
public class Main {
	public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<Item>();
		DB db = new DB();
		CommandLineUserOrdering commandLineUserOrdering = new CommandLineUserOrdering();

        db.readRecordsAndFillItems(items);

        OrderImpl orderImpl = new OrderImpl(items);

		commandLineUserOrdering.printItems(items);

        String messageOfCommandLine = "start";
		while (!messageOfCommandLine.equals("exit")){
            RawCart rawCart = commandLineUserOrdering.receiveOrder(messageOfCommandLine);
		    messageOfCommandLine = orderImpl.takeItemsToMakeCart(rawCart);
        }


        commandLineUserOrdering.printCart(orderImpl.getCart());

		ArrayList<PricingPolicy> pricingPolicies = new ArrayList<PricingPolicy>();
		pricingPolicies.add(new Tax(10));
        pricingPolicies.add(new Discount(10));

        commandLineUserOrdering.printPrice(orderImpl.calculatePrice(pricingPolicies));

		db.addRecordsToORDERS(orderImpl.getCart(), orderImpl.calculatePrice(pricingPolicies));
		
		// you can use this line to see the ORDERS_DETAIL DB
		// db.readORDERS_DETAIL();
	}

}