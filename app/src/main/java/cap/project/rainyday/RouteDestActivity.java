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

    private TextView dateTextView;
    private TextView timeTextView;

    private TextView selectedView;
    private void showDatePickerDialog () {
        // 현재 날짜 가져오기
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // DatePickerDialog 생성
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // 선택한 날짜 TextView에 표시
                        dateTextView.setText("날짜 : "+year + "년 " + (month + 1) + "월 " + dayOfMonth + "일");
                    }
                }, year, month, dayOfMonth);

        // DatePickerDialog 표시
        datePickerDialog.show();
    }

    // TimePickerDialog 표시 메서드
    private void showTimePickerDialog () {
        // 현재 시간 가져오기
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // TimePickerDialog 생성
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 선택한 시간 TextView에 표시
                        timeTextView.setText("시간 : "+hourOfDay + "시 " + minute + "분");
                    }
                }, hour, minute, true); // 마지막 매개변수는 24시간 표시 여부

        // TimePickerDialog 표시
        timePickerDialog.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_dest);
        final String[] name = new String[1];
        final double[] lat = new double[1];
        final double[] lng = new double[1];
        dateTextView = findViewById(R.id.date_dest);
        timeTextView = findViewById(R.id.time_dest);
        selectedView = findViewById(R.id.selected_dest);
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

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Time 입력 위젯에 대한 클릭 이벤트 설정
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }
}
