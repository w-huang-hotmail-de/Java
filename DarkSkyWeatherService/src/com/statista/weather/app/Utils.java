package com.statista.weather.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Some common usefull methods for the whole project
 *
 */
public class Utils {
	
	/**
	 * Figure out whether the inputed string is a valid number and in the certain range, 
	 * if not, get an new input again until the input is valid.
	 * @param scanner the scanner that produces values scanned from console
	 * @param input the string that will be checked
	 * @param min the minimum of the range 
	 * @param max the maximum of the range
	 * @return the double value represented by the inputed string
	 */
	public static double isNumericAndIsInRange(Scanner scanner, String input, double min, double max) 
	{	
		//if the inputed string is not a valid numer or beyond the range.
		while(!isNumeric(input) || !isInRange(Double.parseDouble(input.trim()), min, max)) 
		{	
    		if(!isNumeric(input)) {		//the inputed string is not a valid number
    			System.out.print("!!! It must be a number, please try again: ");
    			input = scanner.nextLine();		//get the newly inputed string
    		}else {		//the inputed string is a number, but beyond the range
    			System.out.printf("!!! It must be in the range %.0f to %.0f (inclusive), please try again: ", min, max);
    			input = scanner.nextLine();		//get the newly inputed string
    		}
    	}
		return Double.parseDouble(input);
	}
	

	/**
	 * Convert unix time in seconds to formated date-time string
	 * @param unixTime unixtime in seconds
	 * @param timezoneID Id of timezone, for example Europe/Berlin
	 * @return formated date-time string
	 */
	public static String convertUnixTimeToFormatedDateTimeString(long unixTime, String timezoneID) 
	{
		Date date = new Date(unixTime*1000L);	//convert seconds to milliseconds
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//set the format of date
		dateFormat.setTimeZone(java.util.TimeZone.getTimeZone(timezoneID));			//set the time zone
		String formatedDate = dateFormat.format(date);	//convert time value to formated date-time String
		
		return formatedDate;
	}
	
	
	/**
	 * Figure out whether a string is a valid number
	 * @param input the string to be checked
	 * @return true if the string is a valid number, otherwise false
	 */
	public static boolean isNumeric(String input) 
	{
		if(input == null)
			return false;
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");	//compile the given regular expression into a pattern
		
		return pattern.matcher(input.trim()).matches();			//match the given input string (spacce removed) against the pattern
	}
	
	
	/**
	 * Figure out whether a number is in the range of [min, max]
	 * @param input the number to be checked
	 * @param min the minimum of range
	 * @param max the maximum of range
	 * @return true if the number is in range (inclusiv), otherwise false
	 */
	public static boolean isInRange(double input, double min, double max) 
	{
		if(input < min || input > max)
			return false;
		return true;
	}
	
}





