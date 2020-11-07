package ir.artaateam.android.braingame.game1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.Random;

import ir.artaateam.android.braingame.EvaluateImage;
import ir.artaateam.android.braingame.MainActivity;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.RandomShapeAndColor;
import ir.artaateam.android.braingame.User;
import ir.artaateam.android.braingame.UserPreferences;
import ir.artaateam.android.braingame.app.MyApplication;

public class Game1GameFragment extends Fragment {
    private int GAME1_SLIDE_ANIMATION_DURATION = Game1AnimationValues.GAME1_SLIDE_ANIMATION_DURATION_MAX;
    private int GAME1_DECISION_RESULT_ANIMATION_DURATION = Game1AnimationValues.GAME1_DECISION_RESULT_ANIMATION_DURATION_MAX;


    private int MAX_TIME_FOR_ANSWER_MAX = 5000;
    private int MAX_TIME_FOR_ANSWER = MAX_TIME_FOR_ANSWER_MAX;


    private final int NUMBER_OF_SHAPES = 3;
    private final int NUMBER_OF_COLOR = 4;


    private final float FIRST_VALUE_OF_LIVES = 3f;
    private final float MAX_VALUE_OF_LIVES = 4f;
    private final float MAX_VALUE_PROGRESSBAR = 100f;


    private final double HARDENING_COEFFICIENT = 0.985;

    private final long GAME_MUSIC1_DURATION = 15360;
    private final long GAME_MUSIC2_DURATION = 84480;
    private final long GAME_MUSIC3_DURATION = 1503;
    private final long GAME_MUSIC4_DURATION = 310000;
    //*****************************
    private final float INCREASE_LIVE = 1;
    private final float DECREASE_LIVE = -1;
    //*****************************
    private final int SHAPE_ONLY = 0;
    private final int COLOR_ONLY = 1;
    private final int SHAPE_AND_COLOR = 2;
    private final int NEITHER = 3;
    //*****************************
    private final int RED_COLOR = 0;
    private final int YELLOW_COLOR = 1;
    private final int GREEN_COLOR = 2;
    private final int PURPLE_COLOR = 3;
    //*****************************
    private final int TRIANGLE_SHAPE = 0;
    private final int SQUARE_SHAPE = 1;
    private final int CIRCLE_SHAPE = 2;
    //*****************************


    private int scoreInt = 0;
    private int countdownInt = 3;
    private int oldColor = -1;
    private int newColor = -1;
    private int oldShape = -1;
    private int newShape = -1;
    private int shapeIndex;
    private int gameMusicNumber = 1;
    private float livesFloat;
    private float livesProgressbarProgress;
    private boolean canClickOnButton = false;
    private boolean gameInProgress = false;
    private boolean isRemainTimeLiveCountDownTimerStarted = false;
    private boolean isMusicTimerStarted = false;
    private boolean isNewBestScore=false;

    private ImageView shapeOnlyImageView;
    private ImageView colorOnlyImageView;
    private ImageView shapeAndColorImageView;
    private ImageView neitherImageView;
    private ImageView shapeImageView;
    private ImageView validationResultImageView;
    private TextView countdownTextView;
    private TextView remainTimeLiveEditText;
    private RoundCornerProgressBar livesProgressbar;


