package ir.artaateam.android.braingame.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;


import ir.artaateam.android.braingame.App.Settings;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.App.MyApplication;

public class SettingsFragment extends Fragment {
    private ImageView musicImageView;
    private ImageView audioImageView;
    private ImageView closeImageView;
    private TextView aboutUsTextView;
    private TextView howToDoTextView;
    private TextView quitTextView;

    public SettingsFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (Settings.get().getIsSettingOpen()) {
            return null;
        } else {
            Settings.get().setIsSettingOpen(true);
            return inflater.inflate(R.layout.settings_fragment, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
    }

    @Override
    public void onPause() {
        super.onPause();
        removeFragment();
    }

    private void findViews(View view) {
        musicImageView = view.findViewById(R.id.music_image_view_button);
        audioImageView = view.findViewById(R.id.audio_image_view_button);
        closeImageView = view.findViewById(R.id.close_image_view_button);
        aboutUsTextView = view.findViewById(R.id.about_us_text_view);
        howToDoTextView = view.findViewById(R.id.how_to_do_text_view);
        quitTextView = view.findViewById(R.id.quit_text_view);
    }

    private void configure() {
        setFont();
        setMusicImageView();
        setAudioImageView();

        musicImageView.setOnClickListener(view -> {
            boolean isMusicAllowed = Settings.get().getIsMusicAllowed();
            Settings.get().setIsMusicAllowed(!isMusicAllowed);
            setMusicImageView();
        });

        audioImageView.setOnClickListener(view -> {
            boolean isAudioAllowed = Settings.get().getIsAudioAllowed();
            Settings.get().setIsAudioAllowed(!isAudioAllowed);
            setAudioImageView();
        });

        closeImageView.setOnClickListener(view -> removeFragment());

        aboutUsTextView.setOnClickListener(view -> {
            removeFragment();
            FragmentController.showAboutUsFragment(getActivity());
        });

        howToDoTextView.setOnClickListener(view -> {
            removeFragment();
            FragmentController.showHowToDoFragment(getActivity());
        });

        quitTextView.setOnClickListener(view -> getActivity().finish());
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_MAIN_FONT_PATH
        );
        aboutUsTextView.setTypeface(font);
        howToDoTextView.setTypeface(font);
        quitTextView.setTypeface(font);
    }

    private void setMusicImageView() {
        if (Settings.get().getIsMusicAllowed()) {
            musicImageView.setImageResource(R.drawable.music_is_on);
        } else {
            musicImageView.setImageResource(R.drawable.music_is_off);
        }
    }

    private void setAudioImageView() {
        if (Settings.get().getIsAudioAllowed()) {
            audioImageView.setImageResource(R.drawable.audio_is_on);
        } else {
            audioImageView.setImageResource(R.drawable.audio_is_off);
        }
    }

    private void removeFragment() {
        Settings.get().setIsSettingOpen(false);
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}

