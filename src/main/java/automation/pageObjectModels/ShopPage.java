package automation.pageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class ShopPage extends AbstractComponent {

	WebDriver driver;

	public ShopPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav[@class='woocommerce-breadcrumb']/a")
	WebElement homeButton;

	public HomePage clickOnHomeOption() {
		homeButton.click();
		return new HomePage(driver);
	}

}
