package com.ecs.techtest.testpages;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecs.techtest.base.TestBase;
import com.ecs.techtest.pages.ArrayChallenge;
import com.ecs.techtest.utils.TestUtil;

/**
 * Testcase class to execute the test.
 * 
 * @author Mayura Patil
 *
 */
public class ArrayChallengeTest extends ArrayChallenge{


	public ArrayChallengeTest()
	{
		super();
	}
	
	/**
	 * This method launches the browser before every test
	 */
	@BeforeMethod
	public void setUp()
	{
		browserLaunch();
		
	}
	
	/**
	 * This method test array challenge.
	 */
	@Test(priority=1)
	public void verifyArrayChallenge()
	{
		Assert.assertTrue(checkArrayChallenge(), "Array challenge is not successful");		
	}
	
	/**
	 * This method closes browser after every test
	 */
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
}
