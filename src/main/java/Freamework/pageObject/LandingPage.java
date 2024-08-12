package Freamework.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	

	public ProductCatalouge loginApp(String email, String password1) {

		userEmail.sendKeys(email);
		userPassword.sendKeys(password1);
		login.click();

		ProductCatalouge productCataloge = new ProductCatalouge(driver);

		return productCataloge;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		
	

	}
	
	public String errorValidation() {
		
		waitUntilvisibilityOfWebElement(errorMessage);
		return errorMessage.getText();
		
		
	}

}
