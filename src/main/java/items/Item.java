package items;

// Abstract class Item for other specific items to build on, basic attributes include, name and price

public abstract class Item {

	protected String name;
	protected int price;
	
	protected Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return this.price;
	}
}
