package com.aqi.aqi_service.model;

public class AqiResponse {
    private String city;
    private int aqi;
    private int pm25;
    private int pm10;
    private String dominentPollutant;

    // Getters and Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public int getAqi() { return aqi; }
    public void setAqi(int aqi) { this.aqi = aqi; }
    public int getPm25() { return pm25; }
    public void setPm25(int pm25) { this.pm25 = pm25; }
    public int getPm10() { return pm10; }
    public void setPm10(int pm10) { this.pm10 = pm10; }
    public String getDominentPollutant() { return dominentPollutant; }
    public void setDominentPollutant(String dominentPollutant) { this.dominentPollutant = dominentPollutant; }
}