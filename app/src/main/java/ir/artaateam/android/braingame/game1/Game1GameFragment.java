package ir.artaateam.android.braingame.game1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;

import ir.artaateam.android.braingame.EvaluateImage;
import ir.artaateam.android.braingame.MainActivity;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.User;
import ir.artaateam.android.braingame.UserPreferences;

public class Game1GameFragment extends Fragment {
    private int GAME1_SLIDE_ANIMATION_DURATION = Game1AnimationValues.GAME1_SLIDE_ANIMATION_DURATION_MAX;
    private int GAME1_DECISION_RESULT_ANIMATION_DURATION = Game1AnimationValues.GAME1_DECISION_RESULT_ANIMATION_DURATION_MAX;
    private int GAME1_INCREASE_OR_DECREASE_LIVES_ANIMATION_DURATION = Game1AnimationValues.GAME1_INCREASE_OR_DECREASE_LIVES_ANIMATION_DURATION_MAX;


    private int MAX_TIME_FOR_ANSWER_MAX = 5000;
    private int MAX_TIME_FOR_ANSWER = MAX_TIME_FOR_ANSWER_MAX;


    private final int NUMBER_OF_SHAPES = 3;
    private final int NUMBER_OF_COLOR = 4;


    private final float FIRST_LIVES = 3f;
    private final float MAX_LIVES = 4f;


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
    private float oldGraphScaleLives;
    private float oldGraphScaleLivesTime;
    private float newGraphScaleLives;
    private float newGraphScaleLivesTime;
    private boolean canClickOnButton = false;
    private boolean gameInProgress = false;
    private boolean isRemainTimeLiveCountDownTimerStarted = false;
    private boolean isMusicTimerStarted = false;
    private boolean isRemainTimeLiveFinished = false;

    private ImageView shapeOnlyImageView;
    private ImageView colorOnlyImageView;
    private ImageView shapeAndColorImageView;
    private ImageView neitherImageView;
    private ImageView shapeImageView;
    private ImageView validationResultImageView;
    private TextView speedCountdownTextView;
    private TextView remainTimeLiveEditText;
    private View graphViewLives;


