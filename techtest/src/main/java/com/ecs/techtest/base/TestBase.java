package com.ecs.techtest.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * @author Mayura Patil
 * Base class that defines common functionality for all test cases
 *
 */
public abstract class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;

	
	public TestBase() {

		try {
			prop = new Properties();
			InputStream resourceAsStream = this.getClass()
					.getResourceAsStream("/com/ecs/techtest/config/config.properties");
			prop.load(resourceAsStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initialises the driver to be used for testing
	 */
	public void browserLaunch() {
		try {
			String browserName = prop.getProperty("browser");
			if (browserName.equals("chrome")) {

				WebDriverManager.getInstance(DriverManagerType.CHROME).version(prop.getProperty("chromeVersion"))
						.setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-browser-side-navigation");
				options.addArguments("--disable-gpu");
				driver = new ChromeDriver(options);
				System.out.println("inside if ...chrome initialised");
			} else if (browserName.equals("Firefox")) {
				System.getProperty("webdriver.gecko.driver", "/Users/PATILSA/Downloads/geckodriver.exe");
				driver = new FirefoxDriver();
			}

			driver.manage().deleteAllCookies();
			driver.get(prop.getProperty("url"));

			wait = new WebDriverWait(driver, 20);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	/**
	 * This method wait till the visibility of element located.
	 * @param locator
	 * @return 
	 */
	public WebElement explicitWaitVisibilityOfElementLocated(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	/**
	 * This method wait till the visibility and click on element.
	 * @param locator
	 */
	public void elementWaitAndClick(By locator) {
		
		
		explicitWaitVisibilityOfElementLocated(locator).click();
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator))).click();

	}
	/**
	 * This method wait till visibility and pass string input to element
	 * @param locator
	 * @param text
	 */

	public void elementWaitAndSendKey(By locator, String text) {
		
		explicitWaitVisibilityOfElementLocated(locator).sendKeys(text);
	
	}

	/**
	 * This method wait till visibility and return if displayed.
	 * @param locator
	 * @return
	 */
	public boolean waitAndIsDisplayed(By locator) {
		return explicitWaitVisibilityOfElementLocated(locator).isDisplayed();
		}
}
