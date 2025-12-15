package com.example.apptiempo.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptiempo.data.model.CurrentWeather;
import com.example.apptiempo.data.model.ForecastResponse;
import com.example.apptiempo.data.repository.WeatherRepository;

// ViewModel para la MainActivity. Se encarga de la lógica de la UI y de la comunicación con el repositorio.
public class MainViewModel extends ViewModel {

    // Instancia del repositorio para obtener los datos del tiempo.
    private WeatherRepository repository = new WeatherRepository();

    // Obtiene los datos del tiempo actual para una ciudad específica.
    // Devuelve un LiveData que la UI puede observar para recibir actualizaciones.
    public LiveData<CurrentWeather> getWeather(String city) {
        return repository.getCurrentWeather(city);
    }

    // Obtiene el pronóstico del tiempo para una ciudad específica.
    // Devuelve un LiveData que la UI puede observar para recibir actualizaciones.
    public LiveData<ForecastResponse> getForecast(String city) {
        return repository.getForecast(city);
    }
}
