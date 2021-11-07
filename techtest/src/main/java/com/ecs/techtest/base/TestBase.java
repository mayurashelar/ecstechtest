package com.ecs.techtest.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;

	
	
	public TestBase()
	{
		
		try {
			prop = new Properties();
			//FileInputStream fis = (FileInputStream) this.getClass().getResourceAsStream("config.properties");
			FileInputStream fis = new FileInputStream("C:\\MayuraWorkspace\\techtest\\src\\main\\resources\\com\\ecs\\techtest\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void browserLaunch()
	{
		try
		{
		String browserName = prop.getProperty("browser");
		System.out.println("browserName:- "+browserName);
		if(browserName.equals("chrome")) 
		{
			System.out.println("inside if ...going to init chrome drivers");
			System.out.println(prop.getProperty("url"));
			//System.setProperty("webdriver.chrome.driver", "C:\\MayuraWorkspace\\test\\chromedriver.exe");
			 System.setProperty("webdriver.chrome.whitelistedIps", "");
			System.out.println("inside if ...property is set");
			System.out.println(prop.getProperty("chromeVersion"));
			WebDriverManager.getInstance(DriverManagerType.CHROME).version(prop.getProperty("chromeVersion")).setup();
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
		}else if(browserName.equals("Firefox"))
		{
			System.getProperty("webdriver.gecko.driver", "/Users/PATILSA/Downloads/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}

}
}
