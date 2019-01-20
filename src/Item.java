/**
 * 
 * @author amir hosein Created on 19 May 2018
 */
public class Item {
	private int Id;
	private String name;
	private int price;

	public Item(int Id, String name, int price) {
		this.setId(Id);
		this.setName(name);
		this.setPrice(price);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}

		if(!(o instanceof Item)){
			return false;
		}

		Item item = (Item) o;

		return (this.getId() == item.getId());
	}
}
