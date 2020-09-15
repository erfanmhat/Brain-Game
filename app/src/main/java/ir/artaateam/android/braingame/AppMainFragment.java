package ir.artaateam.android.braingame;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class AppMainFragment extends Fragment {
    private ImageView settingsImageView;
    private ImageView startGame1ImageView;
    private TextView userTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_main_fragment, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
        User user=UserPreferences.getInstance(getActivity()).getUser();
        String userString="Your record : "+user.getBestScore();
        userTextView.setText(userString);
        MainActivity.startMusic(getActivity(),R.raw.music_menu,true);
    }

    @Override
    public void onPause() {
        MainActivity.stopMusic();
        super.onPause();
    }

    private void findViews(View view){
        startGame1ImageView=view.findViewById(R.id.start_game1_image_view);
        userTextView=view.findViewById(R.id.user_text_view);
        settingsImageView=view.findViewById(R.id.settings_image_view);
    }

    private void configure(){
        startGame1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.stopMusic();
                MainActivity.showGame1GameFragment(getActivity());
            }
        });
        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showSettingsFragment(getActivity());
            }
        });
    }
}
