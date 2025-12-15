package com.example.apptiempo.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptiempo.R;
import com.example.apptiempo.data.model.ForecastItem;
import com.example.apptiempo.ui.adapter.ForecastAdapter;

import java.util.ArrayList;
import java.util.List;

// Actividad principal de la aplicación, muestra el tiempo actual y el pronóstico.
public class MainActivity extends AppCompatActivity {

    // ViewModel para la lógica de la UI
    private MainViewModel viewModel;
    // Vistas para mostrar los datos del tiempo
    private TextView tvTemp, tvCondition;
    private ImageView ivWeatherIcon;
    private ProgressBar progress;
    private EditText etCity;
    // RecyclerView y su adaptador para el pronóstico
    private RecyclerView rvForecast;
    private ForecastAdapter forecastAdapter;
    // Lista para almacenar los datos del pronóstico
    private List<ForecastItem> forecastList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita el modo Edge-to-Edge para una UI inmersiva
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Ajusta el padding para evitar que la UI se solape con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de las vistas
        etCity = findViewById(R.id.etCity);
        tvTemp = findViewById(R.id.tvTemp);
        tvCondition = findViewById(R.id.tvCondition);
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon);
        progress = findViewById(R.id.progressBar);
        rvForecast = findViewById(R.id.rvForecast);

        // Inicialización del ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Configuración del RecyclerView
        setupRecyclerView();

        // Configuración del listener para el botón de búsqueda
        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            String city = etCity.getText().toString().trim();
            if (!city.isEmpty()) {
                loadWeather(city);
            }
        });
    }

    // Configura el RecyclerView con su LayoutManager y Adaptador.
    private void setupRecyclerView() {
        forecastAdapter = new ForecastAdapter(forecastList);
        rvForecast.setLayoutManager(new LinearLayoutManager(this));
        rvForecast.setAdapter(forecastAdapter);
    }

    // Carga los datos del tiempo (actual y pronóstico) para la ciudad especificada.
    private void loadWeather(String city) {
        // Muestra el ProgressBar mientras se cargan los datos
        progress.setVisibility(View.VISIBLE);

        // Observa los cambios en el LiveData del tiempo actual
        viewModel.getWeather(city).observe(this, weather -> {
            // Oculta el ProgressBar una vez que se reciben los datos
            progress.setVisibility(View.GONE);

            if (weather == null) {
                // Si no se encuentran datos, muestra un mensaje de error y oculta el icono
                tvTemp.setText("Ciudad no encontrada");
                tvCondition.setText("");
                ivWeatherIcon.setVisibility(View.GONE);
            } else {
                // Si se encuentran datos, actualiza la UI
                ivWeatherIcon.setVisibility(View.VISIBLE);
                tvTemp.setText((int) weather.getMain().getTemp() + "°C");
                if (weather.getWeather() != null && !weather.getWeather().isEmpty()) {
                    String condition = weather.getWeather().get(0).getMain();
                    tvCondition.setText(condition);
                    ivWeatherIcon.setImageResource(getWeatherIcon(condition));
                }
            }
        });

        // Observa los cambios en el LiveData del pronóstico
        viewModel.getForecast(city).observe(this, forecast -> {
            if (forecast != null && forecast.getList() != null) {
                // Limpia la lista actual y añade los nuevos datos del pronóstico
                forecastList.clear();
                forecastList.addAll(forecast.getList());
                // Notifica al adaptador que los datos han cambiado para que actualice la vista
                forecastAdapter.notifyDataSetChanged();
            }
        });
    }

    // Devuelve el recurso de icono correcto basado en la condición del tiempo.
    @DrawableRes
    private int getWeatherIcon(String condition) {
        if (condition == null) return R.drawable.ic_cloudy; // Icono por defecto
        switch (condition.toLowerCase()) {
            case "clear":
                return R.drawable.ic_sunny;
            case "clouds":
                return R.drawable.ic_cloudy;
            case "rain":
            case "drizzle":
            case "thunderstorm":
                return R.drawable.ic_rainy;
            default:
                return R.drawable.ic_cloudy;
        }
    }
}
