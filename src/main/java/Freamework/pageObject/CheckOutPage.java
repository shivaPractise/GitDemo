package Freamework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Framework.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	

	By countryOptions = By.cssSelector("input[placeholder='Select Country']");

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//body//app-root//button[2]")
	WebElement selectCountry;

	By searchResults = By.cssSelector("section[class*='ta-results']");

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;

	public void selectCountry(String countryName) {
	
		waitUntil(countryOptions);
		Actions a = new Actions(driver);

		a.sendKeys(country, countryName).build().perform();

		waitUntil(searchResults);

		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		Actions a = new Actions(driver);
		
		a.moveToElement(submit).click().build().perform();

		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}

}
