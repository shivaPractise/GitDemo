package Freamework.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {
	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "div[class*='mb-3']")
	List<WebElement> productList;

	By productsBy = By.cssSelector("div[class*='mb-3']");
	
	By addToCart =By.cssSelector("button[style='float: right;']");
	
	By toaster =By.cssSelector(".ng-animating");
	
	

	public List<WebElement> getProductList() {

		waitUntil(productsBy);

		return productList;

	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product -> product
				.findElement(By.cssSelector("h5[style='text-transform: uppercase;']")).getText().equals(productName))
				.findFirst().orElse(null);
		
		return prod;
		
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		
		waitUntil(toaster);
		
		waitUntilInvisibilityOf(toaster);

	}
	
	
}
