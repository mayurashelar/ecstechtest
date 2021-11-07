package com.ecs.techtest.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecs.techtest.base.TestBase;

public class TestUtil extends TestBase {

	public static WebDriverWait wait = new WebDriverWait(driver, 20);

	public void explicitWaitvisibilityOfElementLocated(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public int arrayIndex(int[] array) {
		int frontVal = array[0];
		int backVal = array[array.length - 1];
		int i = 0;
		int j;

		try {
			for (i = 1, j = array.length - 2; i < array.length;) {
				if (i == array.length - 1 || j == 0)
				{
					i = 0;
					break;
				}
				if (frontVal < backVal) {

					frontVal = frontVal + array[i];
					i++;
				} else if (backVal < frontVal) {

					backVal = backVal + array[j];
					j--;

				} else if (backVal == frontVal) {
					break;
				}

			}

		} catch (Exception e) {
			System.out.println("Exception occured in finding array index");
		}

		return i;

	}

	/*
	 * public static void main(String[] args) { int[] array = new
	 * int[]{110,10,20,10,10,100,30,20}; //{} int i = arrayIndex(array);
	 * System.out.println("Arrray index " +i); }
	 */

}
