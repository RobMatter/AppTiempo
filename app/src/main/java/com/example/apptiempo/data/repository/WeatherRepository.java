package com.example.apptiempo.data.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.apptiempo.BuildConfig;
import com.example.apptiempo.data.model.CurrentWeather;
import com.example.apptiempo.data.model.ForecastResponse;
import com.example.apptiempo.data.network.ApiClient;
import com.example.apptiempo.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Repositorio para manejar los datos del tiempo. Se encarga de obtener los datos de la red.
public class WeatherRepository {

    // Instancia del servicio de la API, creada a través del ApiClient.
    private ApiService apiService = ApiClient.getClient().create(ApiService.class);
    // Clave de la API para acceder al servicio del tiempo. Se obtiene desde BuildConfig.
    private String apiKey = BuildConfig.WEATHER_API_KEY;

    // Obtiene el tiempo actual para una ciudad. Devuelve un MutableLiveData.
    public MutableLiveData<CurrentWeather> getCurrentWeather(String city) {
        // Crea un MutableLiveData para almacenar los datos del tiempo.
        MutableLiveData<CurrentWeather> data = new MutableLiveData<>();

        // Llama al metodo de la API de forma asíncrona.
        apiService.getCurrentWeather(city, apiKey, "metric", "es")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        // Si la respuesta es exitosa, actualiza el LiveData con los datos recibidos.
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        } else {
                            // Si hay un error, actualiza el LiveData con null y loguea el error.
                            Log.e("WeatherRepository", "Error en la respuesta: " + response.code());
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        // Si la llamada falla, actualiza el LiveData con null y loguea el error.
                        Log.e("WeatherRepository", "Fallo en la llamada a la API", t);
                        data.setValue(null);
                    }
                });

        // Devuelve el LiveData. La UI observará este objeto para recibir los datos.
        return data;
    }

    // Obtiene el pronóstico del tiempo para una ciudad. Funciona de manera similar a getCurrentWeather.
    public MutableLiveData<ForecastResponse> getForecast(String city) {
        MutableLiveData<ForecastResponse> data = new MutableLiveData<>();

        apiService.getForecast(city, apiKey, "metric", "es")
                .enqueue(new Callback<ForecastResponse>() {
                    @Override
                    public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        } else {
                            Log.e("WeatherRepository", "Error en la respuesta del pronóstico: " + response.code());
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponse> call, Throwable t) {
                        // Si la llamada falla, actualiza el LiveData con null y loguea el error.
                        Log.e("WeatherRepository", "Fallo en la llamada a la API de pronóstico", t);
                        data.setValue(null);
                    }
                });

        return data;
    }
}
