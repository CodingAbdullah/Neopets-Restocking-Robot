package items;

public class PotionItem extends Item {
	private int quantity;
	
	public PotionItem(String name, int cost, int quantity) {
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
