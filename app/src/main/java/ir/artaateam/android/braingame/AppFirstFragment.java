package ir.artaateam.android.braingame;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class AppFirstFragment extends Fragment {
    boolean isFirstTimePlaying;
    String progressString = "";

    TextView progressTextView;

    private CountDownTimer appFirstFragmentTimer;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_first_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        checkIsFirstTimePlaying();
        startCountDownTimer();
    }

    private void findViews(View view) {
        progressTextView = view.findViewById(R.id.progress_text_view);
    }

    private void checkIsFirstTimePlaying() {
        User user = UserPreferences.getInstance().getUser();
        isFirstTimePlaying = (user.getUsername().equals(""));
    }

    private void startCountDownTimer() {
        appFirstFragmentTimer = new CountDownTimer(MainUtilValues.DELAY_TIME_FOR_GAME_FIRST_FRAGMENT, 200) {
            @Override
            public void onTick(long l) {
                progressUpdater();
            }

            @Override
            public void onFinish() {
                showNextFragment();
            }
        };
        appFirstFragmentTimer.start();
    }

    private void progressUpdater() {
        switch (progressString) {
            case "": {
                progressString = ".";
                break;
            }
            case ".": {
                progressString = "..";
                break;
            }
            case "..": {
                progressString = "...";
                break;
            }
            case "...": {
                progressString = "";
                break;
            }
        }
        progressTextView.setText(progressString);
    }

    private void showNextFragment() {
        removeFragment();
        if (isFirstTimePlaying) {
            MainActivity.showSingUpFragment(getActivity());
        } else {
            MainActivity.showAppMainFragment(getActivity());
        }
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
