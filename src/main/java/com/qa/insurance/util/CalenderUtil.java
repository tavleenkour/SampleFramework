package com.qa.insurance.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qa.insurance.base.TestBase;

public class CalenderUtil extends TestBase {
	
	static Date date;
	
	// ******* Utility to update format for a date-picker **********
	public static String Calender(String input, String output, String issue_date)
	{
			DateFormat inputFormat = new SimpleDateFormat(input);
			DateFormat outputFormat = new SimpleDateFormat(output);
			
					try {
						date = inputFormat.parse(issue_date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				
			String outputText = outputFormat.format(date);
			
			return outputText;

	}



}
