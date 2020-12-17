package ir.artaateam.android.braingame.Fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.Random;

import ir.artaateam.android.braingame.App.MyApplication;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Controllers.ShapeAndColorController;
import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.SmartCountDownTimer;
import ir.artaateam.android.braingame.R;

public class AnimationsFragment extends Fragment {
    // TODO update
    private static final int NUMBER_OF_IMAGE_VIEWS = 40;
    private static final int IMAGE_VIEWS_SIZE = 50;
    private static final int ANIMATION_DURATION = 10000;

    private ConstraintLayout parentLayout;
    private AppCompatImageView[] shapeImageViews;

    private static int WIDTH;
    private static int HEIGHT;

    private int[] imageViewOldLocationX;
    private int[] imageViewOldLocationY;
    private int[] imageViewTargetLocationX;
    private int[] imageViewTargetLocationY;

    private Random random;
    private ShapeAndColorController shapeAndColorController;
    private SmartCountDownTimer animationTimer;

    public AnimationsFragment() {
        super();
        random = new Random();
        shapeAndColorController = new ShapeAndColorController(GameDifficulty.hard);
        shapeImageViews = new AppCompatImageView[NUMBER_OF_IMAGE_VIEWS];
        imageViewOldLocationX = new int[NUMBER_OF_IMAGE_VIEWS];
        imageViewOldLocationY = new int[NUMBER_OF_IMAGE_VIEWS];
        imageViewTargetLocationX = new int[NUMBER_OF_IMAGE_VIEWS];
        imageViewTargetLocationY = new int[NUMBER_OF_IMAGE_VIEWS];
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.animations_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        getWidthAndHeightAndSetImageViewsRandomTranslationXYAndStartImageViewsAnimation();
        setImageViewsRandomResource();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(!animationTimer.isEnded()){
            animationTimer.cancel();
        }
        FragmentController.removeFragment(getActivity(),this);
    }

    private void findViews(View view) {
        parentLayout = view.findViewById(R.id.constraint_layout);
    }

    private void getWidthAndHeightAndSetImageViewsRandomTranslationXYAndStartImageViewsAnimation() {
        ViewTreeObserver observer = parentLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                WIDTH = parentLayout.getWidth();
                HEIGHT = parentLayout.getHeight();
                setImageViewsRandomLocationXY();
                startImageViewsAnimation();
                parentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private void setImageViewsRandomResource() {
        for (int index = 0; index < NUMBER_OF_IMAGE_VIEWS; index++) {
            shapeImageViews[index] = new AppCompatImageView(MyApplication.getContext());
            shapeImageViews[index].setLayoutParams(
                    new ViewGroup.LayoutParams(
                            IMAGE_VIEWS_SIZE, IMAGE_VIEWS_SIZE
                    )
            );
            parentLayout.addView(shapeImageViews[index]);
            shapeImageViews[index].setImageResource(shapeAndColorController.getRandom());
        }
    }

    private void setImageViewsRandomLocationXY() {
        if (HEIGHT == 0 || WIDTH == 0) return;
        for (int index = 0; index < NUMBER_OF_IMAGE_VIEWS; index++) {
            imageViewOldLocationY[index]=random.nextInt(
                    HEIGHT - IMAGE_VIEWS_SIZE / 2
            );
            imageViewOldLocationX[index]=random.nextInt(
                    WIDTH - IMAGE_VIEWS_SIZE / 2
            );
            shapeImageViews[index].setTranslationY(
                    imageViewOldLocationY[index]
            );
            shapeImageViews[index].setTranslationX(
                    imageViewOldLocationX[index]
            );
        }
    }

    private void startImageViewsAnimation() {
        for (int index = 0; index < NUMBER_OF_IMAGE_VIEWS; index++) {
            int height=random.nextInt(HEIGHT - IMAGE_VIEWS_SIZE / 2);
            int width=random.nextInt(WIDTH - IMAGE_VIEWS_SIZE / 2);
            imageViewTargetLocationY[index]=height;
            imageViewTargetLocationX[index]=width;
        }
        for (int index = 0; index < NUMBER_OF_IMAGE_VIEWS; index++) {
            startImageViewAnimation(index);
        }
        animationTimer=new SmartCountDownTimer(ANIMATION_DURATION,100) {
            @Override
            public void on_tick(long millisUntilFinished) {
            }

            @Override
            public void on_finish() {
                for(int index=0;index<NUMBER_OF_IMAGE_VIEWS;index++){
                    imageViewOldLocationX[index]=
                            imageViewTargetLocationX[index];
                    imageViewOldLocationY[index]=
                            imageViewTargetLocationY[index];
                }
                startImageViewsAnimation();
            }
        };
        animationTimer.startTimer();
    }

    private void startImageViewAnimation(int index){
        ObjectAnimator animatorX=ObjectAnimator.ofFloat(
                shapeImageViews[index],
                "TranslationX",
                imageViewOldLocationX[index],
                imageViewTargetLocationX[index]
        );
        ObjectAnimator animatorY=ObjectAnimator.ofFloat(
                shapeImageViews[index],
                "TranslationY",
                imageViewOldLocationY[index],
                imageViewTargetLocationY[index]
        );
        animatorX.setDuration(ANIMATION_DURATION);
        animatorY.setDuration(ANIMATION_DURATION);
        animatorX.start();
        animatorY.start();
    }
}
