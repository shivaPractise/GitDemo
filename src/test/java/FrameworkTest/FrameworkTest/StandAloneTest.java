package FrameworkTest.FrameworkTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.AbstractComponents.AbstractComponents;
import FrameworkTest.TestComponents.BaseTest;
import Freamework.pageObject.CartPage;
import Freamework.pageObject.CheckOutPage;
import Freamework.pageObject.ConfirmationPage;
import Freamework.pageObject.LandingPage;
import Freamework.pageObject.OrdersPage;
import Freamework.pageObject.ProductCatalouge;

public class StandAloneTest extends BaseTest {

	String productToBeAdded = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })

	public void standAlone(HashMap<String, String> input) throws InterruptedException, IOException {

		String expectedTitle = "Thankyou for the order.";

		ProductCatalouge productCataloge = landingPage.loginApp(input.get("email"), input.get("password"));

		List<WebElement> productList = productCataloge.getProductList();

		productCataloge.addProductToCart(input.get("productToBeAdded"));

		CartPage cartpage = productCataloge.goToCart();
		
		Thread.sleep(2000);

		Boolean match = cartpage.verifyProduct(input.get("productToBeAdded"));

		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartpage.checkOut();

		checkOutPage.selectCountry("India");

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String actualTitle = confirmationPage.verifyConfirmationMessage();

		Assert.assertTrue(expectedTitle.equalsIgnoreCase(actualTitle));

		// WebDriverManager.chromedriver().setup();

		// System.setProperty("WebDriver.Chrome.Driver",
		// "C:/Users/ShivakrishnaSakkaram/Documents/chromedriver-win64");

		// WebDriver driver = new ChromeDriver();

		// driver.get("https://rahulshettyacademy.com/client/");

		// driver.manage().window().maximize();

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));

		/*
		 * LandingPage landingPage = new LandingPage(driver);
		 * 
		 * landingPage.goTo();
		 */

		// List<WebElement> productList =
		// driver.findElements(By.cssSelector("div[class*='mb-3']"));

		// wait.until(ExpectedConditions
		// .visibilityOfElementLocated(By.cssSelector("h5[style='text-transform:
		// uppercase;']")));

		/*
		 * WebElement prod = productList.stream().filter(product -> product
		 * .findElement(By.cssSelector("h5[style='text-transform: uppercase;']")).
		 * getText().equals(productName)) .findFirst().orElse(null);
		 */

		// prod.findElement(By.cssSelector("button[style='float: right;']")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		// driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		// List<WebElement> expectedProd =
		// driver.findElements(By.cssSelector(".cartSection h3"));

		// Boolean Match = expectedProd.stream().anyMatch(product ->
		// product.getText().equalsIgnoreCase(productName));

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow
		// button")));

		// driver.findElement(By.cssSelector(".totalRow button")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select
		// Country']")));

		/*
		 * Actions a = new Actions(driver); a.sendKeys(driver.findElement(By.
		 * cssSelector("input[placeholder='Select Country']")), "India").build()
		 * .perform();
		 */
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));

		// driver.findElement(By.xpath("//body//app-root//button[2]")).click();

		// Thread.sleep(2000);
		/*
		 * wait.until(ExpectedConditions .elementToBeClickable(By.
		 * xpath("//a[@class='btnn action__submit ng-star-inserted']")));
		 */

		// a.scrollToElement(driver.findElement(By.xpath("//a[@class='btnn
		// action__submit ng-star-inserted']")));

		/*
		 * a.moveToElement(driver.findElement(By.
		 * xpath("//a[@class='btnn action__submit ng-star-inserted']"))).click()
		 * .build().perform();
		 */

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class='hero-primary']")));

		/*
		 * String expectedTitle =
		 * driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		 * 
		 * System.out.println(expectedTitle);
		 */

	}

	@Test(dependsOnMethods = { "standAlone" })
	public void orderHistoryTest() {

		ProductCatalouge productCataloge = landingPage.loginApp("shiva11@gmail.com", "Avankia@11");

		OrdersPage ordersPage = productCataloge.goToOrdersPage();
		ordersPage.verifyOrderDisplay(productToBeAdded);
	}
	
	

	@DataProvider

	public Object[][] getData() throws IOException {

		// return new Object[][]{{"shiva11@gmail.com","Avankia@11","ZARA COAT
		// 3"},{"shiva22@gmail.com","Avankia@11","ZARA COAT 3"}};

		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "shiva11@gmail.com"); map.put("password", "Avankia@11");
		 * map.put("productToBeAdded", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "shiva22@gmail.com"); map1.put("password", "Avankia@11");
		 * map1.put("productToBeAdded", "ZARA COAT 3");
		 */
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\Framework\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	
}
