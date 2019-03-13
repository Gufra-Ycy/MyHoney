package com.gufra.model;

/*
* 天气实体
* */
public class WeatherInfo {
    private String city;
    private String temperature;
    private String state;

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "{city="+city+"tempertature="+temperature+"state="+state+"}";
    }
}
