package cap.project.rainyday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CurWeatherAdapter extends RecyclerView.Adapter<CurWeatherAdapter.ViewHolder> {
    private Context context;
    private List<CurWeatherItem> weatherList;

    public CurWeatherAdapter(Context context, List<CurWeatherItem> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_weather_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurWeatherItem curWeatherItem = weatherList.get(position);
        holder.locationTime.setText(curWeatherItem.getTime());
        holder.locationTemperature.setText(curWeatherItem.getTemperature());
        holder.rainAmount.setText(curWeatherItem.getRainAmount());
        holder.rainPercent.setText(curWeatherItem.getRainPercent());
        holder.weatherIcon.setImageResource(curWeatherItem.getIconResId());
        holder.weatherInfo.setText(curWeatherItem.getWeatherInfo());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationTime, locationTemperature, rainAmount, rainPercent, weatherInfo;
        ImageView weatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTime = itemView.findViewById(R.id.location_time);
            locationTemperature = itemView.findViewById(R.id.bell_sche_title);
            rainAmount = itemView.findViewById(R.id.rain_amount);
            rainPercent = itemView.findViewById(R.id.bell_explain);
            weatherIcon = itemView.findViewById(R.id.weather_icon);
            weatherInfo = itemView.findViewById(R.id.bell_time);
        }
    }
}
