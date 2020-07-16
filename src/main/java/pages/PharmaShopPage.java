package pages;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import items.PharmaItem;
import util.JavaMailUtil;

public class PharmaShopPage extends ShopPage {
	List<PharmaItem> pharmaList;
	private WebDriver driver;
	
	public PharmaShopPage(WebDriver driver) throws MessagingException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		this.pharmaList = new ArrayList<PharmaItem>();
		findItem();
	}
	
	@Override
	protected void findItem() throws MessagingException {
		// Iterate through each item and investigate their names and descriptions (stock quantity, price)	
		
		if (itemExists().size() != 0) {
			JavaMailUtil.sendEmail(itemExists());
		}
	}
	
	private List<PharmaItem> itemExists() {
		int rows = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr")).size();
		int columns = 0;
		
		for (int i = 1; i <= rows; i++) {
			columns = driver.findElements(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[" + i + "]//td")).size();
			
			for (int j = 1; j <= columns; j++) {
				WebElement item = driver.findElement(By.xpath("//body//div[@id='content']//table//form[2]//table//table//tbody//tr[2]//tr[" + i + "]//td[" + j + "]")); // Grid x by y so iterate items
				
				// There is only one rare item in the shop, compare it to that particular item name
				
				if (filterItemName(item.getText()).equals(PharmaItem.URL_STRING)){
					this.pharmaList.add(new PharmaItem(filterItemName(item.getText()), filterItemCost(item.getText())));
					System.out.println(item.getText());
					return this.pharmaList;
				}	
			}
		}
		return pharmaList;
	}

}
