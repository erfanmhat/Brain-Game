package ir.artaateam.android.braingame.AdaptersAndHolders;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.artaateam.android.braingame.Forms.Game;
import ir.artaateam.android.braingame.R;

public class GamesAdapter extends RecyclerView.Adapter<GamesViewHolder> {
    private List<Game> items;
    private Activity activity;

    public GamesAdapter(List<Game> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.game_template,
                parent,
                false
        );
        return new GamesViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        holder.setTextLevelTextView(items.get(position).getGameLevel());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
