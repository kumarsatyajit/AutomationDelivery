package automation.pageObjectModels;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
	WebElement addToBasket;

	@FindBy(xpath = "//div[@class='woocommerce-message']")
	WebElement addedToBasketMessgae;

	@FindBy(xpath = "//nav[@class='woocommerce-breadcrumb']/a[1]")
	WebElement homeOption;

	@FindBy(xpath = "//div[@id='tab-description']//p")
	WebElement bookDescriptionText;

	@FindBy(xpath = "//li[contains(@class,'description_tab')]/a")
	WebElement descriptionSection;

	@FindBy(xpath = "//li[contains(@class,'reviews_tab')]/a")
	WebElement reviewsSection;

	@FindBy(xpath = "//div[@id='reviews']/div/p")
	WebElement noReviews;

	@FindBy(xpath = "//div[@class='description']/p")
	WebElement reviews;

	By reviewBy = By.xpath("//div[@id='reviews']/div/p");

	@FindBy(xpath = "//p[@class='stars']//a")
	List<WebElement> stars;

	@FindBy(xpath = "//textarea[@id='comment']")
	WebElement reviewArea;

	@FindBy(xpath = "//input[@id='author']")
	WebElement authorName;

	@FindBy(xpath = "//input[@id='email']")
	WebElement authorEmail;

	@FindBy(xpath = "//input[@id='wp-comment-cookies-consent']")
	WebElement saveDetailsCheckbox;

	@FindBy(xpath = "//input[@id='submit']")
	WebElement submitButton;

	public void addProductToBasket() {
		addToBasket.click();
	}

	public boolean checkProductIsAdded(String productName) {
		addProductToBasket();
		waitForWebElementToBeAppear(addedToBasketMessgae);
		String text = addedToBasketMessgae.getText();
		return text.contains(productName);
	}

	public HomePage clickOnHomeOption() {
		scrollToTopOfWebPage();
		moveToWebElementClick(homeOption);
		return new HomePage(driver);
	}

	public boolean checkProductHasDescription() {
		scrollToWebElement(descriptionSection);
		moveToWebElementClick(descriptionSection);
		String text = bookDescriptionText.getText();
		return text.length() > 0;
	}

	public boolean isThereAnyReviews() {
		boolean flag = true;
		boolean displayed = reviews.isDisplayed();
		if (displayed == false) {
			boolean displayed2 = noReviews.isDisplayed();
			if (displayed2 == true)
				flag = false;
		}

		return flag;
	}

	public void makeReview(int rating, String message, String name, String email) {
		reviewsSection.click();
		// Click on star
		List<WebElement> ratingStar = new ArrayList<WebElement>(stars);
		for (int i = 0; i < rating; i++) {
			WebElement star = ratingStar.get(i);
			star.click();
		}

		// Write review and enter details
		reviewArea.sendKeys(message);
		authorName.sendKeys(name);
		authorEmail.sendKeys(email);
		submitButton.click();
	}
}
