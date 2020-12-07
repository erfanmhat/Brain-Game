package ir.artaateam.android.braingame.Fragments;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ir.artaateam.android.braingame.App.Data;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Controllers.MusicController;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.App.MyApplication;

public class ShowScoreFragment extends Fragment {
    public final static int SCORE_ANIMATION_DURATION = 2000;

    int scoreInt;

    private ImageView homeImageView;
    private ImageView replayImageView;
    private TextView scoreTextView;
    private ImageView brainImageView;
    private ImageView settingsImageView;
    private TextView newBestScoreTextView;
    private TextView gemTextView;
    private TextView coinTextView;

    public ShowScoreFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_score_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
        getScoreIntFromBundle();
        rewardToPlayerAndShowReward();
        setScoreTextView();
        setBestScoreTextView();
        scoreAndNewBestScoreAnimation();
        MusicController.startMusic(getActivity(), R.raw.music_show_score, false);
        otherAnimations();
    }

    @Override
    public void onPause() {
        removeFragment();
        MusicController.stopMusic();
        super.onPause();
    }

    private void findViews(View view) {
        scoreTextView = view.findViewById(R.id.scores_text_view);
        homeImageView = view.findViewById(R.id.home_image_view);
        replayImageView = view.findViewById(R.id.replay_image_view);
        brainImageView = view.findViewById(R.id.brain_image_view);
        settingsImageView = view.findViewById(R.id.show_score_settings_image_view);
        newBestScoreTextView = view.findViewById(R.id.new_best_score_text_view);
        gemTextView = view.findViewById(R.id.gem_text_view);
        coinTextView = view.findViewById(R.id.coin_text_view);
    }

    private void configure() {
        setFont();
        homeImageView.setOnClickListener(view -> {
            MusicController.stopMusic();
            removeFragment();
            FragmentController.showAppMainFragment(getActivity());
        });
        replayImageView.setOnClickListener(view -> {
            MusicController.stopMusic();
            removeFragment();
            FragmentController.showGame1GameFragment(getActivity());
        });
        settingsImageView.setOnClickListener(view -> FragmentController.showSettingsFragment(getActivity()));
    }

    private void rewardToPlayerAndShowReward(){
        String gemString=String.valueOf(Data.getGem());
        String coinString=String.valueOf(Data.getCoin());
        int gemPlusInt=scoreInt/10;
        int coinPlusInt=scoreInt;
        gemString+=" + "+gemPlusInt+" ";
        coinString+=" + "+coinPlusInt+" ";
        gemTextView.setText(gemString);
        coinTextView.setText(coinString);

        Data.setGem(Data.getGem()+gemPlusInt);
        Data.setCoin(Data.getCoin()+coinPlusInt);
    }

    private void getScoreIntFromBundle(){
        Bundle bundle;
        bundle = getArguments();
        scoreInt = bundle.getInt("score");
    }

    private void setFont(){
        Typeface font=Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_MAIN_FONT_PATH
        );
        scoreTextView.setTypeface(font);
        newBestScoreTextView.setTypeface(font);
        gemTextView.setTypeface(font);
        coinTextView.setTypeface(font);
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    private void setBestScoreTextView() {
        if (getArguments().getBoolean("isNewBestScore", false)) {
            String recordString=getString(R.string.new_best_score)+" ";
            newBestScoreTextView.setText(recordString);
        }
    }

    private void otherAnimations() {
        ObjectAnimator homeImageViewScaleX = ObjectAnimator.ofFloat(
                homeImageView,
                "scaleX",
                0f, 1f, 2f, 1f
        );
        homeImageViewScaleX.setDuration(1000);

        ObjectAnimator homeImageViewScaleY = ObjectAnimator.ofFloat(
                homeImageView,
                "scaleY",
                0f, 1f, 2f, 1f
        );
        homeImageViewScaleY.setDuration(1000);

        ObjectAnimator replayImageViewScaleX = ObjectAnimator.ofFloat(
                replayImageView,
                "scaleX",
                0f, 1f, 2f, 1f
        );
        replayImageViewScaleX.setDuration(1000);

        ObjectAnimator replayImageViewScaleY = ObjectAnimator.ofFloat(
                replayImageView,
                "scaleY",
                0f, 1f, 2f, 1f
        );
        replayImageViewScaleY.setDuration(1000);

        ObjectAnimator brainImageViewTranslationY = ObjectAnimator.ofFloat(
                brainImageView,
                "TranslationY",
                -500f, 0f
        );
        brainImageViewTranslationY.setDuration(4000);

        homeImageViewScaleX.start();
        homeImageViewScaleY.start();
        replayImageViewScaleX.start();
        replayImageViewScaleY.start();
        brainImageViewTranslationY.start();
    }

    private void setScoreTextView() {
        String scoreString=getString(R.string.speed_final_score, scoreInt)+" ";
        scoreTextView.setText(scoreString);
    }

    private void scoreAndNewBestScoreAnimation() {
        ObjectAnimator scoresX = ObjectAnimator.ofFloat(
                scoreTextView,
                "scaleX",
                0f, 1.2f
        );
        scoresX.setDuration(SCORE_ANIMATION_DURATION);
        scoresX.start();

        ObjectAnimator scoresY = ObjectAnimator.ofFloat(
                scoreTextView,
                "scaleY",
                0f, 1.2f
        );
        scoresY.setDuration(SCORE_ANIMATION_DURATION);
        scoresY.start();

        ObjectAnimator scoresRotateX = ObjectAnimator.ofFloat(
                scoreTextView,
                "RotationX",
                0f, 720f
        );
        scoresRotateX.setDuration(SCORE_ANIMATION_DURATION);
        scoresRotateX.start();

        ObjectAnimator scoresRotate = ObjectAnimator.ofFloat(
                scoreTextView,
                "Rotation",
                0f, 360f
        );
        scoresRotate.setDuration(SCORE_ANIMATION_DURATION);
        scoresRotate.start();
        //========================================================
        ObjectAnimator newBestScoresX = ObjectAnimator.ofFloat(
                newBestScoreTextView,
                "scaleX",
                0f, 1.2f
        );
        newBestScoresX.setDuration(SCORE_ANIMATION_DURATION);
        newBestScoresX.start();

        ObjectAnimator newBestScoresY = ObjectAnimator.ofFloat(
                newBestScoreTextView,
                "scaleY",
                0f, 1.2f
        );
        newBestScoresY.setDuration(SCORE_ANIMATION_DURATION);
        newBestScoresY.start();

        ObjectAnimator newBestScoresRotateX = ObjectAnimator.ofFloat(
                newBestScoreTextView,
                "RotationX",
                0f, 720f
        );
        newBestScoresRotateX.setDuration(SCORE_ANIMATION_DURATION);
        newBestScoresRotateX.start();

        ObjectAnimator newBestScoresRotate = ObjectAnimator.ofFloat(
                newBestScoreTextView,
                "Rotation",
                0f, 360f
        );
        newBestScoresRotate.setDuration(SCORE_ANIMATION_DURATION);
        newBestScoresRotate.start();
    }
}
