package ir.artaateam.android.braingame.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import ir.artaateam.android.braingame.App.App;
import ir.artaateam.android.braingame.App.Data;
import ir.artaateam.android.braingame.Controllers.EvaluateImageController;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Controllers.GameMusicController;
import ir.artaateam.android.braingame.Controllers.MusicController;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.Controllers.ShapeAndColorController;
import ir.artaateam.android.braingame.App.MyApplication;
import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.Enums.GameInputButton;
import ir.artaateam.android.braingame.ShapeAndColorAnimationValues;
import ir.artaateam.android.braingame.SmartCountDownTimer;

import static ir.artaateam.android.braingame.Enums.GameDifficulty.*;
import static ir.artaateam.android.braingame.Enums.GameInputButton.*;

//TODO
// refactor game values to another class
// add new items one by one to game
// 1> dublicate score for x S
// 2> extra lives x
// 3> freaze time for x S
// 4> skip to next shape and color
// 5>remove some buttons randomly item  for x S
// add dataBase for scores saving
// game background updating
// close and end game
// music switch to smartCountDownTimer
public class GameShapeAndColorFragment extends Fragment {
    private int GAME1_SLIDE_ANIMATION_DURATION = ShapeAndColorAnimationValues.GAME1_SLIDE_ANIMATION_DURATION_MAX;
    private int GAME1_DECISION_RESULT_ANIMATION_DURATION = ShapeAndColorAnimationValues.GAME1_DECISION_RESULT_ANIMATION_DURATION_MAX;

    private int MAX_TIME_FOR_ANSWER_MAX = 5000;
    private int MAX_TIME_FOR_ANSWER = MAX_TIME_FOR_ANSWER_MAX;

    private final float FIRST_VALUE_OF_LIVES = 3f;
    private final float MAX_VALUE_OF_LIVES = 4f;
    private final float MAX_VALUE_PROGRESSBAR = 100f;

    private final double HARDENING_COEFFICIENT = 0.985;

    private final float INCREASE_LIVE = 1;
    private final float DECREASE_LIVE = -1;

    private GameDifficulty gameDifficulty = NULLGameDifficulty;

    private static int scoreInt = 0;
    private int countdownInt;
    private float livesFloat;
    private float livesProgressbarProgress;
    private boolean canClickOnButton = false;
    private boolean gameInProgress = false;
    private static boolean isNewBestScore = false;
    private boolean Paused = false;

    private ImageView shapeButtonImageView;
    private ImageView colorButtonImageView;
    private ImageView bothButtonImageView;
    private ImageView nonButtonImageView;
    private ImageView shapeImageView;
    private ImageView validationResultImageView;
    private TextView countdownTextView;
    private TextView remainTimeLiveEditText;
    private RoundCornerProgressBar livesProgressbar;
    private TextView shapeButtonTextView;
    private TextView colorButtonTextView;
    private TextView bothButtonTextView;
    private TextView nonButtonTextView;
    private ImageView pauseImageView;

    private AnimatorSet countdownAnimatorSet = null;
    private AnimatorSet changeShapeAnimatorSet = null;
    private AnimatorSet validationResultImageViewAlphaAndScaleAnimatorSet = null;
    private SmartCountDownTimer remainTimeLiveSmartCountDownTimer;
    private ShapeAndColorController randomShapeAndColorController;

    public GameShapeAndColorFragment() {
        super();
    }

