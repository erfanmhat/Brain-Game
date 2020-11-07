package ir.artaateam.android.braingame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import ir.artaateam.android.braingame.Fragments.AboutUsFragment;
import ir.artaateam.android.braingame.Fragments.AppFirstFragment;
import ir.artaateam.android.braingame.Fragments.AppMainFragment;
import ir.artaateam.android.braingame.Fragments.HowToDoFragment;
import ir.artaateam.android.braingame.Fragments.ItemsAnimationFragment;
import ir.artaateam.android.braingame.Fragments.SettingsFragment;
import ir.artaateam.android.braingame.Fragments.ShowScoreFragment;
import ir.artaateam.android.braingame.Fragments.SingUpFragment;
import ir.artaateam.android.braingame.game1.Game1GameFragment;

public class MainActivity extends AppCompatActivity {
    private static MediaPlayer musicPlayer;
    private static MediaPlayer AudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        showGameFirstFragment(MainActivity.this);
    }

    public static void showGameFirstFragment(Activity activity) {
        AppFirstFragment appFirstFragment = new AppFirstFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, appFirstFragment)
                .commit();
    }

    public static void showAppMainFragment(Activity activity) {
        AppMainFragment appMainFragment = new AppMainFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, appMainFragment)
                .commit();
    }

    public static void showGame1GameFragment(Activity activity) {
        Game1GameFragment game1GameFragment = new Game1GameFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, game1GameFragment)
                .commit();
    }

    public static void showSingUpFragment(Activity activity) {
        SingUpFragment singUpFragment = new SingUpFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, singUpFragment)
                .commit();
    }

    public static void showShowScoreFragment(Activity activity, int scoreInt, boolean isNewBestScore) {
        ShowScoreFragment showScoreFragment = new ShowScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("score", scoreInt);
        bundle.putBoolean("isNewBestScore", isNewBestScore);
        showScoreFragment.setArguments(bundle);

        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, showScoreFragment)
                .commit();
    }

    public static void showAboutUsFragment(Activity activity) {
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, aboutUsFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showHowToDoFragment(Activity activity) {
        HowToDoFragment howToDoFragment = new HowToDoFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, howToDoFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showSettingsFragment(Activity activity) {
        SettingsFragment settingsFragment = new SettingsFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.settings_frame, settingsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showItemsAnimationFragment() {
        ItemsAnimationFragment itemsAnimationFragment = new ItemsAnimationFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, itemsAnimationFragment, null)
                .addToBackStack(null)
                .commit();
    }

    public static boolean getIsMusicAllowed() {
        return UserPreferences.getInstance().getUser().getIsMusicAllowed();
    }

    public static void setIsMusicAllowed(boolean musicAllowed) {
        User user = UserPreferences.getInstance().getUser();
        user.setIsMusicAllowed(musicAllowed);
        UserPreferences.getInstance().putUser(user);
    }

    public static void stopMusic() {
        try {
            if (musicPlayer.isPlaying()) {
                musicPlayer.stop();
                musicPlayer.release();
            }
        } catch (Exception ignored) {
        }
    }

    public static void startMusic(Activity activity, int res, boolean loop) {
        stopMusic();
        try {
            musicPlayer = MediaPlayer.create(activity, res);
            musicPlayer.setLooping(loop);
            musicPlayer.start();
            setMusicVolume();
        } catch (Exception ignored) {
        }
    }

    public static void setMusicVolume() {
        try {
            if (MainActivity.getIsMusicAllowed()) {
                musicPlayer.setVolume(1f, 1f);
            } else {
                musicPlayer.setVolume(0f, 0f);
            }
        } catch (Exception ignored) {
        }
    }


    public static boolean getIsAudioAllowed() {
        return UserPreferences.getInstance().getUser().getIsAudioAllowed();
    }

    public static void setIsAudioAllowed(boolean AudioAllowed) {
        User user = UserPreferences.getInstance().getUser();
        user.setIsAudioAllowed(AudioAllowed);
        UserPreferences.getInstance().putUser(user);
    }

    public static void stopAudio() {
        try {
            if (AudioPlayer.isPlaying()) {
                AudioPlayer.stop();
                AudioPlayer.release();
            }
        } catch (Exception ignored) {
        }
    }

    public static void startAudio(Activity activity, int res, boolean loop) {
        stopAudio();
        try {
            AudioPlayer = MediaPlayer.create(activity, res);
            AudioPlayer.setLooping(loop);
            AudioPlayer.start();
            setAudioVolume();
        } catch (Exception ignored) {
        }
    }

    public static void setAudioVolume() {
        try {
            if (MainActivity.getIsAudioAllowed()) {
                AudioPlayer.setVolume(1f, 1f);
            } else {
                AudioPlayer.setVolume(0f, 0f);
            }
        } catch (Exception ignored) {
        }
    }
}