    private AnimatorSet countdownAnimatorSet = null;
    private AnimatorSet changeShapeAnimatorSet = null;
    private AnimatorSet validationResultImageViewAlphaAndScaleAnimatorSet = null;
    private AnimatorSet graphLivesAnimationSet = null;
    private AnimatorSet graphTimeAnimationSet = null;
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
        speedCountdownTextView = view.findViewById(R.id.speed_countdown);
        remainTimeLiveEditText = view.findViewById(R.id.remain_time_live_edit_text);
        graphViewLives = view.findViewById(R.id.graph_view_lives);
    }

    private void configure() {
        livesFloat = FIRST_LIVES;
        random = new Random();
        configureGraphScaleAndValues();

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
            configureAndStartGraphTime();
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

        GAME1_INCREASE_OR_DECREASE_LIVES_ANIMATION_DURATION =
                (int) (hardeningCoefficientAccordingToScoreInt() *
                        Game1AnimationValues.
                                GAME1_INCREASE_OR_DECREASE_LIVES_ANIMATION_DURATION_MAX);
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
        configureAndStartGraphTime();
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

        ObjectAnimator shapeOutAnimationRotation = ObjectAnimator.ofFloat(
                shapeImageView,
                "RotationY",
                0f, 720f);
        shapeOutAnimationRotation.setDuration(GAME1_SLIDE_ANIMATION_DURATION);


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

        final ObjectAnimator shapeInAnimationRotation = ObjectAnimator.ofFloat(
                shapeImageView,
                "RotationX",
                0f, -720f);
        shapeInAnimationRotation.setDuration(GAME1_SLIDE_ANIMATION_DURATION);

        changeShapeAnimatorSet = new AnimatorSet();
        changeShapeAnimatorSet.playTogether(shapeOutAnimationX,shapeOutAnimationY,shapeOutAnimationRotation);
        changeShapeAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setNextNewRandomShape();
                updateNewColorAndShapeByIndex();
                shapeInAnimationX.start();
                shapeInAnimationY.start();
                shapeInAnimationRotation.start();
            }
        });
        changeShapeAnimatorSet.start();
        remainTimeLiveCountDownTimer.start();
        isRemainTimeLiveCountDownTimerStarted = true;
    }

    private void endGame() {
        gameInProgress = false;
        saveGameIfNewHighScore();
        MainActivity.showShowScoreFragment(getActivity(), scoreInt);
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
            if (graphLivesAnimationSet != null) {
                if (graphLivesAnimationSet.isRunning()) {
                    graphLivesAnimationSet.cancel();
                }
            }
            if (graphTimeAnimationSet != null) {
                if (graphTimeAnimationSet.isRunning()) {
                    graphTimeAnimationSet.cancel();
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

    private void increaseOrDecreaseGraphLives(float increaseOrDecrease) {
        oldGraphScaleLives = newGraphScaleLives;
        newGraphScaleLives = newGraphScaleLives + ((1f / MAX_LIVES) * (increaseOrDecrease));
        if (newGraphScaleLives < 0f) {
            newGraphScaleLives = 0f;
        }
        if (newGraphScaleLives > 1f) {
            newGraphScaleLives = 1f;
        }

        ObjectAnimator graphScaleX = ObjectAnimator.ofFloat(
                graphViewLives,
                "scaleX",
                oldGraphScaleLives,
                newGraphScaleLives
        );
        graphScaleX.setDuration(GAME1_INCREASE_OR_DECREASE_LIVES_ANIMATION_DURATION);
        configureGraphLivesAnimationSet();
        graphLivesAnimationSet.playTogether(graphScaleX);
        graphLivesAnimationSet.start();
    }

    private void configureGraphTimeAnimationSet() {
        if (graphTimeAnimationSet == null) {
            graphTimeAnimationSet = new AnimatorSet();
        }
        try {
            if (graphTimeAnimationSet.isRunning()) {
                graphTimeAnimationSet.cancel();
            }
        } catch (Exception ignored) {
        }
    }///////////////////////////////

    private void configureGraphLivesAnimationSet() {
        if (graphLivesAnimationSet == null) {
            graphLivesAnimationSet = new AnimatorSet();
        }
        try {
            if (graphLivesAnimationSet.isRunning()) {
                graphLivesAnimationSet.cancel();
            }
        } catch (Exception ignored) {
        }
    }

    private void configureGraphScaleAndValues() {
        oldGraphScaleLives = FIRST_LIVES / MAX_LIVES;
        newGraphScaleLives = FIRST_LIVES / MAX_LIVES;
        graphViewLives.setScaleX(newGraphScaleLives);
    }

    private void configureAndStartGraphTime() {
        configureGraphTimeAnimationSet();
        oldGraphScaleLivesTime = newGraphScaleLives;
        newGraphScaleLivesTime = oldGraphScaleLivesTime - (1f / MAX_LIVES);
        ObjectAnimator graphTimeScaleX = ObjectAnimator.ofFloat(
                graphViewLives,
                "ScaleX",
                oldGraphScaleLivesTime,
                newGraphScaleLivesTime
        );
        graphTimeScaleX.setDuration(MAX_TIME_FOR_ANSWER);
        try {
            if (graphTimeAnimationSet.isRunning()) {
                graphTimeAnimationSet.cancel();
            }
        } catch (Exception ignored) {
        }
        graphTimeAnimationSet.playTogether(graphTimeScaleX);
        graphTimeAnimationSet.start();
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
                remainTimeLiveEditText.setText(
                        String.valueOf(((float) (l / 100)) / 10)
                );
            }

            @Override
            public void onFinish() {
                if (gameInProgress) {
                    updateLivesIntAndStartLiveAnimation(false);
                    remainTimeLiveEditText.setText("0.0");
                    configureAndStartGraphTime();
                    isRemainTimeLiveFinished=true;
                    remainTimeLiveCountDownTimer.start();
                }
            }
        };
    }

    private void countdownAnimation() {
        ObjectAnimator XAnimator = ObjectAnimator.ofFloat(
                speedCountdownTextView,
                "scaleX",
                1f, 3f
        );
        XAnimator.setDuration(Game1AnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator YAnimation = ObjectAnimator.ofFloat(
                speedCountdownTextView,
                "scaleY",
                1f, 3f
        );
        YAnimation.setDuration(Game1AnimationValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(
                speedCountdownTextView,
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
                speedCountdownTextView.setText(String.valueOf(countdownInt));
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
        if(isRemainTimeLiveFinished){
//            answer
//                    age zaman remainTime live tamom shod pas nabayad + beshe emtiaz !
        }
        updateLivesIntAndStartLiveAnimation(answer);
        evaluateAnimationAndNextLevel(answer);
    }

    private void updateLivesIntAndStartLiveAnimation(boolean answer) {
        if (!gameInProgress) return;
        if (answer) {
            increaseOrDecreaseGraphLives(INCREASE_LIVE);
            if (livesFloat != MAX_LIVES) {
                livesFloat++;
            }
        } else {
            increaseOrDecreaseGraphLives(DECREASE_LIVE);
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
        User user = UserPreferences.getInstance(getActivity()).getUser();
        if (user.getBestScore() < scoreInt) {
            user.setBestScore(scoreInt);
            UserPreferences.getInstance(getActivity()).putUser(user);
        }
    }

    private void setButtonsAndClockAndLiveVisible() {
        remainTimeLiveEditText.setVisibility(View.VISIBLE);
        shapeOnlyImageView.setVisibility(View.VISIBLE);
        shapeAndColorImageView.setVisibility(View.VISIBLE);
        colorOnlyImageView.setVisibility(View.VISIBLE);
        neitherImageView.setVisibility(View.VISIBLE);
        graphViewLives.setVisibility(View.VISIBLE);
    }
}
