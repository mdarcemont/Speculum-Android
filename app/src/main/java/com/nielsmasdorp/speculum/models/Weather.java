package com.nielsmasdorp.speculum.models;

import com.nielsmasdorp.speculum.models.forecast.DayForecast;

import java.util.Date;
import java.util.List;

/**
 * @author Niels Masdorp (NielsMasdorp)
 */
public class Weather {

    private int iconId;
    private String temperature;
    private String summary;
    private String lastUpdated;
    private List<ForecastDayWeather> forecast;

    public static class Builder {

        private int iconId;
        private String temperature;
        private String summary;
        private String lastUpdated;
        private List<ForecastDayWeather> forecast;

        public Builder iconId(int iconId) {
            this.iconId = iconId;
            return this;
        }

        public Builder temperature(String temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder lastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder forecast(List<ForecastDayWeather> forecast) {
            this.forecast = forecast;
            return this;
        }

        public Weather build() {

            return new Weather(this);
        }
    }

    private Weather(Builder builder) {

        this.iconId = builder.iconId;
        this.lastUpdated = builder.lastUpdated;
        this.temperature = builder.temperature;
        this.summary = builder.summary;
        this.lastUpdated = builder.lastUpdated;
        this.forecast = builder.forecast;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSummary() {
        return summary;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public List<ForecastDayWeather> getForecast() {
        return forecast;
    }
}

