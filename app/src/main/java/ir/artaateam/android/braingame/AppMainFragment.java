package ir.artaateam.android.braingame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class AppMainFragment extends Fragment {
    private Button settingsImageView;
    private Button startGame1ImageView;
    private TextView userTextView;
    private TextView gemTextView;
    private TextView coinTextView;
    private Button storeButton;




    public AppMainFragment() {
        super();
    }

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
        User user=UserPreferences.getInstance().getUser();
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
        settingsImageView=view.findViewById(R.id.settings_button);
        gemTextView= view.findViewById(R.id.gem_text_view);
        coinTextView= view.findViewById(R.id.coin_text_view);
        storeButton= view.findViewById(R.id.store_button);
    }

    private void configure(){
        int gemInt=UserPreferences.getInstance().getUser().getGem();
        int coinInt=UserPreferences.getInstance().getUser().getCoin();
        gemTextView.setText(String.valueOf(gemInt));
        coinTextView.setText(String.valueOf(coinInt));
        startGame1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.stopMusic();
                removeFragment();
                MainActivity.showGame1GameFragment(getActivity());
            }
        });
        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showSettingsFragment(getActivity());
            }
        });
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showStoreFragments();
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
