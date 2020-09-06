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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;

import ir.artaateam.android.braingame.EvaluateImage;
import ir.artaateam.android.braingame.R;

public class Game1GameFragment extends Fragment {
    private final int MAX_NUMBER_OF_CLICK = 20;
    //*****************************
    private final int NUMBER_OF_SHAPES = 3;
    private final int NUMBER_OF_COLOR = 4;
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


    private int pointsInt = 0;
    private int countdownInt = 3;
    private int oldColor = -1;
    private int newColor = -1;
    private int oldShape = -1;
    private int newShape = -1;
    private int shapeIndex;
    private int numberOfClick = 0;
    private boolean canClickOnButton = false;

    private Button shapeOnlyButton;
    private Button colorOnlyButton;
    private Button shapeAndColorButton;
    private Button neitherButton;
    private ImageView shapeImageView;
    private ImageView validationResultImageView;
    private TextView speedCountdownTextView;
    private TextView pointsTextView;

    private CountDownTimer gameTimer = null;
    private AnimatorSet countdownAnimatorSet = null;
    private AnimatorSet changeShapeAnimatorSet = null;
    private AnimatorSet validationResultAnimatorSet = null;
    Random random;

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
        setNewRandomShape();
        updateNewColorAndShapeByIndex();
        countdownAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        closeGame();
    }


    private void findViews(View view) {
        shapeOnlyButton = view.findViewById(R.id.shape_only_button);
        colorOnlyButton = view.findViewById(R.id.color_only_button);
        shapeAndColorButton = view.findViewById(R.id.shape_and_color_button);
        neitherButton = view.findViewById(R.id.neither_button);
        shapeImageView = view.findViewById(R.id.shape_image_view);
        validationResultImageView = view.findViewById(R.id.validation_result_image);
        speedCountdownTextView = view.findViewById(R.id.speed_countdown);
        pointsTextView = view.findViewById(R.id.points);
    }

    private void configure() {
        random = new Random();

        shapeOnlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClickOnButton && numberOfClick < MAX_NUMBER_OF_CLICK) {
                    numberOfClick++;
                    evaluateInputButton(SHAPE_ONLY);
                }
                if (numberOfClick == MAX_NUMBER_OF_CLICK) {
                    //savePoint();
                    closeGame();
                    showPoints();
                }
            }
        });
        colorOnlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClickOnButton && numberOfClick < MAX_NUMBER_OF_CLICK) {
                    numberOfClick++;
                    evaluateInputButton(COLOR_ONLY);
                }
                if (numberOfClick == MAX_NUMBER_OF_CLICK) {
                    //savePoint();
                    closeGame();
                    showPoints();
                }
            }
        });
        shapeAndColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClickOnButton && numberOfClick < MAX_NUMBER_OF_CLICK) {
                    numberOfClick++;
                    evaluateInputButton(SHAPE_AND_COLOR);
                }
                if (numberOfClick == MAX_NUMBER_OF_CLICK) {
                    //savePoint();
                    closeGame();
                    showPoints();
                }
            }
        });
        neitherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClickOnButton && numberOfClick < MAX_NUMBER_OF_CLICK) {
                    numberOfClick++;
                    evaluateInputButton(NEITHER);
                }
                if (numberOfClick == MAX_NUMBER_OF_CLICK) {
                    //savePoint();
                    closeGame();
                    showPoints();
                }
            }
        });
    }

    private void countdownAnimation() {
        ObjectAnimator XAnimator = ObjectAnimator.ofFloat(
                speedCountdownTextView,
                "scaleX",
                1f, 3f
        );
        XAnimator.setDuration(Game1UtilValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator YAnimation = ObjectAnimator.ofFloat(
                speedCountdownTextView,
                "scaleY",
                1f, 3f
        );
        YAnimation.setDuration(Game1UtilValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(
                speedCountdownTextView,
                "alpha",
                1f, 0f
        );
        alphaAnimation.setDuration(Game1UtilValues.GAME1_COUNT_DOWN_ANIMATION_DURATION);

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
                speedCountdownTextView.setText(String.valueOf(countdownInt));
                countdownInt--;
            }
        });
        countdownAnimatorSet.start();
    }

    private void startGame() {
        canClickOnButton = true;
        gameTimer = new CountDownTimer(Game1UtilValues.GAME1_TIME_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                //savePoint();
                closeGame();
                showPoints();
            }
        };
        gameTimer.start();
        nextLevel();
    }

    private void showPoints() {
        shapeOnlyButton.setVisibility(View.INVISIBLE);
        colorOnlyButton.setVisibility(View.INVISIBLE);
        shapeAndColorButton.setVisibility(View.INVISIBLE);
        neitherButton.setVisibility(View.INVISIBLE);
        shapeImageView.setVisibility(View.INVISIBLE);
        validationResultImageView.setVisibility(View.INVISIBLE);
        speedCountdownTextView.setVisibility(View.INVISIBLE);

        pointsTextView.setText(getString(R.string.speed_final_score, pointsInt));
        ObjectAnimator pointsX = ObjectAnimator.ofFloat(
                pointsTextView,
                "scaleX",
                0f, 2f
        );
        pointsX.setDuration(Game1UtilValues.GAME1_SCORE_ANIMATION_DURATION);
        pointsX.start();

        ObjectAnimator pointsY = ObjectAnimator.ofFloat(
                pointsTextView,
                "scaleY",
                0f, 2f
        );
        pointsY.setDuration(Game1UtilValues.GAME1_SCORE_ANIMATION_DURATION);
        pointsY.start();

        ObjectAnimator pointsRotateX = ObjectAnimator.ofFloat(
                pointsTextView,
                "RotationX",
                0f, 720f
        );
        pointsRotateX.setDuration(Game1UtilValues.GAME1_SCORE_ANIMATION_DURATION);
        pointsRotateX.start();

        ObjectAnimator pointsRotate = ObjectAnimator.ofFloat(
                pointsTextView,
                "Rotation",
                0f, 360f
        );
        pointsRotate.setDuration(Game1UtilValues.GAME1_SCORE_ANIMATION_DURATION);
        pointsRotate.start();

    }

    private void nextLevel() {
        ObjectAnimator imageOutAnimation = ObjectAnimator.ofFloat(
                shapeImageView,
                "translationX",
                0f, -500f);
        imageOutAnimation.setDuration(Game1UtilValues.GAME1_SLIDE_ANIMATION_DURATION);

        final ObjectAnimator imageInAnimation = ObjectAnimator.ofFloat(
                shapeImageView,
                "translationX",
                500f, 0f);
        imageInAnimation.setDuration(Game1UtilValues.GAME1_SLIDE_ANIMATION_DURATION);

        changeShapeAnimatorSet = new AnimatorSet();
        changeShapeAnimatorSet.playTogether(imageOutAnimation);
        changeShapeAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setNextNewRandomShape();
                updateNewColorAndShapeByIndex();
                imageInAnimation.start();
            }
        });
        changeShapeAnimatorSet.start();
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

    private void evaluateAnimation(boolean answer) {
        validationResultImageView.setVisibility(View.VISIBLE);
        setEvaluate(answer);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(
                validationResultImageView,
                "alpha",
                1f
        );
        alphaAnimation.setDuration(Game1UtilValues.GAME1_DECISION_RESULT_ANIMATION_DURATION);
        validationResultAnimatorSet = new AnimatorSet();
        validationResultAnimatorSet.playTogether(alphaAnimation);
        validationResultAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                validationResultImageView.setVisibility(View.INVISIBLE);
            }
        });
        validationResultAnimatorSet.start();

    }

    private void evaluateInputButton(int button) {
        boolean answer = false;
        switch (button) {
            case SHAPE_ONLY: {
                if ((oldColor != newColor) && (oldShape == newShape)) {
                    answer = true;
                    pointsInt++;
                }
                break;
            }
            case COLOR_ONLY: {
                if ((oldColor == newColor) && (oldShape != newShape)) {
                    answer = true;
                    pointsInt++;
                }
                break;
            }
            case SHAPE_AND_COLOR: {
                if ((oldColor == newColor) && (oldShape == newShape)) {
                    answer = true;
                    pointsInt++;
                }
                break;
            }
            case NEITHER: {
                if ((oldColor != newColor) && (oldShape != newShape)) {
                    answer = true;
                    pointsInt++;
                }
                break;
            }
        }
        evaluateAnimation(answer);
        nextLevel();
    }

    private void closeGame() {
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
        if (validationResultAnimatorSet != null) {
            if (validationResultAnimatorSet.isRunning()) {
                validationResultAnimatorSet.cancel();
            }
        }
        if (gameTimer != null) {
            gameTimer.cancel();
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

//    private void savePoint() {
//        String playerName = getArguments().getString("NAME", "");
//        RankList rankList = Preferences.getInstance(getActivity(),"game1").getRankList();
//        rankList.addPlayer(new Player(playerName, pointsInt));
//        Preferences.getInstance(getActivity(),"game1").putRankList(rankList);
//    }
}
