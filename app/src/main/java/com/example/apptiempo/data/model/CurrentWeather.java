package com.example.apptiempo.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Modelo de datos para el tiempo actual. Representa la respuesta JSON de la API.
public class CurrentWeather {

    // Objeto que contiene los datos principales como la temperatura.
    @SerializedName("main")
    private Main main;

    // Lista que contiene la información sobre la condición del tiempo (soleado, nublado, etc.).
    @SerializedName("weather")
    private List<Weather> weather;

    // Nombre de la ciudad.
    @SerializedName("name")
    private String name;

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }
}
