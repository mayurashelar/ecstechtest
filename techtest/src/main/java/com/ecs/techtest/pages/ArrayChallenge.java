package com.ecs.techtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecs.techtest.base.TestBase;
import com.ecs.techtest.utils.TestUtil;

public class ArrayChallenge extends TestBase {

	TestUtil testUtil = new TestUtil();

	By renderButton = By.xpath("//span[contains(text(),'Render the Challenge')]");
	By webTable = By.xpath("//table/tbody");
	By yourName = By.xpath("//input[contains(@id,'undefined-YourName')]");
	By submitAnswer = By.xpath("//span[contains(text(),'Submit Answers')]");
	

	public ArrayChallenge() {
		PageFactory.initElements(driver, this);

	}

	public void checkArrayChallenge() {
		
		int[] array = null;
		int arrayIndex = 0;
		testUtil.explicitWaitvisibilityOfElementLocated(renderButton);
		driver.findElement(renderButton).click(); // testUtil.doubleClick(renderButton);
		testUtil.explicitWaitvisibilityOfElementLocated(webTable);
		List<WebElement> rowList = driver.findElements(By.xpath("//table/tbody/tr"));
		
		
		for(int i = 1; i<=rowList.size();i++)
		{
			List<WebElement> colList = driver.findElements(By.xpath("//table/tbody/tr["+i+"]/td"));
			array = new int[colList.size()];
			for(int j=1;j<=colList.size();j++)
			{
				array[j-1] = Integer.valueOf(driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+j+"]")).getText()).intValue();
					
			}
			
			arrayIndex = testUtil.arrayIndex(array);
			driver.findElement(By.xpath("//input[contains(@id,'undefined-submitchallenge"+i+"')]")).sendKeys(""+arrayIndex);
					
		}		
		driver.findElement(yourName).sendKeys(prop.getProperty("yourName"));
		new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOfElementLocated(submitAnswer));
		driver.findElement(submitAnswer).click();
		
	}

}
