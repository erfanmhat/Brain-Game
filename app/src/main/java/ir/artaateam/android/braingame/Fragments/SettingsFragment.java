package ir.artaateam.android.braingame.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import ir.artaateam.android.braingame.MainActivity;
import ir.artaateam.android.braingame.R;

@SuppressLint("ValidFragment")
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
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setAudioAndMusicImageViewsSrc();
        configure();
    }

    private void findViews(View view) {
        musicImageView = view.findViewById(R.id.music_image_view_button);
        audioImageView = view.findViewById(R.id.audio_image_view_button);
        closeImageView = view.findViewById(R.id.close_image_view_button);
        aboutUsTextView=view.findViewById(R.id.about_us_text_view);
        howToDoTextView=view.findViewById(R.id.how_to_do_text_view);
        quitTextView=view.findViewById(R.id.quit_text_view);
    }

    private void setAudioAndMusicImageViewsSrc() {
        setMusicImageView();
        setAudioImageView();
    }

    private void setMusicImageView() {
        if (MainActivity.getIsMusicAllowed()) {
            musicImageView.setImageResource(R.drawable.music_is_on);
        } else {
            musicImageView.setImageResource(R.drawable.music_is_off);
        }
    }

    private void setAudioImageView() {
        if (MainActivity.getIsAudioAllowed()) {
            audioImageView.setImageResource(R.drawable.audio_is_on);
        } else {
            audioImageView.setImageResource(R.drawable.audio_is_off);
        }
    }

    private void configure() {
        musicImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setIsMusicAllowed(!(MainActivity.getIsMusicAllowed()));
                setMusicImageView();
                MainActivity.setMusicVolume();
            }
        });

        audioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setIsAudioAllowed(!(MainActivity.getIsAudioAllowed()));
                setAudioImageView();
                MainActivity.setAudioVolume();
            }
        });

        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });

        aboutUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
                MainActivity.showAboutUsFragment(getActivity());
            }
        });

        howToDoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
                MainActivity.showHowToDoFragment(getActivity());
            }
        });

        quitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}

