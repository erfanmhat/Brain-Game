package ir.artaateam.android.braingame;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ir.artaateam.android.braingame.game1.Game1AnimationValues;

public class ShowScoreFragment extends Fragment {
    private ImageView homeImageView;
    private ImageView replayImageView;
    private TextView scoreTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_score_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
        setScoreTextView();
        scoreAnimation();
        MainActivity.startMusic(getActivity(),R.raw.music_show_score,false);
    }

    @Override
    public void onPause() {
        MainActivity.stopMusic();
        super.onPause();
    }

    private void findViews(View view){
        scoreTextView=view.findViewById(R.id.scores_text_view);
        homeImageView=view.findViewById(R.id.home_image_view);
        replayImageView=view.findViewById(R.id.replay_image_view);
    }

    private void configure(){
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showAppMainFragment(getActivity());
            }
        });
        replayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showGame1GameFragment(getActivity());
            }
        });
    }

    private void setScoreTextView(){
        Bundle bundle;
        bundle=getArguments();
        int scoreInt= bundle.getInt("score");
        scoreTextView.setText(getString(R.string.speed_final_score,scoreInt));
    }

    private void scoreAnimation(){
        ObjectAnimator scoresX = ObjectAnimator.ofFloat(
                scoreTextView,
                "scaleX",
                0f, 1.2f
        );
        scoresX.setDuration(Game1AnimationValues.GAME1_SCORE_ANIMATION_DURATION);
        scoresX.start();

        ObjectAnimator scoresY = ObjectAnimator.ofFloat(
                scoreTextView,
                "scaleY",
                0f, 1.2f
        );
        scoresY.setDuration(Game1AnimationValues.GAME1_SCORE_ANIMATION_DURATION);
        scoresY.start();

        ObjectAnimator scoresRotateX = ObjectAnimator.ofFloat(
                scoreTextView,
                "RotationX",
                0f, 720f
        );
        scoresRotateX.setDuration(Game1AnimationValues.GAME1_SCORE_ANIMATION_DURATION);
        scoresRotateX.start();

        ObjectAnimator scoresRotate = ObjectAnimator.ofFloat(
                scoreTextView,
                "Rotation",
                0f, 360f
        );
        scoresRotate.setDuration(Game1AnimationValues.GAME1_SCORE_ANIMATION_DURATION);
        scoresRotate.start();
    }
}
