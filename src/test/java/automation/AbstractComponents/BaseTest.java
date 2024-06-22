package automation.AbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import automation.pageObjectModels.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	Properties prop;

	public Properties getProperties() throws IOException {
		File file = new File(
				System.getProperty("user.dir") + "/src/main/java/automation/resources/GlobalData.properties");
		FileInputStream inputStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(inputStream);

		return prop;
	}

	public WebDriver initializeDriver() throws IOException {
		String systemVariable = System.getProperty("browser");
		String propertyFileValue = getProperties().getProperty("browser");
		String browser = systemVariable != null ? systemVariable : propertyFileValue;

		if (browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			if (browser.contains("headless"))
				options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browser.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			if (browser.contains("headless"))
				options.addArguments("headless");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browser.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.setAcceptInsecureCerts(true);
			if (browser.contains("headless"))
				options.addArguments("headless");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().setSize(new Dimension(1440, 900));
		}

		return driver;
	}

	public HomePage openUrl() throws IOException {
		String url = getProperties().getProperty("url");
		driver = initializeDriver();
		driver.navigate().to(url);
		HomePage homePage = new HomePage(driver);

		return homePage;
	}

	public String getScreenshots(WebDriver driver, String testCaseName) throws IOException {
		TakesScreenshot shot = (TakesScreenshot) driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "/screentShots/" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);

		return System.getProperty("user.dir") + "/screentShots/" + testCaseName + ".png";
	}

	public void closeWindow() {
		driver.close();
	}

	public void terminateWebDriver() {
		driver.quit();
	}
}
