package runner;

import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.cdimascio.dotenv.Dotenv;
import pages.FoodShopPage;
import pages.LoginPage;
import pages.PharmaShopPage;
import pages.PotionShopPage;

public class Runner {
	
	public static void main(String[] args) throws MessagingException, InterruptedException {
		Dotenv dotenv = Dotenv.load(); // Load all environment variables (key value pairs) from the .env file in the root of project
		
		LoginPage loginPage;
		FoodShopPage foodShopPage;
		PharmaShopPage pharmaShopPage;
		PotionShopPage potionShopPage;
		
		System.setProperty("webdriver.chrome.driver", dotenv.get("chrome_driver_path"));
		WebDriver driver = new ChromeDriver();
		
		final int [] SHOP_NUMBERS = new int[] {1, 2, 13}; // Corresponding shops (1 -> Food, 2 -> Potion, 13 -> Pharma) 
		
		int randomShop = (int)((int) 3 * Math.random() + 1); // Randomly select which of the three shops to restock from
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("http://www.neopets.com/login/");
		
		loginPage = new LoginPage(driver);
		
		loginPage.setLoginInputValue(dotenv.get("neo_username")); // Provide username and password to neopets account
		loginPage.setPasswordInputValue(dotenv.get("neo_password")); // Creds should be specified in a .env file in the root of project
		loginPage.clickLoginButtonElement();
		
		driver = loginPage.getWebDriver();
		
		switch (randomShop) {
		case 1:
			driver.get("http://www.neopets.com/objects.phtml?type=shop&obj_type=" + SHOP_NUMBERS[randomShop - 1]); // After login, navigate to the random shop
			foodShopPage = new FoodShopPage(driver);
			break;
		case 2:
			driver.get("http://www.neopets.com/objects.phtml?type=shop&obj_type=" + SHOP_NUMBERS[randomShop - 1]); // After login, navigate to the random shop
		 	potionShopPage = new PotionShopPage(driver);
			break;
		case 3:
			driver.get("http://www.neopets.com/objects.phtml?type=shop&obj_type=" + SHOP_NUMBERS[randomShop - 1]); // After login, navigate to the random shop
			pharmaShopPage = new PharmaShopPage(driver);
			break;
		}	
		
		driver.close(); // Close the driver
	}
}