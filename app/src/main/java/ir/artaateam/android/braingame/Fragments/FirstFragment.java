package ir.artaateam.android.braingame.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.artaateam.android.braingame.App.App;
import ir.artaateam.android.braingame.App.Data;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.App.MyApplication;
import ir.artaateam.android.braingame.SmartCountDownTimer;

//TODO add a progress bar to this fragment
public class FirstFragment extends Fragment {
    private boolean isFirstTimePlaying;

    private final static int DELAY_TIME_AFTER_END_PROGRESS = 2500;
    private final float DELAY_TIME_FOR_END_PROGRESS = 3000;

    private TextView gameNameTextView;

    private SmartCountDownTimer appFirstFragmentTimer;

    public FirstFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setGameNameFont();
        checkIsFirstTimePlaying();
        startCountDownTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!appFirstFragmentTimer.isEnded()) {
            appFirstFragmentTimer.cancel();
        }
        FragmentController.removeFragment(getActivity(),this);
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
        isFirstTimePlaying = "".equals(Data.get().getName());
    }

    private void startCountDownTimer() {
        appFirstFragmentTimer = new SmartCountDownTimer((long) DELAY_TIME_FOR_END_PROGRESS, 100) {
            @Override
            public void on_tick(long l) {
            }

            @Override
            public void on_finish() {
                startEndProgressDelay();
            }
        };
        appFirstFragmentTimer.startTimer();
    }

    private void startEndProgressDelay() {
        appFirstFragmentTimer = new SmartCountDownTimer(DELAY_TIME_AFTER_END_PROGRESS, 100) {
            @Override
            public void on_tick(long millisUntilFinished) {
            }

            @Override
            public void on_finish() {
                showNextFragment();
            }
        };
        appFirstFragmentTimer.startTimer();
    }

    private void showNextFragment() {
        FragmentController.removeFragment(getActivity(),this);
        if(getActivity()==null){
            App.l("null activity error");
            return;
        }
        if (isFirstTimePlaying) {
            FragmentController.showSingUpFragment(getActivity());
        } else {
            FragmentController.showMainFragment(getActivity());
        }
    }
}
