package com.ecs.techtest.testpages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecs.techtest.base.TestBase;
import com.ecs.techtest.pages.ArrayChallenge;
import com.ecs.techtest.utils.TestUtil;

public class ArrayChallengeTest extends TestBase{

	TestUtil testUtil;
	ArrayChallenge challenge;
	
	public ArrayChallengeTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		browserLaunch();
		challenge = new ArrayChallenge();
	}
	
	
	@Test(priority=1)
	public void verifyArrayChallenge()
	{
		challenge.checkArrayChallenge();
	}
	
	@AfterMethod
	public void tearDown()
	{
		//driver.close();
	}
	
}
