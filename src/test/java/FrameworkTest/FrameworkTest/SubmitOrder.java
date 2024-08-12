package FrameworkTest.FrameworkTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import FrameworkTest.TestComponents.Retry;

import FrameworkTest.TestComponents.BaseTest;
import Freamework.pageObject.ProductCatalouge;

public class SubmitOrder extends BaseTest {

	@Test(groups = {"errorHandling"}, retryAnalyzer = Retry.class)
	public void standAlone() throws InterruptedException, IOException {

		
		String expectedTitle = "Thankyou for the order.";

		String productToBeAdded = "ZARA COAT 3";

		ProductCatalouge productCataloge = landingPage.loginApp("shiva11@gmail.com", "1Avankia@11");
		
		Assert.assertEquals("Incorrect email or password.",landingPage.errorValidation());

		

	}

}
