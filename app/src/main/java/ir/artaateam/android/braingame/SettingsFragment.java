package ir.artaateam.android.braingame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class SettingsFragment extends Fragment {
    private ImageView musicImageView;
    private ImageView audioImageView;
    private ImageView closeImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setImageViewsSrc();
        configure();
    }

    private void findViews(View view) {
        musicImageView = view.findViewById(R.id.music_image_view_button);
        audioImageView = view.findViewById(R.id.audio_image_view_button);
        closeImageView = view.findViewById(R.id.close_image_view_button);
    }

    private void setImageViewsSrc() {
        setMusicImageView();
        setAudioImageView();
    }

    private void setMusicImageView() {
        if (MainActivity.getIsMusicAllowed(getActivity())) {
            musicImageView.setImageResource(R.drawable.music_is_on);
        } else {
            musicImageView.setImageResource(R.drawable.music_is_off);
        }
    }

    private void setAudioImageView() {
        if (MainActivity.getIsAudioAllowed(getActivity())) {
            audioImageView.setImageResource(R.drawable.audio_is_on);
        } else {
            audioImageView.setImageResource(R.drawable.audio_is_off);
        }
    }

    private void configure() {
        musicImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setIsMusicAllowed(getActivity(), !(MainActivity.getIsMusicAllowed(getActivity())));
                setMusicImageView();
                MainActivity.setMusicVolume(getActivity());
            }
        });

        audioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setIsAudioAllowed(getActivity(), !(MainActivity.getIsAudioAllowed(getActivity())));
                setAudioImageView();
                MainActivity.setAudioVolume(getActivity());
            }
        });

        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment();
            }
        });
    }

    private void closeFragment() {
        getActivity()
                .getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}

