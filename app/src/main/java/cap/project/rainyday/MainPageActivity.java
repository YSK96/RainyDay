package cap.project.rainyday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cap.project.rainyday.data.model.Schedule;

public class MainPageActivity extends AppCompatActivity {
    private ArrayList<Schedule> scheduleList;
    private ArrayAdapter<Schedule> adapter;
    private ListView listViewSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        scheduleList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scheduleList);
        listViewSchedule = findViewById(R.id.sch_list);
        listViewSchedule.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {

                // HTTP 요청 보내기
                try {
                  //  String url = "http://ec2-34-229-85-193.compute-1.amazonaws.com/schedule";
                    String url = "/192.168.219.153/schedule/10";
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // HTTP 요청 설정
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);


                    // 응답 받기
                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // 응답 데이터 받기
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        // JSON 데이터 파싱 및 리스트뷰에 추가
                        Gson gson = new Gson();
                        Schedule[] schedules = gson.fromJson(response.toString(), Schedule[].class);
                        for (Schedule schedule : schedules) {
                            scheduleList.add(new Schedule(schedule.getScheduleId(),schedule.getTitle()));

                        }

                        // UI 업데이트
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                    else if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST ||
                            responseCode == HttpURLConnection.HTTP_NOT_FOUND){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               //
                            }
                        });
                    }
                    else {
                        Log.d("errs", "불러오기 실패");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        listViewSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainPageActivity.this, RouteActivity.class);
                startActivity(intent);
            }
        });

        Button button_add = findViewById(R.id.addScheduleButton);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, ScheduleAddActivity.class);
                startActivity(intent);
            }
        });
    }

}

