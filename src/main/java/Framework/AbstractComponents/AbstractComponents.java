package Framework.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Freamework.pageObject.CartPage;
import Freamework.pageObject.OrdersPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub

		super();
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	public void waitUntil(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitUntilInvisibilityOf(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitUntilvisibilityOfWebElement(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf((WebElement) findBy));
	}

	@FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement goToCart;
	
	@FindBy(css = "button[routerlink='/dashboard/myorders']")
	WebElement goToOrders;

	public CartPage goToCart() {

		goToCart.click();
		
		CartPage cartpage=new CartPage(driver);
		
		return cartpage;
	}
	
	public OrdersPage goToOrdersPage() {
		
		waitUntilvisibilityOfWebElement(goToOrders);
		
		goToOrders.click();	
		
		OrdersPage ordersPage=new OrdersPage(driver);
		
		return ordersPage;
	}

}
