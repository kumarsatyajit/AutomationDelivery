package automation.tests;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.AbstractComponents.BaseTest;
import automation.pageObjectModels.HomePage;
import automation.pageObjectModels.ShopPage;

public class HomePageTestCases extends BaseTest {

	@Test
	public void homePageWithThreeSlidersOnly() throws IOException {
		HomePage homePage = openUrl();
		ShopPage shopPage = homePage.clickOnTargetMenuButton("shop");
		homePage = shopPage.clickOnHomeOption();
		int slidersCount = homePage.getSlidersCount();
		Assert.assertTrue(slidersCount == 3, "Slide count is not 3.");
		terminateWebDriver();
	}

	@Test
	public void homePageWithThreeArrivalsOnly() throws IOException {
		HomePage homePage = openUrl();
		ShopPage shopPage = homePage.clickOnTargetMenuButton("shop");
		homePage = shopPage.clickOnHomeOption();
		int count = homePage.getNewArrivalsCount();
		Assert.assertTrue(count == 3, "New Arrivals count is not 3.");
		terminateWebDriver();
	}

	@Test
	public void homePageImagesInArrivalsShouldNavigate() throws IOException {
		HomePage homePage = openUrl();
		ShopPage shopPage = homePage.clickOnTargetMenuButton("shop");
		homePage = shopPage.clickOnHomeOption();
		int count = homePage.getNewArrivalsCount();
		Assert.assertTrue(count == 3);
		List<Boolean> list = homePage.checkEachNewArrialCanAddableOrNot().stream().filter(e -> e.booleanValue() == true)
				.collect(Collectors.toList());
		Assert.assertTrue(count == list.size());
		terminateWebDriver();
	}

	@Test
	public void homePageArrivalsImagesDescription() throws IOException {
		HomePage homePage = openUrl();
		ShopPage shopPage = homePage.clickOnTargetMenuButton("shop");
		homePage = shopPage.clickOnHomeOption();
		int count = homePage.getNewArrivalsCount();
		Assert.assertTrue(count == 3, "New Arrival count is 3");
		List<Boolean> list = homePage.checkEachNewArrivalHasDescription().stream().filter(e -> e.booleanValue() == true)
				.collect(Collectors.toList());
		Assert.assertTrue(count == list.size());
		terminateWebDriver();
	}

	//@Test
	public void homePageArrivalsImagesReviews() throws IOException {
		HomePage homePage = openUrl();
		ShopPage shopPage = homePage.clickOnTargetMenuButton("shop");
		homePage = shopPage.clickOnHomeOption();
		int arrivalsCount = homePage.getNewArrivalsCount();
		Assert.assertTrue(arrivalsCount == 3, "New Arrivals count is " + arrivalsCount + " not 3.");
		List<Boolean> list = homePage.checkEachNewArrivalHasReviews().stream().filter(e -> e.booleanValue() == true)
				.collect(Collectors.toList());
		Assert.assertTrue(arrivalsCount == list.size());
		terminateWebDriver();
	}
}
