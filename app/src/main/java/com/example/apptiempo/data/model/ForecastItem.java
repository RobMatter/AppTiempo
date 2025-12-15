package com.example.apptiempo.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Modelo de datos para un elemento individual del pronóstico. Representa una predicción en un momento concreto.
public class ForecastItem {

    // Objeto que contiene los datos principales como la temperatura.
    @SerializedName("main")
    private Main main;

    // Lista que contiene la información sobre la condición del tiempo.
    @SerializedName("weather")
    private List<Weather> weather;

    // Fecha y hora de la predicción en formato de texto.
    @SerializedName("dt_txt")
    private String dateTime;

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getDateTime() {
        return dateTime;
    }
}