    private AnimatorSet countdownAnimatorSet = null;
    private AnimatorSet changeShapeAnimatorSet = null;
    private AnimatorSet validationResultImageViewAlphaAndScaleAnimatorSet = null;
    private CountDownTimer remainTimeLiveCountDownTimer;
    private CountDownTimer nextMusicTimer;
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game1_game_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
        generateFirstLevelAndStartTimer();
        //ImageView imageView;
        //Drawable drawable;
        //drawable.setTint(R.color.shape_blue_color);
        //imageView.setImageDrawable(drawable);
    }

    @Override
    public void onPause() {
        super.onPause();
        gameInProgress = false;
        closeGame();
    }

    private void findViews(View view) {
        shapeOnlyImageView = view.findViewById(R.id.shape_only_button);
        colorOnlyImageView = view.findViewById(R.id.color_only_button);
        shapeAndColorImageView = view.findViewById(R.id.shape_and_color_button);
        neitherImageView = view.findViewById(R.id.neither_button);
        shapeImageView = view.findViewById(R.id.shape_image_view);
        validationResultImageView = view.findViewById(R.id.validation_result_image);
        countdownTextView = view.findViewById(R.id.countdown_text_view);
        remainTimeLiveEditText = view.findViewById(R.id.remain_time_live_edit_text);
        livesProgressbar = view.findViewById(R.id.lives_progressbar);
    }

    private void configure() {
        configureAndStartLivesProgressbar();
        livesFloat = FIRST_VALUE_OF_LIVES;
        random = new Random();

        shapeOnlyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClick(SHAPE_ONLY);
            }
        });
        colorOnlyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClick(COLOR_ONLY);
            }
        });
        shapeAndColorImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClick(SHAPE_AND_COLOR);
            }
        });
        neitherImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOnClick(NEITHER);
            }
        });
    }

    private void buttonOnClick(int clickedButton) {
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
                        Game1AnimationValues.
                                GAME1_SLIDE_ANIMATION_DURATION_MAX);

        GAME1_DECISION_RESULT_ANIMATION_DURATION =
                (int) (hardeningCoefficientAccordingToScoreInt() *
                        Game1AnimationValues.
                                GAME1_DECISION_RESULT_ANIMATION_DURATION_MAX);
    }

    private void decreaseMaxTimeForAnswer() {
        remainTimeLiveCountDownTimer.cancel();

        MAX_TIME_FOR_ANSWER =
                (int) (hardeningCoefficientAccordingToScoreInt() *
                        MAX_TIME_FOR_ANSWER_MAX);

        configureLivesCountDown();
        remainTimeLiveCountDownTimer.start();
    }

    private double hardeningCoefficientAccordingToScoreInt() {
        return Math.pow(HARDENING_COEFFICIENT, scoreInt);
    }

    private void configureNextMusic(long duration, final int res) {
        nextMusicTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                configureNextMusicOnFinish(res);
            }
        };
        nextMusicTimer.start();
        isMusicTimerStarted = true;
    }

    private void configureNextMusicOnFinish(int res) {
        if (gameInProgress) {
            gameMusicNumber++;
            switch (gameMusicNumber) {
                case 2: {
                    MainActivity.startMusic(getActivity(), res, false);
                    configureNextMusic(GAME_MUSIC2_DURATION, R.raw.game_music3);
                    break;
                }
                case 3: {
                    MainActivity.startMusic(getActivity(), res, false);
                    configureNextMusic(GAME_MUSIC3_DURATION, R.raw.game_music4);
                    break;
                }
                case 4: {
                    MainActivity.startMusic(getActivity(), res, true);
                    break;
                }
            }
        }
    }

    private void startGame() {
        setButtonsAndClockAndLiveVisible();
        gameInProgress = true;
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
                setNextNewRandomShape();
                updateNewColorAndShapeByIndex();
                shapeInAnimationX.start();
                shapeInAnimationY.start();
            }
        });
        changeShapeAnimatorSet.start();
        remainTimeLiveCountDownTimer.start();
        isRemainTimeLiveCountDownTimerStarted = true;
    }

    private void endGame() {
        gameInProgress = false;
        saveGameIfNewHighScore();
        MainActivity.showShowScoreFragment(getActivity(), scoreInt,isNewBestScore);
        closeGame();
        removeFragment();
    }

    private void closeGame() {
        try {
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
            if (isRemainTimeLiveCountDownTimerStarted) {
                remainTimeLiveCountDownTimer.cancel();
            }
            if (isMusicTimerStarted) {
                nextMusicTimer.cancel();
            }
        } catch (Exception ignored) {
        }
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    private void generateFirstLevelAndStartTimer() {
        setNewRandomShape();
        updateNewColorAndShapeByIndex();
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

    private int shapeOnlyId() {
        int newShapeIndex = shapeIndex;
        int temp;
        do {
            temp = random.nextInt(NUMBER_OF_COLOR);
            newShapeIndex /= NUMBER_OF_COLOR;
            newShapeIndex *= NUMBER_OF_COLOR;
            newShapeIndex += temp;
        } while (newShapeIndex == shapeIndex);
        shapeIndex = newShapeIndex;
        return CardsImage.getInstance().getIdByIndex(newShapeIndex);
    }

    private int colorOnlyId() {
        int newShapeIndex = shapeIndex;
        do {
            newShapeIndex += random.nextInt(NUMBER_OF_SHAPES) * NUMBER_OF_COLOR;
            newShapeIndex %= NUMBER_OF_COLOR * NUMBER_OF_SHAPES;
        } while (newShapeIndex == shapeIndex);
        shapeIndex = newShapeIndex;
        return CardsImage.getInstance().getIdByIndex(newShapeIndex);
    }

    private int shapeAndColorId() {
        return CardsImage.getInstance().getIdByIndex(shapeIndex);
    }

    private int neitherId() {
        int newShapeIndex;
        int temp;
        do {
            newShapeIndex = (shapeIndex / NUMBER_OF_COLOR) * NUMBER_OF_COLOR + NUMBER_OF_COLOR;
            temp = random.nextInt((NUMBER_OF_SHAPES - 1) * NUMBER_OF_COLOR);
            newShapeIndex += temp;
            newShapeIndex %= NUMBER_OF_COLOR * NUMBER_OF_SHAPES;
        } while ((newShapeIndex % NUMBER_OF_COLOR) == (shapeIndex % NUMBER_OF_COLOR));
        shapeIndex = newShapeIndex;
        return CardsImage.getInstance().getIdByIndex(newShapeIndex);
    }

    private void configureLivesCountDown() {
        remainTimeLiveCountDownTimer = new CountDownTimer(MAX_TIME_FOR_ANSWER, 100) {
            @Override
            public void onTick(long l) {
                //0 -> ... -> 1
                //#######################################
                countdownTextView.setAlpha(1f);
                countdownTextView.setText("your point : "+scoreInt+"\nlives : "+livesFloat);
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
            public void onFinish() {
                if (gameInProgress) {
                    updateLivesIntAndStartLiveAnimation(false);
                    remainTimeLiveEditText.setText("0.0");
                    remainTimeLiveCountDownTimer.start();
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
        XAnimator.setDuration(Game1AnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator YAnimation = ObjectAnimator.ofFloat(
                countdownTextView,
                "scaleY",
                1f, 3f
        );
        YAnimation.setDuration(Game1AnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(
                countdownTextView,
                "alpha",
                1f, 0f
        );
        alphaAnimation.setDuration(Game1AnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        countdownAnimatorSet = new AnimatorSet();
        countdownAnimatorSet.playTogether(XAnimator, YAnimation, alphaAnimation);
        countdownAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (countdownInt == 0) {
                    MainActivity.startMusic(getActivity(), R.raw.game_music1, false);
                    configureNextMusic(GAME_MUSIC1_DURATION, R.raw.game_music2);
                    startGame();
                } else {
                    countdownAnimation();
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                countdownTextView.setText(String.valueOf(countdownInt));
                countdownInt--;
                if (countdownInt == 2) {
                    MainActivity.startAudio(getActivity(), R.raw.cd1, false);
                } else if (countdownInt == 1) {
                    MainActivity.startAudio(getActivity(), R.raw.cd2, false);
                } else if (countdownInt == 0) {
                    MainActivity.startAudio(getActivity(), R.raw.cd3, false);
                }
            }
        });
        countdownAnimatorSet.start();
    }

    private void setEvaluate(boolean answer) {

        if (answer) {
            validationResultImageView.setImageResource(
                    EvaluateImage.getInstance().getTrueId());
        } else {
            validationResultImageView.setImageResource(
                    EvaluateImage.getInstance().getFalseId());
        }
    }

    private void evaluateAnimationAndNextLevel(boolean answer) {
        validationResultImageView.setVisibility(View.VISIBLE);
        setEvaluate(answer);
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

    private void isNewBestScore(){
        int oldScore = UserPreferences.getInstance().getUser().getBestScore();
        if ((oldScore!=0)&&(oldScore<scoreInt)){
            isNewBestScore=true;
            Toast.makeText(MyApplication.getContext(),
                    R.string.new_best_score, Toast.LENGTH_SHORT).show();
        }
    }

    private void evaluateInputButtonAndConfigureNextLevel(int button) {
        boolean answer = false;
        switch (button) {
            case SHAPE_ONLY: {
                if ((oldColor != newColor) && (oldShape == newShape)) {
                    answer = true;
                    scoreInt++;
                }
                break;
            }
            case COLOR_ONLY: {
                if ((oldColor == newColor) && (oldShape != newShape)) {
                    answer = true;
                    scoreInt++;
                }
                break;
            }
            case SHAPE_AND_COLOR: {
                if ((oldColor == newColor) && (oldShape == newShape)) {
                    answer = true;
                    scoreInt++;
                }
                break;
            }
            case NEITHER: {
                if ((oldColor != newColor) && (oldShape != newShape)) {
                    answer = true;
                    scoreInt++;
                }
                break;
            }
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

    private void setNewRandomShape() {
        int id = getRandomShapeIdAndSetShapeIndex();
        shapeImageView.setImageResource(id);
    }

    public int getRandomShapeIdAndSetShapeIndex() {
        int newShapeIndex = random.nextInt() % 12;
        if (newShapeIndex < 0) {
            newShapeIndex = (-newShapeIndex);
        }
        shapeIndex = newShapeIndex;
        return CardsImage.getInstance().getIdByIndex(newShapeIndex);
    }

    private void setNextNewRandomShape() {
        int id = getNextRandomShapeIdAndSetShapeIndex();
        shapeImageView.setImageResource(id);
    }

    public int getNextRandomShapeIdAndSetShapeIndex() {
        int whatIsAnswer = random.nextInt(4);
        int newImageId = -1;
        switch (whatIsAnswer) {
            case SHAPE_ONLY: {
                newImageId = shapeOnlyId();
                break;
            }
            case COLOR_ONLY: {
                newImageId = colorOnlyId();
                break;
            }
            case SHAPE_AND_COLOR: {
                newImageId = shapeAndColorId();
                break;
            }
            case NEITHER: {
                newImageId = neitherId();
                break;
            }
        }
        return newImageId;
    }

    private void updateNewColorAndShapeByIndex() {
        oldColor = newColor;
        oldShape = newShape;
        //                              %>> set new shape <<%

        if (shapeIndex < 4) {                //shapeIndex{0-3}-> triangle
            newShape = TRIANGLE_SHAPE;
        } else if (shapeIndex < 8) {         //shapeIndex{4-7}-> square
            newShape = SQUARE_SHAPE;
        } else if (shapeIndex < 12) {        //shapeIndex{8-11}-> circle
            newShape = CIRCLE_SHAPE;
        }
        //                              %>> set new color <<%

        if (shapeIndex % 4 == 0) {                 //shapeIndex{0,4,8}-> red
            newColor = RED_COLOR;
        } else if (shapeIndex % 4 == 1) {           //shapeIndex{1,5,9}-> yellow
            newColor = YELLOW_COLOR;
        } else if (shapeIndex % 4 == 2) {           //shapeIndex{2,6,10}-> blue
            newColor = GREEN_COLOR;
        } else if (shapeIndex % 4 == 3) {           //shapeIndex{3,7,11}-> purple
            newColor = PURPLE_COLOR;
        }
    }

    private void saveGameIfNewHighScore() {
        User user = UserPreferences.getInstance().getUser();
        if (user.getBestScore() < scoreInt) {
            user.setBestScore(scoreInt);
            UserPreferences.getInstance().putUser(user);
        }
    }

    private void setButtonsAndClockAndLiveVisible() {
        remainTimeLiveEditText.setVisibility(View.VISIBLE);
        shapeOnlyImageView.setVisibility(View.VISIBLE);
        shapeAndColorImageView.setVisibility(View.VISIBLE);
        colorOnlyImageView.setVisibility(View.VISIBLE);
        neitherImageView.setVisibility(View.VISIBLE);
        livesProgressbar.setVisibility(View.VISIBLE);
    }

    private void configureAndStartLivesProgressbar() {
        livesProgressbar.setProgressColor(getResources().getColor(R.color.game_main_color));
        livesProgressbar.setBackgroundColor(Color.parseColor("#00000000"));
        livesProgressbar.setMax(100);

        livesProgressbarProgress = (FIRST_VALUE_OF_LIVES / MAX_VALUE_OF_LIVES) * MAX_VALUE_PROGRESSBAR;
        livesProgressbar.setProgress(livesProgressbarProgress);
    }
}
