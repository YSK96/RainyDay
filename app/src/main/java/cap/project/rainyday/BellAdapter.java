package cap.project.rainyday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BellAdapter extends RecyclerView.Adapter<BellAdapter.BellViewHolder> {
    private List<BellItem> BellList;

    public BellAdapter(List<BellItem> BellList) {
        this.BellList = BellList;
    }

    @NonNull
    @Override
    public BellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_bell_list, parent, false);
        return new BellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BellViewHolder holder, int position) {
        BellItem currentItem = BellList.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return BellList.size();
    }

    class BellViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView contentTextView;
        private TextView timeTextView;
        private ImageView iconImageView;

        public BellViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.bell_sche_title);
            contentTextView = itemView.findViewById(R.id.bell_explain);
            timeTextView = itemView.findViewById(R.id.bell_time);
            iconImageView = itemView.findViewById(R.id.weather_icon);
        }

        public void bind(BellItem item) {
            titleTextView.setText(item.getTitle());
            contentTextView.setText(item.getContent());
            timeTextView.setText(item.getTime());
            // Set icon if necessary, e.g., iconImageView.setImageResource(R.drawable.ic_bell);
        }
    }
}
