package ir.artaateam.android.braingame.Controllers;

import android.app.Activity;
import android.media.MediaPlayer;

import ir.artaateam.android.braingame.App.Settings;

public class MusicController {
    private static MediaPlayer musicPlayer;
    private static MediaPlayer audioPlayer;

    public static void stopMusic() {
        try {
            if (musicPlayer.isPlaying()) {
                musicPlayer.stop();
                musicPlayer.release();
            }
        } catch (Exception ignored) {
        }
    }

    public static void stopAudio() {
        try {
            if (audioPlayer.isPlaying()) {
                audioPlayer.stop();
                audioPlayer.release();
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

    public static void startAudio(Activity activity, int res) {
        stopAudio();
        try {
            audioPlayer = MediaPlayer.create(activity, res);
            audioPlayer.setLooping(false);
            audioPlayer.start();
            setAudioVolume();
        } catch (Exception ignored) {
        }
    }

    public static void setMusicVolume() {
        try {
            if (getMusicAllowed()) {
                musicPlayer.setVolume(1f, 1f);
            } else {
                musicPlayer.setVolume(0f, 0f);
            }
        } catch (Exception ignored) {
        }
    }

    public static void setAudioVolume() {
        try {
            if (getAudioAllowed()) {
                audioPlayer.setVolume(1f, 1f);
            } else {
                audioPlayer.setVolume(0f, 0f);
            }
        } catch (Exception ignored) {
        }
    }

    protected static boolean getMusicAllowed() {
        return Settings.get().getIsMusicAllowed();
    }

    protected static boolean getAudioAllowed() {
        return Settings.get().getIsAudioAllowed();
    }
}