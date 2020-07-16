package pages;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PotionShopPage extends ShopPage {
	private WebDriver driver;
	
	public PotionShopPage(WebDriver driver) throws MessagingException {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		findItem();
	}
	
	@Override
	protected void findItem() throws MessagingException {
		
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
