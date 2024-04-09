package cap.project.rainyday;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RouteDestActivity extends AppCompatActivity {

    private TextView selectedView;

    private long scheduleId;

    private int departYear;
    private int departMonth;
    private int departDay;
    private int departHour;
    private int departMinute;

    private String departName;
    private double departLat;
    private double departLng;

    private String departAddress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_dest);
        final String[] name = new String[1];
        final double[] lat = new double[1];
        final double[] lng = new double[1];
        selectedView = findViewById(R.id.selected_dest);
        Intent intent = getIntent();
        scheduleId = intent.getLongExtra("scheduleId", 0);
        departYear = intent.getIntExtra("departYear", 0);
        departMonth = intent.getIntExtra("departMonth", 0);
        departDay = intent.getIntExtra("departDay", 0);
        departHour = intent.getIntExtra("departHour", 0);
        departMinute = intent.getIntExtra("departMinute", 0);
        departName = intent.getStringExtra("departName");
        departLat = intent.getDoubleExtra("departLat",0);
        departLng = intent.getDoubleExtra("departLng", 0);
        departAddress = intent.getStringExtra("departAddress");

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyBI_xKrXy81n7ELWopYZi15QMKJ0rQrL6Q");
        }
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_dest);

        List<Place.Field> fields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME);
        autocompleteFragment.setPlaceFields(fields);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull com.google.android.gms.common.api.Status status) {
                // 오류 처리
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {
                if (place.getLatLng() != null) {
                    name[0] = place.getName();
                    lat[0] = place.getLatLng().latitude;
                    lng[0] = place.getLatLng().longitude;
                    selectedView.setText("선택된 장소 : "+name[0]);
                }
            }
        });

        Button button = findViewById(R.id.button_enroll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RouteDestActivity.this, RouteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }
}
