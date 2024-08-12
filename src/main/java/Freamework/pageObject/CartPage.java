package Freamework.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

		// TODO Auto-generated constructor stub
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> expectedProduct;

	@FindBy(css = ".totalRow button")
	WebElement checkOut;
	
	By CheckoutButton =By.cssSelector(".totalRow button");

	public Boolean verifyProduct(String productName) {

		Boolean match = expectedProduct.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

		return match;

	}

	public CheckOutPage checkOut() {
		
		waitUntil(CheckoutButton);
		
		checkOut.click();
		
		CheckOutPage checkOutPage=new CheckOutPage(driver);
		return checkOutPage;
		
		
	}

}
