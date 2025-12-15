package com.example.apptiempo.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Modelo de datos para la respuesta del pronóstico. Contiene una lista de predicciones.
public class ForecastResponse {

    // Lista de predicciones del tiempo para diferentes momentos.
    @SerializedName("list")
    private List<ForecastItem> list;

    public List<ForecastItem> getList() {
        return list;
    }
}
