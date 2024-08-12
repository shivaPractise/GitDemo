package FrameworkTest.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Freamework.pageObject.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Framework\\Resources\\GlobalData.properties");

		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			ChromeOptions options=new ChromeOptions();
			
			System.setProperty("WebDriver.Chrome.Driver", "C:/Users/ShivakrishnaSakkaram/Documents/chromedriver-win64");
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));

		} else if (browserName.equalsIgnoreCase("firefox")) {

//			System.setProperty("webdriver.gecko.driver",
//					"C:/Users/ShivakrishnaSakkaram/Documents/geckodriver-v0.35.0-win-aarch64");
			
			//FirefoxOptions options = new FirefoxOptions();
			//options.setHeadless(true); // If you want to run Firefox in headless mode
			 driver = new FirefoxDriver();


//			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(groups = { "Purchase", "errorHanding" })
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();

		landingPage = new LandingPage(driver);

		landingPage.goTo();

		return landingPage;

	}

	@AfterMethod(groups = { "Purchase", "errorHanding" })
	public void tearDown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(Filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

}
