package items;

// Abstract class Item for other specific items to build on, basic attributes include, name and price

public abstract class Item {

	protected String name;
	protected int cost;
	
	protected Item(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected void setPrice(int cost) {
		this.cost = cost;
	}
	
	public int getPrice() {
		return this.cost;
	}
}
