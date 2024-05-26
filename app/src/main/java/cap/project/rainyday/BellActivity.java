package cap.project.rainyday;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BellActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BellAdapter adapter;
    private List<BellItem> BellList;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bell);

        recyclerView = findViewById(R.id.recyclerView_bell);
        emptyView = findViewById(R.id.empty_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BellList = new ArrayList<>();
        //샘플 데이터
        //BellList.add(new BellItem("제목 1", "내용 1", "10:00 AM"));
        //BellList.add(new BellItem("제목 2", "내용 2", "11:00 AM"));

        adapter = new BellAdapter(BellList);
        recyclerView.setAdapter(adapter);

        checkEmptyView();
    }

    private void checkEmptyView() {
        if (BellList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}

