package ir.artaateam.android.braingame.Controllers;

import android.app.Activity;

import ir.artaateam.android.braingame.App.App;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.SmartCountDownTimer;

public class GameMusicController {
    private static SmartCountDownTimer smartCountDownTimer;

    private static final long GAME_MUSIC_PART_1_DURATION = 15360;
    private static final long GAME_MUSIC_PART_2_DURATION = 84480;
    private static final long GAME_MUSIC_PART_3_DURATION = 1503;
    //private static final long GAME_MUSIC_PART_4_DURATION = 310000;

    private static int numberOfMusicPart;

    public static void startGameMusic(Activity activity) {
        numberOfMusicPart = 1;
        nextMusic(activity);
    }

    private static void nextMusic(Activity activity) {
        App.l("next music "+numberOfMusicPart);
        if (numberOfMusicPart == 1) {
            MusicController.startMusic(activity, R.raw.game_music_part_1, false);
            setCountDownForNextMusic(activity, GAME_MUSIC_PART_1_DURATION);
        } else if (numberOfMusicPart == 2) {
            MusicController.startMusic(activity, R.raw.game_music_part_2, false);
            setCountDownForNextMusic(activity, GAME_MUSIC_PART_2_DURATION);
        } else if (numberOfMusicPart == 3) {
            MusicController.startMusic(activity, R.raw.game_music_part_3, false);
            setCountDownForNextMusic(activity, GAME_MUSIC_PART_3_DURATION);
        } else if (numberOfMusicPart == 4) {
            MusicController.startMusic(activity, R.raw.game_music_part_4, true);
        }
    }

    private static void setCountDownForNextMusic(Activity activity, long duration) {
        smartCountDownTimer = new SmartCountDownTimer(duration, 100) {
            @Override
            public void on_tick(long millisUntilFinished) {
            }

            @Override
            public void on_finish() {
                numberOfMusicPart++;
                nextMusic(activity);
            }
        };
        smartCountDownTimer.startTimer();
    }

    public static void stopGameMusic(String fun){
        App.l(fun+" stop Game Music");
        MusicController.stopMusic();
        if(!smartCountDownTimer.isEnded()){
            smartCountDownTimer.cancel();
        }
    }

    public static void pause(){
        stopGameMusic("pause");
        // TODO most change
    }

    public static void restart(){
        //TODO continue music from pause time (seekTo)
    }
}
