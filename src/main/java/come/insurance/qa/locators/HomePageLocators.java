package come.insurance.qa.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePageLocators {
	private WebDriver driver;
	
	public HomePageLocators(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	@FindBy(xpath="//*[@id=\"simulateButton\"]")
	//	@CacheLookup
	public WebElement simulateCTA;
		
		@FindBy(xpath="//*[@id=\"carBrand\"]")
		public WebElement c_brand;
		
		@FindBy(xpath="//*[@id=\"carModel\"]")
		public WebElement c_model;
		
		@FindBy(xpath="//*[@id=\"fuelType\"]")
		public WebElement fuel_type;
		
		@FindBy(xpath="//*[@id=\"isNewCar\"]")
		public WebElement is_new;
		
		@FindBy(xpath="//*[@id=\"firstUseYear\"]")
		public WebElement first_use;
		
		@FindBy(xpath="//*[@id=\"name\"]")
		public WebElement user_name;
		
		@FindBy(xpath="//*[@id=\"licenseDate\"]")
		public WebElement license_date;
		
		@FindBy(xpath="//*[@id=\"zipCode\"]")
		public WebElement zip_code;
		
		@FindBy(xpath="//*[@id=\"bonusMalus\"]")
		public WebElement bonus_malus;
		
		@FindBy(xpath="//*[@id=\"comprehensive\"]")
		public WebElement compreh;
		
		@FindBy(xpath="//*[@id=\"thirdParty\"]")
		public WebElement third_party;
		
		@FindBy(xpath="//*[@id=\"fee\"]")
		public WebElement fee;
		



}
