package Freamework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	By confirmationMessage = By.cssSelector("h1[class='hero-primary']");

	@FindBy(css = "h1[class='hero-primary']")
	WebElement expectedTitleElement;

	public String verifyConfirmationMessage() {

		waitUntil(confirmationMessage);
		String actualTitle = expectedTitleElement.getText();

		System.out.println(actualTitle);
		return actualTitle;

	}

}
