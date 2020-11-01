package com.statista.weather.app;

import java.math.BigDecimal;
import ch.rasc.darksky.model.DsIcon;
import ch.rasc.darksky.model.DsPrecipType;

/**
 * The class that saves the current weather information 
 * and has member variables corresponding to units of current weather information from DarkSky.net
 *
 */
public class CurrentWeather extends Weather{
	private long unixTime;
	private String summary; 
	private DsIcon icon;					
	private	DsPrecipType precipType;
	private BigDecimal precipIntensity;
	private BigDecimal precipProbability;
	private BigDecimal temperature;
	private BigDecimal apparentTemperature;
	private BigDecimal dewPoint;
	private BigDecimal humidty;
	private BigDecimal pressure;
	private	BigDecimal windSpeed;
	private BigDecimal windBearing;
	private BigDecimal cloudCover;
	private BigDecimal visibility;
	private BigDecimal ozone;

	public long getUnixTime() {
		return unixTime;
	}

	public String getSummary() {
		return summary;
	}

	public DsIcon getIcon() {
		return icon;
	}

	public DsPrecipType getPrecipType() {
		return precipType;
	}

	public BigDecimal getPrecipIntensity() {
		return precipIntensity;
	}

	public BigDecimal getPrecipProbability() {
		return precipProbability;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public BigDecimal getApparentTemperature() {
		return apparentTemperature;
	}

	public BigDecimal getDewPoint() {
		return dewPoint;
	}

	public BigDecimal getHumidty() {
		return humidty;
	}

	public BigDecimal getPressure() {
		return pressure;
	}

	public BigDecimal getWindSpeed() {
		return windSpeed;
	}

	public BigDecimal getWindBearing() {
		return windBearing;
	}

	public BigDecimal getCloudCover() {
		return cloudCover;
	}

	public BigDecimal getVisibility() {
		return visibility;
	}

	public BigDecimal getOzone() {
		return ozone;
	}

	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setIcon(DsIcon icon) {
		this.icon = icon;
	}

	public void setPrecipType(DsPrecipType precipType) {
		this.precipType = precipType;
	}

	public void setPrecipIntensity(BigDecimal precipIntensity) {
		this.precipIntensity = precipIntensity;
	}

	public void setPrecipProbability(BigDecimal precipProbability) {
		this.precipProbability = precipProbability;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public void setApparentTemperature(BigDecimal apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}

	public void setDewPoint(BigDecimal dewPoint) {
		this.dewPoint = dewPoint;
	}

	public void setHumidty(BigDecimal humidty) {
		this.humidty = humidty;
	}

	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	public void setWindSpeed(BigDecimal windSpeed) {
		this.windSpeed = windSpeed;
	}

	public void setWindBearing(BigDecimal windBearing) {
		this.windBearing = windBearing;
	}

	public void setCloudCover(BigDecimal cloudCover) {
		this.cloudCover = cloudCover;
	}

	public void setVisibility(BigDecimal visibility) {
		this.visibility = visibility;
	}

	public void setOzone(BigDecimal ozone) {
		this.ozone = ozone;
	}

	@Override
	public String toString() {
		//convert unixtime to formated date-time string
		String formatedDataTime = Utils.convertUnixTimeToFormatedDateTimeString(unixTime, timezoneID); 
		//figure out whether it is raining
		String rainfall = precipIntensity.doubleValue() == 0 ? "Dry\t" : "Raining\t";
		
		return "*** Current Weather ***\n" 
				+ "[latitude: " + latitude + ", longitude: " + longitude + ", " + formatedDataTime + " " + timezoneID + "]\n"
			 	+ "summary: " + summary + "\n"
			 	+ rainfall + "    temperature: " + temperature + " °„C    Wind: " + windSpeed + " m/sec    " + "Visibility: " + visibility + " m";
	}
	
}
