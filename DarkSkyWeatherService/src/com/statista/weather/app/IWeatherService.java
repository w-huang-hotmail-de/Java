package com.statista.weather.app;

import com.mashape.unirest.http.HttpResponse;

/**
 * Declare the methods to retrieve weather information from DarkSky.net
 */
public interface IWeatherService {
	//read the latitude and longitude of requested location and save them into constant Constants.COORDINATE
	public void readLatitudeAndLongitude();
	
	//call DarkSky.net and get the weather Information (response) back
	public HttpResponse<String> getHttpResponseFromDarkSky();
	
	//create an instance of CurrentWeather, and save the weather information into it.
	public Weather createCurrentWeather(HttpResponse<String> httpResponse);
}
