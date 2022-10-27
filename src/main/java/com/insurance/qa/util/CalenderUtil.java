package com.insurance.qa.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.insurance.qa.base.TestBase;

public class CalenderUtil extends TestBase {
	
	static Date date;
	
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
