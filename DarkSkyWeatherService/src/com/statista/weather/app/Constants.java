package com.statista.weather.app;

/**
 * Constants for the whole project
 *
 */
public final class Constants {
	public final static double[] COORDINATE = new double[2];			//to save latitude and longitude of requested location
	public final static String BASE_URL = "https://api.darksky.net";	//basic url of DarkSky.net
	public final static String REQUEST_TYPE = "forecast";				//request type
	final static String KEY = "e03bb3b63cad0286e4d1573353e4fb67";		//key numer for calling DarkSky API
	//excludes to exclude some data blocks from response
	public final static String[] EXCLUDES = new String[] {"currently", "minutely", "hourly", "daily", "alerts", "flags"};	
	public final static String[] UNITS = new String[] {"ca", "uk2", "us", "si"};	//units of weather information
	
	private Constants() {
		// no instance should be created.
	}	
}
