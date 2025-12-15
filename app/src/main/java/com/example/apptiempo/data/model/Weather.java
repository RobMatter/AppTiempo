package com.example.apptiempo.data.model;

import com.google.gson.annotations.SerializedName;

// Modelo de datos que representa el objeto "weather" en la respuesta de la API.
public class Weather {

    // Condición principal del tiempo (ej. "Claro", "Nublado", "LLuvia").
    @SerializedName("main")
    private String main;

    // Descripción más detallada de la condición del tiempo.
    @SerializedName("description")
    private String description;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
