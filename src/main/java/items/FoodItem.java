package items;


// Class representing food items from the food shop
public class FoodItem extends Item {
	private int quantity;
	
	public FoodItem(String name, int quantity, int cost) {
		super(name, cost);
		this.quantity = quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}
	
}