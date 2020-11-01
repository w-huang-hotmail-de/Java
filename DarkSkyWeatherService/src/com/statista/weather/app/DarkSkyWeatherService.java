package com.statista.weather.app;

import java.io.IOException;
import java.util.Scanner;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ch.rasc.darksky.json.JacksonJsonConverter;
import ch.rasc.darksky.model.DsDataPoint;
import ch.rasc.darksky.model.DsResponse;
import static com.statista.weather.app.Constants.*;

/**
 * Implement the methods to retrieve weather information from DarkSky.net
 * 
 */
public class DarkSkyWeatherService implements IWeatherService 
{
	/**
	 * Read the latitude and longitude of requested location and save them into constant Constants.COORDINATE
	 */
	@Override
	public void readLatitudeAndLongitude() 
	{
		Scanner scanner = new Scanner(System.in);	//create a scanner that gets value from console
		
		//get a valid latitude 
    	System.out.print("Please give the Latitude of the requested location: ");
    	String input = scanner.nextLine();		//get the inputed string from console
    	//figure out whether the inputed string is a valid number and in the range. A suitable number must be returned. 
    	double latitude = Utils.isNumericAndIsInRange(scanner, input, -90, 90);	  
    	
    	//get a valid longitude
    	System.out.print("\nPlease give the Longitude of the requested location: ");
    	input = scanner.nextLine();		//get the inputed string from console
    	//figure out whether the inputed string is a valid number and in the range. A suitable number must be returned. 
    	double longitude = Utils.isNumericAndIsInRange(scanner, input, -180, 180);
    	
    	scanner.close();	//close the scanner
    	COORDINATE[0] = latitude; 	//pack the correct latitude and longitude in constant Constants.COORDINATE
    	COORDINATE[1] = longitude; 
	}
	
	
	/**
	 * Call DarkSky.net and get the weather Information (response) back
	 * @return an Intance of HttpResponse that contains the weather information from DarkSky
	 */
	@Override
	public HttpResponse<String> getHttpResponseFromDarkSky()  
	{
		//build url to call DarkSky.net
		String url = BASE_URL + "/" + REQUEST_TYPE + "/" + KEY + "/" + COORDINATE[0] + "," + COORDINATE[1];	
		//build exclude-queryString to exclude some data blocks from response
		String excludeStr = "";		
		for(int i = 1; i < EXCLUDES.length; i++)
			excludeStr.concat(EXCLUDES[i]+",");
		
		HttpResponse<String> httpResponse = null;	//declare a variable of HttpResponse<String>
		try {	//call DarkSky.net and get a response containing weather information back
			httpResponse = Unirest.get(url).header("accept", "application/json")
										   .queryString("exclude", excludeStr).queryString("units", UNITS[3])
										   .asString();
		} catch (UnirestException e) {
			System.out.println("\n!!! Failed to obtain data from DarkSky, please check url and try again later.");
		}

		return httpResponse;	//Null will be returned if an exception was thrown
	}

	
	/**
	 * Craete an instance of CurrentWeather, and save the weather information into it.
	 * @param httpResponse the response returned from DarkSky.net
	 * @return an instance of Weather saving the weather information
	 */
	@Override
	public Weather createCurrentWeather(HttpResponse<String> httpResponse) 
	{
		if(httpResponse == null)	//return null if there was no response retured from DarkSky.net 
			return null;
		
		JacksonJsonConverter converter = new JacksonJsonConverter();	//create a convert 
		DsResponse dsResponse = null;	//declare a value of DsResponse
		try{	
			dsResponse = converter.deserialize(httpResponse.getBody());	//convert response of HttpResponse to type DsResponse
		}catch(IOException e) {
			System.out.println("\n!!! Failed to parse weather information, please try again later.");
			return null;
		}
		DsDataPoint dsDataPoint = dsResponse.currently();  	  //get the instance of DsdataPoint only containing current weather condition
		CurrentWeather currentWeather = new CurrentWeather(); //create instance of CurrentWeather to save weather information
		
		//fill the weather information into the instance currentWeather
		currentWeather.setLatitude(COORDINATE[0]);				//set latitude of the requested location
		currentWeather.setLongitude(COORDINATE[1]);				//set longitude of the requested location
		currentWeather.setTimezoneID(dsResponse.timezone());	//set timezoneID of the requested location
		currentWeather.setUnixTime(dsDataPoint.time());			//set unixTime
		currentWeather.setSummary(dsDataPoint.summary());		//set summary
		currentWeather.setIcon(dsDataPoint.icon());				//set icon
		currentWeather.setPrecipType(dsDataPoint.precipType());	//set precipType, null if precipIntensity is zero
		currentWeather.setPrecipIntensity(dsDataPoint.precipIntensity());			//set precip intensity 
		currentWeather.setPrecipProbability(dsDataPoint.precipProbability());		//set precip probability
		currentWeather.setTemperature(dsDataPoint.temperature());					//set temperature
		currentWeather.setApparentTemperature(dsDataPoint.apparentTemperature());	//set apparent temperature
		currentWeather.setDewPoint(dsDataPoint.dewPoint());			//set dew point						
		currentWeather.setHumidty(dsDataPoint.humidity());			//set humidity
		currentWeather.setPressure(dsDataPoint.pressure());			//set pressure
		currentWeather.setWindSpeed(dsDataPoint.windSpeed());		//set windspeed
		currentWeather.setWindBearing(dsDataPoint.windBearing());	//set windbearing
		currentWeather.setCloudCover(dsDataPoint.cloudCover());		//set cloud cover
		currentWeather.setVisibility(dsDataPoint.visibility());		//set visibility
		currentWeather.setOzone(dsDataPoint.ozone());				//set ozone
		
		return currentWeather;
	}

}
