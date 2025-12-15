package com.example.apptiempo.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Cliente de la API. Se encarga de configurar Retrofit para realizar las llamadas de red.
public class ApiClient {

    // Instancia única de Retrofit (patrón Singleton).
    private static Retrofit retrofit = null;
    // URL base de la API del tiempo.
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    // Devuelve la instancia de Retrofit. Si no existe, la crea.
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Construye la instancia de Retrofit.
            retrofit = new Retrofit.Builder()
                    // Establece la URL base para todas las llamadas.
                    .baseUrl(BASE_URL)
                    // Añade un conversor para transformar las respuestas JSON en objetos Java.
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
