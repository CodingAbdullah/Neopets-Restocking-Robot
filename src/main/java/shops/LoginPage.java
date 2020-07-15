package shops;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
		
	@FindBy(xpath="//body//div[@id='main']//table//tbody//tr//td//div[@class='welcomeContent']//div[@class='welcomeLogin']//form//div[@class='welcomeLoginContent']//div[@class='welcomeLoginUsernameInput']//input")
	private WebElement loginInputElement;
	
	@FindBy(xpath="//body//div[@id='main']//table//tbody//tr//td//div[@class='welcomeContent']//div[@class='welcomeLogin']//form//div[@class='welcomeLoginContent']//div[@class='welcomeLoginPasswordInput']//input")
	private WebElement passwordInputElement;
	
	@FindBy(xpath="//body//div[@id='main']//table//tbody//tr//td//div[@class='welcomeContent']//div[@class='welcomeLogin']//form//input[@type='submit']")
	private WebElement submitLoginButtonElement;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setLoginInputValue(String username) {
		this.loginInputElement.sendKeys(username);
	}
	
	public void setPasswordInputValue(String password) {
		this.passwordInputElement.sendKeys(password);
	}
	
	public void clickLoginButtonElement() {
		this.submitLoginButtonElement.click();
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}
}
