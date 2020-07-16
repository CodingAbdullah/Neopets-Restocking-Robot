package shops;

import javax.mail.MessagingException;

public abstract class ShopPage {
	
	protected abstract void findItem() throws MessagingException;
	
	protected String filterItemName(String itemDescription) {
		return itemDescription.split("\\r?\\n")[0].trim(); // Filter the item name by returning the first line of description
	}
	
	protected int filterItemQuantity(String itemDescription) {
		
		/* Split on newline, obtain the second line, split that line on 'in' substring and select first element trimmed, represents # in stock.
		   Convert to Integer, all in one line, you got it? Good :)
		*/
		
		return Integer.parseInt(itemDescription.split("\\r?\\n")[1].split("in")[0].trim()); 
	}
	
	protected int filterItemCost(String itemDescription) {
		
		/* Ight, method chaining this stuff is easssyyy, I mean it's patterning and splitting :)	
		   Split on third line of description by newline, then split on colon select the numerical part and then concatenate the two strings separated by the comma, convert into integer :)
		   If the cost is greater than 999 NP, consider commas
		*/
		
		if (!itemDescription.contains(",")) {
			return Integer.parseInt(itemDescription.split("\\r?\\n")[2].split(":")[1].trim().split(" ")[0]);
		}
		else {
			return Integer.parseInt(itemDescription.split("\\r?\\n")[2].split(":")[1].trim().split(" ")[0].split(",")[0] + itemDescription.split("\\r?\\n")[2].split(":")[1].trim().split(" ")[0].split(",")[1]);

		}
	}
}
