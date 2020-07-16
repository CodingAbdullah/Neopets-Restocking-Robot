package pages;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import items.PotionItem;
import util.JavaMailUtil;

public class PotionShopPage extends ShopPage {
	private WebDriver driver;
	private List<PotionItem> potionList;
	
	public PotionShopPage(WebDriver driver) throws MessagingException, InterruptedException {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		this.potionList = new ArrayList<PotionItem>();
		
		for (int i = 0 ; i < 5760; i++) { // Have it run on 15 second intervals => 4/minute, 240/hour, 5760/day :)
			findItem(); // Start the automated process
			Thread.sleep(15000L); // Set to 15 seconds, but you can have it refresh any time you want it to (time is entered as a long and is in milliseconds) :)
			driver.navigate().refresh();
		}
	}
	
	@Override
	protected void findItem() throws MessagingException {
		List<PotionItem> foundPotions = potionExists();
		
		if (foundPotions.size() != 0) {
			JavaMailUtil.sendEmail(foundPotions);
		}
		
		this.potionList.removeAll(potionList);
	}	

	private List<PotionItem> potionExists() {
		int rows = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr")).size();
		int columns = 0;
			
		for (int i = 1; i <= rows; i++) {
			columns = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[" + i + "]//td")).size();
				
			for (int j = 1; j <= columns; j++) {
				WebElement item = driver.findElement(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[" + i + "]//td[" + j + "]")); // Grid x by y so iterate items
					
				// Filter item names to figure out which one will be considered, using the item to be considered list
				if (itemToBeConsidered(filterItemName(item.getText()))) {
					this.potionList.add(new PotionItem(filterItemName(item.getText()), filterItemCost(item.getText())));
				}				
			}	
		}
		return potionList;
	}	
	
	private boolean itemToBeConsidered(String itemName) {
		// Super Rare ones
		if (itemName.contains("Draik")) {
			return true;
		}
		else if (itemName.contains("Krawk")) {
			return true;
		}
		else if (itemName.contains("Lutari")) {
			return true;
		}
		else if (itemName.contains("Grarrl")) {
			return true;
		}
		else if (itemName.contains("Hissi")) {
			return true;
		}
		else if (itemName.contains("Darigan")) {
			return true;
		}
		else if (itemName.contains("Wraith")) {
			return true;
		}
		else if (itemName.contains("Plushie")) {
			return true;
		}
		else if (itemName.contains("Faerie")) {
			return true;
		}
		else if (itemName.contains("Maractite")) {
			return true;
		}
		else if (itemName.contains("Ogrin")) { // Pet to not be considered, these are overrated :)
			return false;
		}			
		else if (itemName.contains("Vandagyre")) {
			return false;
		}
		else if (itemName.contains("Strange Potion")) {
			return true;
		}
		else if (itemName.contains("Rainbow Swirly Potion")) {
			return true;
		}
		else if (itemName.contains("Morphing Potion")) {
			return true;
		}
		else {
			return false;
		}
	}
}
