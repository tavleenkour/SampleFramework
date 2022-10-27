package com.insurance.qa.util;

import java.time.Duration;

import com.insurance.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(20);
	public static Duration IMPLICIT_WAIT = Duration.ofSeconds(10);

	    public void switchToFrame() 
	    {
	    	driver.switchTo().defaultContent();
	    }
	
	


}
