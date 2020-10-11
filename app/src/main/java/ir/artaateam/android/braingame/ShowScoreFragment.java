package ir.artaateam.android.braingame;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ShowScoreFragment extends Fragment {
    public final static int SCORE_ANIMATION_DURATION = 2000;

    private ImageView homeImageView;
    private ImageView replayImageView;
    private TextView scoreTextView;
    private ImageView brainImageView;
    private ImageView settingsImageView;

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
        setScoreTextView();
        scoreAnimation();
        MainActivity.startMusic(getActivity(), R.raw.music_show_score, false);
        otherAnimations();
    }

    @Override
    public void onPause() {
        MainActivity.stopMusic();
        super.onPause();
    }

    private void findViews(View view) {
        scoreTextView = view.findViewById(R.id.scores_text_view);
        homeImageView = view.findViewById(R.id.home_image_view);
        replayImageView = view.findViewById(R.id.replay_image_view);
        brainImageView = view.findViewById(R.id.brain_image_view);
        settingsImageView=view.findViewById(R.id.show_score_settings_image_view);
    }

    private void configure() {
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
                MainActivity.showAppMainFragment(getActivity());
            }
        });
        replayImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.stopMusic();
                removeFragment();
                MainActivity.showGame1GameFragment(getActivity());
            }
        });
        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showSettingsFragment(getActivity());
            }
        });
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
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
        Bundle bundle;
        bundle = getArguments();
        int scoreInt = bundle.getInt("score");
        scoreTextView.setText(getString(R.string.speed_final_score, scoreInt));
    }

    private void scoreAnimation() {
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
    }
}
