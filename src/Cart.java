/**
 * 
 * @author amir hosein Created on 19 May 2018
 */
public class Cart {
	private Item item;
	private int amount;
	private double price;

	public Cart(Item item, int amount, double price) {
		this.item = item;
		this.amount = amount;
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}

		if(!(o instanceof Cart)){
			return false;
		}

		Cart cart = (Cart) o;

		return (this.item.getId() == cart.item.getId()) && (this.amount == cart.amount);
	}
}
