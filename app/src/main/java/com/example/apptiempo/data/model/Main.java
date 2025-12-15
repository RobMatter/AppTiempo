package com.example.apptiempo.data.model;

import com.google.gson.annotations.SerializedName;

// Modelo de datos que representa el objeto "main" en la respuesta de la API.
public class Main {

    // Temperatura actual.
    @SerializedName("temp")
    private double temp;

    public double getTemp() {
        return temp;
    }
}
