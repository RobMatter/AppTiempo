package com.example.apptiempo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptiempo.R;
import com.example.apptiempo.data.model.ForecastItem;
import java.util.List;

// Adaptador para el RecyclerView que muestra la lista del pronóstico del tiempo.
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    // Lista de elementos del pronóstico que se mostrarán.
    private List<ForecastItem> forecastList;

    // Constructor del adaptador.
    public ForecastAdapter(List<ForecastItem> forecastList) {
        this.forecastList = forecastList;
    }

    // Crea una nueva vista para un elemento de la lista (llamado por el LayoutManager).
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout del item del pronóstico.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    // Vincula los datos de un elemento de la lista con su vista (llamado por el LayoutManager).
    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        // Obtiene el elemento del pronóstico en la posición actual.
        ForecastItem item = forecastList.get(position);

        // Asigna los datos a las vistas del ViewHolder.
        holder.tvDateTime.setText(item.getDateTime());
        holder.tvTemp.setText((int) item.getMain().getTemp() + "°C");

        if (item.getWeather() != null && !item.getWeather().isEmpty()) {
            String condition = item.getWeather().get(0).getMain();
            holder.ivForecastIcon.setImageResource(getWeatherIcon(condition));
        }
    }

    // Devuelve el número total de elementos en la lista.
    @Override
    public int getItemCount() {
        return forecastList.size();
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

    // ViewHolder que contiene las vistas para cada elemento de la lista del pronóstico.
    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        // Vistas para el icono, la fecha/hora y la temperatura.
        ImageView ivForecastIcon;
        TextView tvDateTime, tvTemp;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa las vistas a partir del layout del item.
            ivForecastIcon = itemView.findViewById(R.id.ivForecastIcon);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvTemp = itemView.findViewById(R.id.tvTempItem);
        }
    }
}
