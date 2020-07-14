package restock_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MagicShopPage {
	
	private WebDriver driver;
	
	public MagicShopPage(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		findPotionItem();
	}
	
	public void findPotionItem() {
		// Scrap through the shops item list using the driver, return a list to begin analysis
		List<WebElement> shopItemList = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[1]//td"));
	
		for (int i = 1; i <= shopItemList.size(); i++) { // Iterate through the list of items in the shop
			WebElement item = driver.findElement(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[1]//td[" + i + "]"));
			System.out.println(item.getText());
		}
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
		else if (itemName.contains("Ogrin")) { // Pets to not be considered, these are overrated :)
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
