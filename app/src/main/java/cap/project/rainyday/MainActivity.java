package cap.project.rainyday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    // 하단 바 구성요소
     TextView weatherTextView, homeTextView, myInfoTextView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 툴바 관련
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            // 툴바에서 + 터치시 이동
            Intent intent = new Intent(MainActivity.this, ScheduleAddActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 하단 바 설정
        weatherTextView = findViewById(R.id.weatherTextView);
        homeTextView = findViewById(R.id.homeTextView);
        myInfoTextView = findViewById(R.id.myInfoTextView);

        weatherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new WeatherFragment());
            }
        });

        homeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new HomeFragment());
            }
        });

        myInfoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new MyInfoFragment());
            }
        });

        // 앱 시작 시 기본적으로 표시될 프래그먼트 설정
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    // 프래그먼트 로드 메소드
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}