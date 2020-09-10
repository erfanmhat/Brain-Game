package ir.artaateam.android.braingame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import ir.artaateam.android.braingame.game1.Game1GameFragment;

public class MainActivity extends AppCompatActivity {
    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

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

    public static void showAppMainFragment(Activity activity){
        AppMainFragment appMainFragment=new AppMainFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame,appMainFragment)
                .commit();
    }

    public static void showGame1GameFragment(Activity activity){
        Game1GameFragment game1GameFragment=new Game1GameFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame,game1GameFragment)
                .commit();
    }

    public static void showSingUpFragment(Activity activity){
        SingUpFragment singUpFragment=new SingUpFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame,singUpFragment)
                .commit();
    }

    public static void showShowScoreFragment(Activity activity,int scoreInt){
        ShowScoreFragment showScoreFragment=new ShowScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("score", scoreInt);
        showScoreFragment.setArguments(bundle);

        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame,showScoreFragment)
                .commit();
    }

    public static void stopMusic(){
        try {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }catch (Exception e){}
    }

    public static void startMusic(Context context,int res,boolean loop){
        stopMusic();
        try{
            mediaPlayer = MediaPlayer.create(context, res);
            mediaPlayer.setLooping(loop);
            mediaPlayer.start();
        }catch (Exception e){}
    }
}