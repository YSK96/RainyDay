package cap.project.rainyday;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ScheduleAddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add);

        TextInputLayout textInputLayout = findViewById(R.id.titleInputLayout);
        TextInputEditText textInput = (TextInputEditText) textInputLayout.getEditText();

        Button addScheduleButton = findViewById(R.id.addScheduleButton);
        addScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = textInput.getText().toString();
                Log.d("aa", title);
                Intent intent = new Intent(ScheduleAddActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}