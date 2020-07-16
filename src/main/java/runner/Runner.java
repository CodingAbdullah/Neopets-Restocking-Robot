package runner;

import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.cdimascio.dotenv.Dotenv;
import shops.FoodShopPage;
import shops.LoginPage;

public class Runner {
	
	public static void main(String[] args) throws MessagingException, InterruptedException {
		Dotenv dotenv = Dotenv.load(); // Load all environment variables (key value pairs) from the .env file in the root of project
		
		LoginPage loginPage;
		FoodShopPage foodShopPage;
		
		System.setProperty("webdriver.chrome.driver", dotenv.get("chrome_driver_path"));
		WebDriver driver = new ChromeDriver();
		
		final int SHOP_NUMBER = 1; // Change shop number to the corresponding shop (1 -> Food, 2 -> Potion, 9 -> Battle) 
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("http://www.neopets.com/login/");
		
		loginPage = new LoginPage(driver);
		
		loginPage.setLoginInputValue(dotenv.get("neo_username")); // Provide username and password to neopets account
		loginPage.setPasswordInputValue(dotenv.get("neo_password")); // Creds should be specified in a .env file in the root of project
		loginPage.clickLoginButtonElement();
		
		driver = loginPage.getWebDriver();
		
		driver.get("http://www.neopets.com/objects.phtml?type=shop&obj_type=" + SHOP_NUMBER); // After login, navigate to the requested shop
		
		foodShopPage = new FoodShopPage(driver);
		
	}
}