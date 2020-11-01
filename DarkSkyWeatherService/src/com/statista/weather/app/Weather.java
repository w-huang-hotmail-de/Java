package com.statista.weather.app;

/**
 * The parent class of all types of weather-class (for example the class CurrentWeather).
 *
 */
public abstract class Weather {
	protected double latitude;
	protected double longitude;
	protected String timezoneID;
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getTimezoneID() {
		return timezoneID;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setTimezoneID(String timezoneID) {
		this.timezoneID = timezoneID;
	}
	
}
