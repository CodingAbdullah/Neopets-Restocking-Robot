package runner;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.cdimascio.dotenv.Dotenv;
import restock_pages.MagicShopPage;
import restock_pages.LoginPage;

public class Runner {
	
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load(); // Load all environment variables (key value pairs) from the .env file in the root of project
		
		LoginPage loginPage;
		MagicShopPage magicShopPage;
		
		System.setProperty("webdriver.chrome.driver", dotenv.get("chrome_driver_path"));
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://www.neopets.com/login/");
		
		loginPage = new LoginPage(driver);
		
		loginPage.setLoginInputValue(dotenv.get("neo_username")); // Provide username and password to neopets account
		loginPage.setPasswordInputValue(dotenv.get("neo_password")); // Creds should be specified in a .env file in the root of project
		loginPage.clickLoginButtonElement();
		
		driver = loginPage.getWebDriver();
		
		driver.get("http://www.neopets.com/objects.phtml?obj_type=2&type=shop");
		
		magicShopPage = new MagicShopPage(driver);
		
	}
}