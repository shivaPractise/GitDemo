package Freamework.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//tbody//tr//td[2]")
	List<WebElement> ordersList;
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match = ordersList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

		return match;
		
	}
	
	

}
