package ir.artaateam.android.braingame.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ir.artaateam.android.braingame.App.Data;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Controllers.MusicController;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.Activitys.StoreActivity;
import ir.artaateam.android.braingame.App.MyApplication;

import static ir.artaateam.android.braingame.Enums.GameDifficulty.*;


public class AppMainFragment extends Fragment {
    private ImageView settingsImageView;
    private TextView gemTextView;
    private TextView coinTextView;
    private ImageView storeImageView;

    private TextView gameLevel1;
    private TextView gameLevel2;
    private TextView gameLevel3;
    private TextView gameLevel4;

    //TODO add user best scores button and new fragment for scores :)

    public AppMainFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
        MusicController.startMusic(getActivity(), R.raw.music_menu, true);
    }

    @Override
    public void onPause() {
        MusicController.stopMusic();
        removeFragment();
        super.onPause();
    }

    private void findViews(View view) {
        settingsImageView = view.findViewById(R.id.settings_button);
        gemTextView = view.findViewById(R.id.gem_text_view);
        coinTextView = view.findViewById(R.id.coin_text_view);
        storeImageView = view.findViewById(R.id.store_image_view);
        gameLevel1 = view.findViewById(R.id.game_level_1);
        gameLevel2 = view.findViewById(R.id.game_level_2);
        gameLevel3 = view.findViewById(R.id.game_level_3);
        gameLevel4 = view.findViewById(R.id.game_level_4);
    }

    private void configure() {
        setFont();
        configureGameLevelButtons();
        int gemInt = Data.getGem();
        int coinInt = Data.getCoin();
        String gemString = gemInt + " ";
        String coinString = coinInt + " ";
        gemTextView.setText(gemString);
        coinTextView.setText(coinString);
        settingsImageView.setOnClickListener(view -> FragmentController.showSettingsFragment(getActivity()));
        storeImageView.setOnClickListener(v -> startActivity(new Intent(getActivity(), StoreActivity.class)));
    }

    private void configureGameLevelButtons(){
        gameLevel1.setText(instructions.toString());
        gameLevel2.setText(easy.toString());
        gameLevel3.setText(normal.toString());
        gameLevel4.setText(hard.toString());

        gameLevel1.setOnClickListener(view -> {
            Data.setGameDifficulty(instructions);
            MusicController.stopMusic();
            FragmentController.showGame1GameFragment(getActivity());
        });
        gameLevel2.setOnClickListener(view -> {
            Data.setGameDifficulty(easy);
            MusicController.stopMusic();
            FragmentController.showGame1GameFragment(getActivity());
        });
        gameLevel3.setOnClickListener(view -> {
            Data.setGameDifficulty(normal);
            MusicController.stopMusic();
            FragmentController.showGame1GameFragment(getActivity());
        });
        gameLevel4.setOnClickListener(view -> {
            Data.setGameDifficulty(hard);
            MusicController.stopMusic();
            FragmentController.showGame1GameFragment(getActivity());
        });
        Typeface font=Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_MAIN_FONT_PATH
        );
        gameLevel1.setTypeface(font);
        gameLevel2.setTypeface(font);
        gameLevel3.setTypeface(font);
        gameLevel4.setTypeface(font);
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(
                MyApplication.getContext().getAssets(),
                MyApplication.main.GAME_MAIN_FONT_PATH
        );
        gemTextView.setTypeface(font);
        coinTextView.setTypeface(font);
    }

    private void removeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
