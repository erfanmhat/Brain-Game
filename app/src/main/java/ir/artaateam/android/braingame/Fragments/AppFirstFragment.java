package ir.artaateam.android.braingame.Fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ir.artaateam.android.braingame.App.Data;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.App.MyApplication;

//TODO add a progress bar to this fragment
public class AppFirstFragment extends Fragment {
    private boolean isFirstTimePlaying;
    private boolean isProgressing = false;

    private final static int DELAY_TIME_AFTER_END_PROGRESS = 2500;
    private final float DELAY_TIME_FOR_END_PROGRESS = 3000;

    private TextView gameNameTextView;

    private CountDownTimer appFirstFragmentTimer;

    public AppFirstFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_first_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setGameNameFont();
        checkIsFirstTimePlaying();
        startCountDownTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        removeFragment();
        if (isProgressing) {
            appFirstFragmentTimer.cancel();
        }
    }

    private void findViews(View view) {
        gameNameTextView = view.findViewById(R.id.game_name_text_view);
    }

    private void setGameNameFont() {
        Typeface font = Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_NAME_FONT_PATH
        );
        gameNameTextView.setTypeface(font);
    }

    private void checkIsFirstTimePlaying() {
        isFirstTimePlaying = "".equals(Data.getName());
    }

    private void startCountDownTimer() {
        appFirstFragmentTimer = new CountDownTimer((long) DELAY_TIME_FOR_END_PROGRESS, 100) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                startEndProgressDelay();
            }
        };
        appFirstFragmentTimer.start();
        isProgressing = true;
    }

    private void startEndProgressDelay() {
        appFirstFragmentTimer = new CountDownTimer(DELAY_TIME_AFTER_END_PROGRESS, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                showNextFragment();
                isProgressing = false;
            }
        };
        appFirstFragmentTimer.start();
    }

    private void showNextFragment() {
        removeFragment();
        if (isFirstTimePlaying) {
            FragmentController.showSingUpFragment(getActivity());
        } else {
            FragmentController.showAppMainFragment(getActivity());
        }
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
