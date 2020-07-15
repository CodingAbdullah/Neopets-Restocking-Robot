package runner;

import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.cdimascio.dotenv.Dotenv;
import shops.FoodShopPage;
import shops.LoginPage;

public class Runner {
	
	public static void main(String[] args) throws MessagingException {
		Dotenv dotenv = Dotenv.load(); // Load all environment variables (key value pairs) from the .env file in the root of project
		
		LoginPage loginPage;
		FoodShopPage foodShopPage;
		
		System.setProperty("webdriver.chrome.driver", dotenv.get("chrome_driver_path"));
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("http://www.neopets.com/login/");
		
		loginPage = new LoginPage(driver);
		
		loginPage.setLoginInputValue(dotenv.get("neo_username")); // Provide username and password to neopets account
		loginPage.setPasswordInputValue(dotenv.get("neo_password")); // Creds should be specified in a .env file in the root of project
		loginPage.clickLoginButtonElement();
		
		driver = loginPage.getWebDriver();
		
		driver.get("http://www.neopets.com/objects.phtml?obj_type=1&type=shop"); // After login, navigate to the food shop
		
		foodShopPage = new FoodShopPage(driver);
		
	}
}