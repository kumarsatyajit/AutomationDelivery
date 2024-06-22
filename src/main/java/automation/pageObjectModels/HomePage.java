package automation.pageObjectModels;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='main-nav']//a")
	List<WebElement> menuButtons;

	@FindBy(xpath = "//div[@class='n2-padding']//div[contains(@class,'n2-ss-slide n2-ss-canvas')]")
	List<WebElement> sliders;

	By slidersBy = By.xpath("//div[@class='n2-padding']//div[contains(@class,'n2-ss-slide n2-ss-canvas')]");

	@FindBy(xpath = "//div[@class='woocommerce']")
	List<WebElement> newArrivals;

	By newArrivalsBy = By.xpath("//div[@class='woocommerce']");
	By newArrivalNameBy = By.xpath(".//h3");
	By newArrivalImg = By.xpath(".//img");

	public ShopPage clickOnTargetMenuButton(String menuButtonName) {
		WebElement menuOption = menuButtons.stream()
				.filter(element -> element.getText().toLowerCase().equalsIgnoreCase(menuButtonName)).findFirst()
				.orElse(null);
		menuOption.click();

		return new ShopPage(driver);
	}

	public int getSlidersCount() {
		waitForWebElementToBePresent(slidersBy);
		int count = sliders.size();
		return count;
	}

	public int getNewArrivalsCount() {
		int count = newArrivals.size();
		return count;
	}

	public ProductPage clickOnNewArrivalImage(WebElement arrival) {
		arrival.findElement(newArrivalImg).click();
		return new ProductPage(driver);
	}

	public List<Boolean> checkEachNewArrialCanAddableOrNot() {
		List<Boolean> result = new ArrayList<Boolean>();
		int iteration = getNewArrivalsCount();
		for (int i = 0; i < iteration; i++) {
			scrollDown(2000);
			WebElement arrival = newArrivals.get(i);
			String arrivalName = arrival.findElement(newArrivalNameBy).getText();
			ProductPage productPage = clickOnNewArrivalImage(arrival);
			boolean isAdded = productPage.checkProductIsAdded(arrivalName);
			result.add(isAdded);
			productPage.clickOnHomeOption();
		}
		return result;
	}

	public List<Boolean> checkEachNewArrivalHasDescription() {
		List<Boolean> result = new ArrayList<Boolean>();
		int count = getNewArrivalsCount();
		for (int i = 0; i < count; i++) {
			scrollDown(900);
			WebElement arrival = newArrivals.get(i);
			String arrivalName = arrival.findElement(newArrivalNameBy).getText();
			ProductPage productPage = clickOnNewArrivalImage(arrival);
			boolean isAdded = productPage.checkProductIsAdded(arrivalName);
			if (isAdded) {
				boolean hasDescription = productPage.checkProductHasDescription();
				result.add(hasDescription);
			}
			productPage.clickOnHomeOption();
		}

		return result;
	}

	public List<Boolean> checkEachNewArrivalHasReviews() {
		List<Boolean> result = new ArrayList<Boolean>();
		int count = getNewArrivalsCount();
		for (int i = 0; i < count; i++) {
			scrollDown(900);
			WebElement arrival = newArrivals.get(i);
			String arrivalName = arrival.findElement(newArrivalNameBy).getText();
			ProductPage productPage = clickOnNewArrivalImage(arrival);
			boolean isAdded = productPage.checkProductIsAdded(arrivalName);
			if (isAdded) {
				boolean anyReviews = productPage.isThereAnyReviews();
				if (anyReviews) {
					result.add(anyReviews);
					productPage.clickOnHomeOption();
				} else {
					productPage.makeReview(3, "Good book", "satya", "satya@gmail.com");
					anyReviews = productPage.isThereAnyReviews();
					if (anyReviews) {
						result.add(anyReviews);
						productPage.clickOnHomeOption();
					}
				}
			}
		}

		return result;
	}

}
