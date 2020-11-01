package com.statista.weather.app;

import com.mashape.unirest.http.HttpResponse;

public class App {	
    /**
     * Create a simple console application which sources the DarkSky API 
     * to retrieve the current weather information for the given location on the planet.
     *
     * @param args
     */
    public static void main(String[] args) {
    	System.out.println("*** Weather information for the requested location ***\n");
    	
    	//create a instance of DarkSkyWeatherService that offers methods to retrieve weather information from DarkSky 
    	DarkSkyWeatherService service = new DarkSkyWeatherService();	
    	
    	//read the latitude and longitude of requested location and save them into constant Constants.COORDINATE
    	service.readLatitudeAndLongitude();
    	
    	//call DarkSky.net and get the weather Information (response) back
    	HttpResponse<String> httpResponse = service.getHttpResponseFromDarkSky();
    	
    	//create an instance of CurrentWeather, and save the weather information into it.
    	Weather currentWeather = service.createCurrentWeather(httpResponse);
    	
    	//show the weather information on consoles
    	if (currentWeather != null)
    		System.out.println("\n" + currentWeather);
    }

}
