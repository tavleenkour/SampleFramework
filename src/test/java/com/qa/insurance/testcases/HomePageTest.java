package com.qa.insurance.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.insurance.base.TestBase;
import com.qa.insurance.pages.HomePage;
import com.qa.insurance.util.ExcelUtil;
import com.qa.insurance.util.TestUtil;

// ****** All assert conditions are specified in test methods. ********
public class HomePageTest extends TestBase
{
	
	HomePage homePage;
	TestUtil testUtil;
	String sheetName = "data";

	
	public HomePageTest()
	
	{
		super();
		
	}

	
	// ****** This method is only run once at the beginning of test suite. ******
	@BeforeSuite(alwaysRun = true)
	public void setUp() 
	{
		
		log.info("Set up is being initialized");
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();

	}
	
	
	@Test(priority=1, groups= {"Functional"})
	public void validateHomePageTitleTest()
	{    

		log.info("Validate Home Page Title Test");
		String homePageTitle = homePage.validateHomePageTitle();
		Assert.assertEquals(homePageTitle, "Smartesting Yest Insurance Simulator");
	}
	
	
	@Test(priority=2, groups= {"Regression"})
	public void validateSimulateButton()
	{     
		
		log.info("Validate Simulate Button Test");
		Boolean loginCTA = homePage.validateSimulateCTA();
		Assert.assertTrue(loginCTA);
	}
	
	
	@DataProvider
	public Object[][] getInsuranceTestData()
	{
		Object data[][] = ExcelUtil.getTestData(sheetName);
		return data;
	}
	
	
	// *********** Test Case 6 and Test Case 7 are getting failed intentionally by passing invalid data from excel file to check Failed State **********
	@Test(priority=3, dataProvider="getInsuranceTestData", dependsOnMethods = { "validateSimulateButton" },groups= {"Regression"})
	public void checkInsuranceValue(String carName, String carModel, String fuelType, String newCar, String firstUse, String name, 
			String licenseIssue, String zipcode, String bonus, String comprehensive , String thirdParty)

	{
	
		log.info("Check Insurance Value Test");
		log.warn("This test is dependent on Validate Simulate Button Test");
		Boolean annual_fee = homePage.getInfo(carName, carModel, fuelType, newCar, firstUse, name, licenseIssue, zipcode, bonus, comprehensive , thirdParty);
		Assert.assertTrue(annual_fee);
	}
	
	// ***** This method is only run once at the end of test suite. *****
	@AfterSuite(alwaysRun = true)
	public void tearDown()
	{

		driver.quit();
		
	}
		
	}
	
	



