package cap.project.rainyday;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment {
    private RecyclerView recyclerView;
    private CurWeatherAdapter weatherAdapter;
    private List<CurWeatherItem> weatherItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_cur);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        weatherItemList = new ArrayList<>();

        weatherItemList.add(new CurWeatherItem("10:00 AM", "20°C", "10mm", "30%", R.drawable.sunrise, "맑음"));
        weatherItemList.add(new CurWeatherItem("11:00 AM", "22°C", "5mm", "20%", R.drawable.wind, "흐림"));
        // Add more items

        weatherAdapter = new CurWeatherAdapter(getContext(), weatherItemList);
        recyclerView.setAdapter(weatherAdapter);

        return view;
    }
}
