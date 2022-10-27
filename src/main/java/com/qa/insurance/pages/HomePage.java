package com.qa.insurance.pages;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.insurance.base.TestBase;
import com.qa.insurance.util.CalenderUtil;



public class HomePage extends TestBase 

{ 
	
	
	
	Select select;
	boolean boolNewCar;
	boolean boolComprehensive;
	boolean boolThirdParty;
	String inputFormat = "dd-MMMM-yyyy";
	String outputFormat = "yyyyyy-MM-dd";	
	StringBuilder sb;
	int i;
	
	
  	    @FindBy(xpath="//*[@id=\"simulateButton\"]")
		@CacheLookup
		WebElement simulateCTA;
		
		@FindBy(xpath="//*[@id=\"carBrand\"]")
		WebElement c_brand;
		
		@FindBy(xpath="//*[@id=\"carModel\"]")
		WebElement c_model;
		
		@FindBy(xpath="//*[@id=\"fuelType\"]")
		WebElement fuel_type;
		
		@FindBy(xpath="//*[@id=\"isNewCar\"]")
		WebElement is_new;
		
		@FindBy(xpath="//*[@id=\"firstUseYear\"]")
		WebElement first_use;
		
		@FindBy(xpath="//*[@id=\"name\"]")
		WebElement user_name;
		
		@FindBy(xpath="//*[@id=\"licenseDate\"]")
		WebElement license_date;
		
		@FindBy(xpath="//*[@id=\"zipCode\"]")
		WebElement zip_code;
		
		@FindBy(xpath="//*[@id=\"bonusMalus\"]")
		WebElement bonus_malus;
		
		@FindBy(xpath="//*[@id=\"comprehensive\"]")
		WebElement compreh;
		
		@FindBy(xpath="//*[@id=\"thirdParty\"]")
		WebElement third_party;
		
		@FindBy(xpath="//*[@id=\"fee\"]")
		WebElement fee;
		
		public HomePage() {
			
			PageFactory.initElements(driver, this);
		}	
		
		
		public String validateHomePageTitle()
		
		{
			return driver.getTitle();
		}
		
		public boolean getInfo(String carName, String carModel, String fuelType, String newCar, String firstUse, String name, String licenseIssue, String zipcode, String bonus, String comprehensive , String thirdParty)
	
		{
			
		
		select = new Select(c_brand);
		select.selectByVisibleText(carName);
		
		select = new Select(c_model);
		select.selectByVisibleText(carModel);
		
		select = new Select(fuel_type);
		select.selectByVisibleText(fuelType);
		
	  boolNewCar = Boolean.parseBoolean(newCar);
	  
	 i=1;
	 sb = new StringBuilder(firstUse);
		while(i < 3)
		{
			sb.deleteCharAt(sb.length()-1);	
			i++;
		} 
	  
	  
	  if(boolNewCar == true)
		{
		  is_new.click();
		}
		else
		{
			first_use.clear();
			first_use.sendKeys(String.valueOf(sb));
		}
		
	  user_name.clear();
	  user_name.sendKeys(name);
		
		((JavascriptExecutor)driver).executeScript("document.getElementById('licenseDate').removeAttribute('readonly',0);");
		license_date.clear();
		String formatted_date = CalenderUtil.Calender(inputFormat, outputFormat ,licenseIssue);
		license_date.sendKeys(formatted_date);

		i=1;
		sb = new StringBuilder(zipcode);
		while(i < 3)
		{
			sb.deleteCharAt(sb.length()-1);	
			i++;
		}
		
		zip_code.clear();
		zip_code.sendKeys(sb);
		
	//	i=1;
		sb = new StringBuilder(bonus);
		while(i < 3)
		{
			sb.deleteCharAt(sb.length()-1);	
			i++;
			
		}
		
		bonus_malus.clear();
		bonus_malus.sendKeys(sb);
		
		boolComprehensive = Boolean.parseBoolean(comprehensive);
		boolThirdParty = Boolean.parseBoolean(thirdParty);
		
		if(boolComprehensive == true)
		{
			compreh.click();	
		}
		else
		{
			if(boolThirdParty == true)
			{
				third_party.click();
			}
		}
	
		simulateCTA.click();
		return fee.isDisplayed();
		
		}
			
		
		public boolean validateSimulateCTA()
		
		{
			return simulateCTA.isDisplayed();
		}
		

}
