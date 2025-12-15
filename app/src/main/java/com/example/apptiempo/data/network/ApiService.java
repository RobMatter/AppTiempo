package com.example.apptiempo.data.network;

import com.example.apptiempo.data.model.CurrentWeather;
import com.example.apptiempo.data.model.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interfaz que define los endpoints de la API del tiempo usando Retrofit.
public interface ApiService {

    // Define la llamada para obtener el tiempo actual.
    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(
            @Query("q") String city,        // Nombre de la ciudad
            @Query("appid") String apiKey,  // Clave de la API
            @Query("units") String units,     // Unidades (metrica para Celsius)
            @Query("lang") String lang        // Idioma de la respuesta
    );

    // Define la llamada para obtener el pronóstico del tiempo.
    @GET("forecast")
    Call<ForecastResponse> getForecast(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units,
            @Query("lang") String lang
    );
}
