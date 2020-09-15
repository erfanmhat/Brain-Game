package ir.artaateam.android.braingame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import ir.artaateam.android.braingame.game1.Game1GameFragment;

public class MainActivity extends AppCompatActivity {
    private static MediaPlayer musicPlayer;
    private static MediaPlayer AudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_main);

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

    public static void showShowScoreFragment(Activity activity, int scoreInt) {
        ShowScoreFragment showScoreFragment = new ShowScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("score", scoreInt);
        showScoreFragment.setArguments(bundle);

        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, showScoreFragment)
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

    public static boolean getIsMusicAllowed(Activity activity) {
        return UserPreferences.getInstance(activity).getUser().getIsMusicAllowed();
    }

    public static void setIsMusicAllowed(Activity activity, boolean musicAllowed) {
        User user = UserPreferences.getInstance(activity).getUser();
        user.setIsMusicAllowed(musicAllowed);
        UserPreferences.getInstance(activity).putUser(user);
    }

    public static void stopMusic() {
        try {
            if (musicPlayer.isPlaying()) {
                musicPlayer.stop();
                musicPlayer.release();
            }
        } catch (Exception e) {
        }
    }

    public static void startMusic(Activity activity, int res, boolean loop) {
        stopMusic();
        try {
            musicPlayer = MediaPlayer.create(activity, res);
            musicPlayer.setLooping(loop);
            musicPlayer.start();
            setMusicVolume(activity);
        } catch (Exception e) {
        }
    }

    public static void setMusicVolume(Activity activity) {
        try {
            if (MainActivity.getIsMusicAllowed(activity)) {
                musicPlayer.setVolume(1f, 1f);
            } else {
                musicPlayer.setVolume(0f, 0f);
            }
        } catch (Exception e) {
        }
    }


    public static boolean getIsAudioAllowed(Activity activity) {
        return UserPreferences.getInstance(activity).getUser().getIsAudioAllowed();
    }

    public static void setIsAudioAllowed(Activity activity, boolean AudioAllowed) {
        User user = UserPreferences.getInstance(activity).getUser();
        user.setIsAudioAllowed(AudioAllowed);
        UserPreferences.getInstance(activity).putUser(user);
    }

    public static void stopAudio() {
        try {
            if (AudioPlayer.isPlaying()) {
                AudioPlayer.stop();
                AudioPlayer.release();
            }
        } catch (Exception e) {
        }
    }

    public static void startAudio(Activity activity, int res, boolean loop) {
        stopAudio();
        try {
            AudioPlayer = MediaPlayer.create(activity, res);
            AudioPlayer.setLooping(loop);
            AudioPlayer.start();
            setAudioVolume(activity);
        } catch (Exception e) {
        }
    }

    public static void setAudioVolume(Activity activity) {
        try {
            if (MainActivity.getIsAudioAllowed(activity)) {
                AudioPlayer.setVolume(1f, 1f);
            } else {
                AudioPlayer.setVolume(0f, 0f);
            }
        } catch (Exception e) {
        }
    }
}