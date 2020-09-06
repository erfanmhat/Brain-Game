package ir.artaateam.android.braingame;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AppFirstFragment extends Fragment {
    private boolean isFirstTime;
    private boolean isLoadingTimerStarted = false;
    private boolean isPaused = false;

    private ImageView brainTrimImageView;
    private TextView gameNameTextView;
    private TextView waitTextView;

    private CountDownTimer loadingTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_first_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        startLoading();
        User user = UserPreferences.getInstance(getActivity()).getUser();
        isFirstTime = (user == null);
    }

    @Override
    public void onPause() {
        super.onPause();
        isPaused = true;
        if (isLoadingTimerStarted) {
            loadingTimer.cancel();
        }
    }

    private void findViews(View view) {
        brainTrimImageView = view.findViewById(R.id.brain_trim_image_view);
        waitTextView = view.findViewById(R.id.wait_text_view);
    }

    private void startLoading() {
        waitTextView.setText("");
        loadingTimer = new CountDownTimer(MainUtilValues.DELAY_TIME_FOR_GAME_FIRST_FRAGMENT, 500) {
            @Override
            public void onTick(long l) {
                if (!isPaused) {
                    String str = (String) waitTextView.getText();
                    switch (str) {
                        case "":
                            waitTextView.setText(".");
                            break;
                        case ".":
                            waitTextView.setText("..");
                            break;
                        case "..":
                            waitTextView.setText("...");
                            break;
                        case "...":
                            waitTextView.setText("");
                            break;
                    }
                }
            }

            @Override
            public void onFinish() {
                if (isFirstTime) {
                    showSingInFragment();
                    return;
                }
                showGameMainFragment();
            }
        };
        loadingTimer.start();
        isLoadingTimerStarted = true;
    }

    private void showGameMainFragment() {
        AppMainFragment appMainFragment = new AppMainFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, appMainFragment)
                .commit();
    }

    private void showSingInFragment() {
        SingUpFragment singUpFragment = new SingUpFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, singUpFragment)
                .commit();
    }
}
