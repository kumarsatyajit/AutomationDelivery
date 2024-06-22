package automation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone {

	@Test
	public void sample() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://practice.automationtesting.in/");
		driver.navigate().to("");
		List<WebElement> menuButtons = driver.findElements(By.xpath("//ul[@class='main-nav']//a"));
		WebElement menuOption = menuButtons.stream()
				.filter(element -> element.getText().toLowerCase().equalsIgnoreCase("shop")).findFirst().orElse(null);
		menuOption.click();
		driver.findElement(By.xpath("//nav[@class='woocommerce-breadcrumb']/a")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//div[@class='n2-padding']//div[contains(@class,'n2-ss-slide n2-ss-canvas')]")));
		int count = driver
				.findElements(By.xpath("//div[@class='n2-padding']//div[contains(@class,'n2-ss-slide n2-ss-canvas')]"))
				.size();
		System.out.println(count);
		driver.quit();
	}
}
