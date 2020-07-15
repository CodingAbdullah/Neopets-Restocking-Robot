package shops;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import items.FoodItem;
import util.JavaMailUtil;

public class FoodShopPage {
	private List<FoodItem> foodItemsList;
	private WebDriver driver;
	
	public FoodShopPage(WebDriver driver) throws MessagingException {
		this.driver = driver;
		this.foodItemsList = new ArrayList<FoodItem>();
		
		PageFactory.initElements(driver, this); // We are following the page factory model... very weak implementation tho :)
		foodItem(); // Start the automated process
	}
	
	public void foodItem() throws MessagingException {
		// Scrap through the shops item list using the driver, return a list to begin analysis. Shops can have a maximum of 48 items or 6 columns and 8 rows
		
		// Obtain the number of rows and columns of items
		int rows = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr")).size();
		int columns = 0;
		
		// Iterate through each item and investigate their names and descriptions (stock quantity, price)	
		for (int i = 1; i <= rows; i++) {
			columns = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[" + i + "]//td")).size();
			
			for (int j = 1; j <= columns; j++) {
				WebElement item = driver.findElement(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[" + i + "]//td[" + j + "]")); // Grid x by y so iterate items
				
				// Only considering those food items with stock less than or equal to 5 and value greater than 1000 NP, they are rare.
				if (filterItemQuantity(item.getText()) <= 5 && filterItemCost(item.getText()) > 1000) {
					
					// Neggs are the exception :)
					if (filterItemName(item.getText()).contains("Negg")) {
						foodItemsList.add(new FoodItem(filterItemName(item.getText()), filterItemQuantity(item.getText()), filterItemCost(item.getText()))); // populate the foodItemsList
					}
					else if (filterItemCost(item.getText()) == 2500) { // Exact cost items will not be considered, they are traps
						continue;
					}
					else {
						foodItemsList.add(new FoodItem(filterItemName(item.getText()), filterItemQuantity(item.getText()), filterItemCost(item.getText()))); // populate the foodItemsList
					}
				}
				else {
					continue;
				}
			}
		}
		
		// Once this process is complete, send email if there is a list of interesting food items 
		if (foodItemsList.size() > 0) {
			JavaMailUtil.sendEmail(foodItemsList);
		}		
	}
	
	private String filterItemName(String itemDescription) {
		return itemDescription.split("\\r?\\n")[0].trim(); // Filter the item name by returning the first line of description
	}
	
	private int filterItemQuantity(String itemDescription) {
		
		// Split on newline, obtain the second line, split that line on 'in' substring and select first element trimmed, represents # in stock.
		// Convert to Integer, all in one line, you got it? Good :)
		
		return Integer.parseInt(itemDescription.split("\\r?\\n")[1].split("in")[0].trim()); 
	}
	
	private int filterItemCost(String itemDescription) {
		
		// Ight, method chaining this stuff is easssyyy, I mean it's patterning and splitting :)	
		// Split on third line of description by newline, then split on colon select the numerical part and then concatenate the two strings separated by the comma, convert into integer :)
		// If the cost is greater than 999 NP, consider commas
		
		if (!itemDescription.contains(",")) {
			return Integer.parseInt(itemDescription.split("\\r?\\n")[2].split(":")[1].trim().split(" ")[0]);
		}
		else {
			return Integer.parseInt(itemDescription.split("\\r?\\n")[2].split(":")[1].trim().split(" ")[0].split(",")[0] + itemDescription.split("\\r?\\n")[2].split(":")[1].trim().split(" ")[0].split(",")[1]);

		}
	}
}