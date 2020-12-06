package ir.artaateam.android.braingame.AdaptersAndHolders;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Controllers.MusicController;
import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.R;

import static ir.artaateam.android.braingame.Enums.GameDifficulty.*;

public class GamesViewHolder extends RecyclerView.ViewHolder {
    private TextView levelTextView;

    public void setTextLevelTextView(String text) {
        this.levelTextView.setText(text);
    }

    public GamesViewHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        levelTextView = itemView.findViewById(R.id.level_text_view);
        itemView.setOnClickListener(v -> onClick(activity));
    }

    private void onClick(Activity activity) {
        GameDifficulty gameDifficulty = NULLGameDifficulty;
        // TODO update if condescend
        if(levelTextView.getText() == instructions.toString()){
            gameDifficulty=instructions;
        }else if (levelTextView.getText() == easy.toString()) {
            gameDifficulty = easy;
        } else if (levelTextView.getText() == normal.toString()) {
            gameDifficulty = normal;
        } else if (levelTextView.getText() == hard.toString()) {
            gameDifficulty = hard;
        }
        //AppMainFragment.removeFragment(); TODO
        MusicController.stopMusic();
        //FragmentController.showGame1GameFragment(activity, gameDifficulty);
    }
}
