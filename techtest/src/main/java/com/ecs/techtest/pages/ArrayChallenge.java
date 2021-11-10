package com.ecs.techtest.pages;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ecs.techtest.base.TestBase;
import com.ecs.techtest.utils.TestUtil;
/**
 * This class contains the testing scenario.
 * 
 * @author Mayura Patil
 *
 */
public class ArrayChallenge extends TestBase {

	private static final By RENDER_BUTTON = By.xpath("//span[contains(text(),'Render the Challenge')]");
	private static final By WEB_TABLE = By.xpath("//table/tbody");
	private static final By YOUR_NAME = By.xpath("//input[contains(@id,'undefined-YourName')]");
	private static final By SUBMIT_ANSWER = By.xpath("//span[contains(text(),'Submit Answers')]");
	private static final By TABLE_ROW = By.xpath("//table/tbody/tr");
	private static final By CONGRATS_TEXT = By.xpath("//div[contains(text(),'Congratulations')]");

	public boolean checkArrayChallenge() {
		
		try {
			final ArrayIndexWrapper arrayIndex = new ArrayIndexWrapper();
			elementWaitAndClick(RENDER_BUTTON);
			explicitWaitVisibilityOfElementLocated(WEB_TABLE);
			List<WebElement> rowList = driver.findElements(TABLE_ROW);
			final AtomicInteger counter = new AtomicInteger(0);
			rowList.forEach(new Consumer<WebElement>() {

				public void accept(WebElement t) {
					List<Integer> list = t.findElements(By.tagName("td")).stream()
							.map(element -> Integer.valueOf(element.getText())).collect(Collectors.toList());
					arrayIndex.setArrayIndex(TestUtil.calculateIndex(list, list.size()/2));
					driver.findElement(By.xpath(
							"//input[contains(@id,'undefined-submitchallenge" + (counter.incrementAndGet()) + "')]"))
							.sendKeys("" + arrayIndex.getArrayIndex());
				}

			});

			elementWaitAndSendKey(YOUR_NAME, prop.getProperty("yourName"));
			elementWaitAndClick(SUBMIT_ANSWER);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return waitAndIsDisplayed(CONGRATS_TEXT);
	}

	private class ArrayIndexWrapper {
		int arrayIndex = 0;

		public void setArrayIndex(int arrayIndex) {
			this.arrayIndex = arrayIndex;
		}

		public int getArrayIndex() {
			return this.arrayIndex;
		}

	}

}