    private void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shape_and_color_game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
        gameInProgress = true;
        generateFirstLevelAndStartTimer();
    }

    @Override
    public void onPause() {
        gameInProgress = false;
        if (remainTimeLiveSmartCountDownTimer != null) {
            remainTimeLiveSmartCountDownTimer.stop();
        }
        removeFragment();
        super.onPause();
    }

    private void findViews(View view) {
        shapeButtonTextView = view.findViewById(R.id.shape_button_text_view);
        colorButtonTextView = view.findViewById(R.id.color_button_text_view);
        bothButtonTextView = view.findViewById(R.id.both_button_text_view);
        nonButtonTextView = view.findViewById(R.id.non_button_text_view);
        shapeButtonImageView = view.findViewById(R.id.shape_only_button);
        colorButtonImageView = view.findViewById(R.id.color_only_button);
        bothButtonImageView = view.findViewById(R.id.shape_and_color_button);
        nonButtonImageView = view.findViewById(R.id.neither_button);
        shapeImageView = view.findViewById(R.id.shape_image_view);
        validationResultImageView = view.findViewById(R.id.validation_result_image);
        countdownTextView = view.findViewById(R.id.countdown_text_view);
        remainTimeLiveEditText = view.findViewById(R.id.remain_time_live_edit_text);
        livesProgressbar = view.findViewById(R.id.lives_progressbar);
        pauseImageView = view.findViewById(R.id.pause_button);
    }

    private void configure() {
        setGameDifficulty(Data.get().getGameDifficulty());
        configureAndStartLivesProgressbar();
        livesFloat = FIRST_VALUE_OF_LIVES;

        shapeButtonImageView.setOnClickListener(v -> buttonOnClick(shapeButton));
        colorButtonImageView.setOnClickListener(v -> buttonOnClick(colorButton));
        bothButtonImageView.setOnClickListener(v -> buttonOnClick(bothButton));
        nonButtonImageView.setOnClickListener(v -> buttonOnClick(nonButton));
        pauseImageView.setOnClickListener(v -> onPauseButtonPressed());

        setTextViewsFont();
    }

    private void buttonOnClick(GameInputButton clickedButton) {
        if (canClickOnButton) {
            evaluateInputButtonAndConfigureNextLevel(clickedButton);
            makeGameHarder();
        }
    }

    private void makeGameHarder() {
        decreaseMaxTimeForAnswer();
        increaseAnimationsSpeed();
    }

    private void increaseAnimationsSpeed() {
        GAME1_SLIDE_ANIMATION_DURATION =
                (int) (hardeningCoefficientAccordingToScoreInt() *
                        ShapeAndColorAnimationValues.
                                GAME1_SLIDE_ANIMATION_DURATION_MAX);

        GAME1_DECISION_RESULT_ANIMATION_DURATION =
                (int) (hardeningCoefficientAccordingToScoreInt() *
                        ShapeAndColorAnimationValues.
                                GAME1_DECISION_RESULT_ANIMATION_DURATION_MAX);
    }

    private void decreaseMaxTimeForAnswer() {
        remainTimeLiveSmartCountDownTimer.stop();

        MAX_TIME_FOR_ANSWER =
                (int) (hardeningCoefficientAccordingToScoreInt() *
                        MAX_TIME_FOR_ANSWER_MAX);

        configureLivesCountDown();
        remainTimeLiveSmartCountDownTimer.start();
    }

    private double hardeningCoefficientAccordingToScoreInt() {
        return Math.pow(HARDENING_COEFFICIENT, scoreInt);
    }

    private void startGame() {
        if (!gameInProgress) return;
        GameMusicController.startGameMusic(getActivity());
        setButtonsAndClockAndLiveVisible();
        canClickOnButton = true;
        configureLivesCountDown();
        nextLevel();
    }

    private void nextLevel() {
        ObjectAnimator shapeOutAnimationX = ObjectAnimator.ofFloat(
                shapeImageView,
                "scaleX",
                1f, 0f);
        shapeOutAnimationX.setDuration(GAME1_SLIDE_ANIMATION_DURATION);

        ObjectAnimator shapeOutAnimationY = ObjectAnimator.ofFloat(
                shapeImageView,
                "scaleY",
                1f, 0f);
        shapeOutAnimationY.setDuration(GAME1_SLIDE_ANIMATION_DURATION);

        final ObjectAnimator shapeInAnimationX = ObjectAnimator.ofFloat(
                shapeImageView,
                "scaleX",
                0f, 1f);
        shapeInAnimationX.setDuration(GAME1_SLIDE_ANIMATION_DURATION);

        final ObjectAnimator shapeInAnimationY = ObjectAnimator.ofFloat(
                shapeImageView,
                "scaleY",
                0f, 1f);
        shapeInAnimationY.setDuration(GAME1_SLIDE_ANIMATION_DURATION);

        changeShapeAnimatorSet = new AnimatorSet();
        changeShapeAnimatorSet.playTogether(shapeOutAnimationX, shapeOutAnimationY);
        changeShapeAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                shapeImageView.setImageResource(
                        randomShapeAndColorController.getRandom()
                );
                shapeInAnimationX.start();
                shapeInAnimationY.start();
            }
        });
        changeShapeAnimatorSet.start();
        remainTimeLiveSmartCountDownTimer.start();
    }

    private void endGame() {
        gameInProgress = false;
        remainTimeLiveSmartCountDownTimer.stop();
        closeGame();
        saveGameIfNewHighScore();
        if (getActivity() != null) {
            FragmentController.showShowScoreFragment(getActivity(), scoreInt, isNewBestScore);
        }
        removeFragment();
    }

    private void closeGame() {
        try {
            GameMusicController.stopGameMusic("closeGame");
            canClickOnButton = false;
            if (countdownAnimatorSet != null) {
                if (countdownAnimatorSet.isRunning()) {
                    countdownAnimatorSet.cancel();
                }
            }
            if (changeShapeAnimatorSet != null) {
                if (changeShapeAnimatorSet.isRunning()) {
                    changeShapeAnimatorSet.cancel();
                }
            }
            if (validationResultImageViewAlphaAndScaleAnimatorSet != null) {
                if (validationResultImageViewAlphaAndScaleAnimatorSet.isRunning()) {
                    validationResultImageViewAlphaAndScaleAnimatorSet.cancel();
                }
            }
            remainTimeLiveSmartCountDownTimer.stop();
        } catch (Exception e) {
            App.l(e.getMessage());
        }
    }

    private void generateFirstLevelAndStartTimer() {
        randomShapeAndColorController = new ShapeAndColorController(gameDifficulty);
        shapeImageView.setImageResource(
                randomShapeAndColorController.getRandom());
        countdownInt = 3;
        countdownAnimation();
    }

    private void increaseOrDecreaseLivesProgressbar(float increaseOrDecrease) {
        livesProgressbarProgress = (livesFloat + increaseOrDecrease) *
                (MAX_VALUE_PROGRESSBAR / MAX_VALUE_OF_LIVES);

        if (livesProgressbarProgress < 0f) {
            livesProgressbarProgress = 0f;
        }
        if (livesProgressbarProgress > MAX_VALUE_PROGRESSBAR) {
            livesProgressbarProgress = MAX_VALUE_PROGRESSBAR;
        }
        livesProgressbar.setProgress(livesProgressbarProgress);
    }

    private void configureLivesCountDown() {
        remainTimeLiveSmartCountDownTimer = new SmartCountDownTimer(MAX_TIME_FOR_ANSWER, 10) {
            @Override
            public void on_tick(long l) {
                //0 -> ... -> 1
                //#######################################
                if (!gameInProgress) return;
                countdownTextView.setAlpha(1f);
                countdownTextView.setText(getString(R.string.game_condition_template, scoreInt, (int) livesFloat));
                //#######################################
                float coefficientOfDecreaseLivesProgressbarProgress =
                        ((MAX_TIME_FOR_ANSWER - (float) l) / MAX_TIME_FOR_ANSWER);

                livesProgressbarProgress =
                        (livesFloat - coefficientOfDecreaseLivesProgressbarProgress) *
                                (MAX_VALUE_PROGRESSBAR / MAX_VALUE_OF_LIVES);
                livesProgressbar.setProgress(livesProgressbarProgress);

                remainTimeLiveEditText.setText(
                        String.valueOf(((float) (l / 100)) / 10)
                );
            }

            @Override
            public void on_finish() {
                if (gameInProgress && (!Paused)) {
                    updateLivesIntAndStartLiveAnimation(false);
                    remainTimeLiveEditText.setText("0.0");
                    remainTimeLiveSmartCountDownTimer.start();
                }
            }
        };
    }

    private void countdownAnimation() {
        ObjectAnimator XAnimator = ObjectAnimator.ofFloat(
                countdownTextView,
                "scaleX",
                1f, 3f
        );
        XAnimator.setDuration(ShapeAndColorAnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator YAnimation = ObjectAnimator.ofFloat(
                countdownTextView,
                "scaleY",
                1f, 3f
        );
        YAnimation.setDuration(ShapeAndColorAnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(
                countdownTextView,
                "alpha",
                1f, 0f
        );
        alphaAnimation.setDuration(ShapeAndColorAnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        countdownAnimatorSet = new AnimatorSet();
        countdownAnimatorSet.playTogether(XAnimator, YAnimation, alphaAnimation);
        countdownAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (countdownInt == 0) {
                    startGame();
                } else {
                    countdownAnimation();
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                String countDownTextViewString = countdownInt + " ";
                countdownTextView.setText(countDownTextViewString);
                countdownInt--;
                if (countdownInt == 2) {
                    MusicController.startAudio(getActivity(), R.raw.audio_count_down_3);
                } else if (countdownInt == 1) {
                    MusicController.startAudio(getActivity(), R.raw.audio_count_down_2);
                } else if (countdownInt == 0) {
                    MusicController.startAudio(getActivity(), R.raw.audio_count_down_1);
                }
            }
        });
        countdownAnimatorSet.start();
    }

    private void setEvaluateImageView(boolean answer) {
        if (answer) {
            validationResultImageView.setImageResource(
                    EvaluateImageController.getInstance().getTrueId());
        } else {
            validationResultImageView.setImageResource(
                    EvaluateImageController.getInstance().getFalseId());
        }
    }

    private void evaluateAnimationAndNextLevel(boolean answer) {
        validationResultImageView.setVisibility(View.VISIBLE);
        setEvaluateImageView(answer);
        isNewBestScore();
        ObjectAnimator validationResultImageViewAlpha = ObjectAnimator.ofFloat(
                validationResultImageView,
                "alpha",
                1f, 0f
        );
        validationResultImageViewAlpha.setDuration(GAME1_DECISION_RESULT_ANIMATION_DURATION);

        ObjectAnimator validationResultImageViewScaleX = ObjectAnimator.ofFloat(
                validationResultImageView,
                "scaleX",
                1f, 2f
        );
        validationResultImageViewScaleX.setDuration(GAME1_DECISION_RESULT_ANIMATION_DURATION);

        ObjectAnimator validationResultImageViewScaleY = ObjectAnimator.ofFloat(
                validationResultImageView,
                "scaleY",
                1f, 2f
        );
        validationResultImageViewScaleY.setDuration(GAME1_DECISION_RESULT_ANIMATION_DURATION);

        validationResultImageViewAlphaAndScaleAnimatorSet = new AnimatorSet();
        validationResultImageViewAlphaAndScaleAnimatorSet.playTogether(
                validationResultImageViewScaleY,
                validationResultImageViewScaleX,
                validationResultImageViewAlpha
        );
        validationResultImageViewAlphaAndScaleAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                nextLevel();
            }
        });
        validationResultImageViewAlphaAndScaleAnimatorSet.start();
    }

    private void isNewBestScore() {
        int oldScore = Data.get().getBestScore();
        if ((oldScore != 0) && (oldScore < scoreInt)) {
            if (!isNewBestScore) {
                App.t(getString(R.string.new_best_score));
            }
            isNewBestScore = true;
        }
    }

    private void evaluateInputButtonAndConfigureNextLevel(GameInputButton clickedButton) {
        boolean answer = randomShapeAndColorController.isInputButtonTrue(clickedButton);
        if (answer) {
            increaseScoreInt();
        }
        updateLivesIntAndStartLiveAnimation(answer);
        evaluateAnimationAndNextLevel(answer);
    }

    private void updateLivesIntAndStartLiveAnimation(boolean answer) {
        if (!gameInProgress) return;
        if (answer) {
            increaseOrDecreaseLivesProgressbar(INCREASE_LIVE);
            if (livesFloat != MAX_VALUE_OF_LIVES) {
                livesFloat++;
            }
        } else {
            increaseOrDecreaseLivesProgressbar(DECREASE_LIVE);
            livesFloat--;
            if (livesFloat == 0) {
                endGame();
            }
        }
    }

    private void saveGameIfNewHighScore() {
        if (Data.get().getBestScore() < scoreInt) {
            Data.get().setBestScore(scoreInt);
        }
    }

    private void setButtonsAndClockAndLiveVisible() {
        shapeButtonTextView.setVisibility(View.VISIBLE);
        colorButtonTextView.setVisibility(View.VISIBLE);
        bothButtonTextView.setVisibility(View.VISIBLE);
        nonButtonTextView.setVisibility(View.VISIBLE);
        remainTimeLiveEditText.setVisibility(View.VISIBLE);
        shapeButtonImageView.setVisibility(View.VISIBLE);
        bothButtonImageView.setVisibility(View.VISIBLE);
        colorButtonImageView.setVisibility(View.VISIBLE);
        nonButtonImageView.setVisibility(View.VISIBLE);
        livesProgressbar.setVisibility(View.VISIBLE);
        pauseImageView.setVisibility(View.VISIBLE);
    }

    private void configureAndStartLivesProgressbar() {
        livesProgressbar.setProgressColor(getResources().getColor(R.color.game_main_color));
        livesProgressbar.setBackgroundColor(Color.parseColor("#00000000"));
        livesProgressbar.setMax(100);

        livesProgressbarProgress = (FIRST_VALUE_OF_LIVES / MAX_VALUE_OF_LIVES) * MAX_VALUE_PROGRESSBAR;
        livesProgressbar.setProgress(livesProgressbarProgress);
    }

    private void setTextViewsFont() {
        Typeface font1 = Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_MAIN_FONT_PATH);
        shapeButtonTextView.setTypeface(font1);
        colorButtonTextView.setTypeface(font1);
        bothButtonTextView.setTypeface(font1);
        nonButtonTextView.setTypeface(font1);
        Typeface font2 = Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_MAIN_FONT_PATH);
        countdownTextView.setTypeface(font2);
    }

    private void onPauseButtonPressed() {
        Paused = !Paused;
        if (Paused) {
            pauseGame();
        } else {
            unPauseGame();
        }
    }

    private void pauseGame() {
        pauseImageView.setImageResource(R.drawable.ic_round_play_arrow_24);
        livesProgressbar.setProgress((livesFloat / MAX_VALUE_OF_LIVES) * MAX_VALUE_PROGRESSBAR);
        canClickOnButton = false;

        shapeImageView.setVisibility(View.INVISIBLE);
        shapeButtonImageView.setVisibility(View.INVISIBLE);
        shapeButtonTextView.setVisibility(View.INVISIBLE);
        colorButtonImageView.setVisibility(View.INVISIBLE);
        colorButtonTextView.setVisibility(View.INVISIBLE);
        bothButtonImageView.setVisibility(View.INVISIBLE);
        bothButtonTextView.setVisibility(View.INVISIBLE);
        nonButtonImageView.setVisibility(View.INVISIBLE);
        nonButtonTextView.setVisibility(View.INVISIBLE);
        countdownTextView.setText("");

        if (countdownAnimatorSet != null) {
            countdownAnimatorSet.pause();
        }
        if (changeShapeAnimatorSet != null) {
            changeShapeAnimatorSet.pause();
        }
        if (validationResultImageViewAlphaAndScaleAnimatorSet != null) {
            validationResultImageViewAlphaAndScaleAnimatorSet.pause();
        }
        remainTimeLiveSmartCountDownTimer.stop();
        randomShapeAndColorController = null;

        GameMusicController.pause();
    }

    private void unPauseGame() {
        pauseImageView.setImageResource(R.drawable.ic_round_pause_24);
        canClickOnButton = true;
        shapeImageView.setVisibility(View.VISIBLE);
        GameMusicController.restart();
        generateFirstLevelAndStartTimer();
    }

    private void increaseScoreInt() {
        if (gameDifficulty == instructions) {
            scoreInt++;
        } else if (gameDifficulty == easy) {
            scoreInt += 2;
        } else if (gameDifficulty == normal) {
            scoreInt += 3;
        } else if (gameDifficulty == hard) {
            scoreInt += 4;
        }
    }

    public static int getScoreInt() {
        return scoreInt;
    }

    public static boolean getIsNewBestScore() {
        return isNewBestScore;
    }

    public void removeFragment() {
        if (getActivity() != null) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .remove(this)
                    .commit();
        }
    }


}