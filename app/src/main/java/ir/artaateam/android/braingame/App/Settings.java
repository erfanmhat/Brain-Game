package ir.artaateam.android.braingame.App;

import ir.artaateam.android.braingame.Controllers.MusicController;
import ir.artaateam.android.braingame.SettingsPreferences;

public class Settings {
    private boolean isMusicAllowed;
    private boolean isAudioAllowed;
    private boolean isSettingOpen = false;
    private static Settings instance;

    public Settings() {
        isMusicAllowed=true;
        isAudioAllowed=true;
    }

    public void config() {
        isAudioAllowed =
                SettingsPreferences
                        .getInstance().getSettings()
                        .getIsAudioAllowed();
        isMusicAllowed =
                SettingsPreferences
                        .getInstance().getSettings()
                        .getIsMusicAllowed();
    }

    public static Settings get() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public void setIsMusicAllowed(boolean musicAllowed) {
        isMusicAllowed = musicAllowed;
        SettingsPreferences.getInstance().putSettings(instance);
        MusicController.setMusicVolume();
    }

    public boolean getIsMusicAllowed() {
        return isMusicAllowed;
    }

    public void setIsAudioAllowed(boolean audioAllowed) {
        isAudioAllowed = audioAllowed;
        SettingsPreferences.getInstance().putSettings(instance);
        MusicController.setAudioVolume();
    }

    public boolean getIsAudioAllowed() {
        return isAudioAllowed;
    }

    public boolean getIsSettingOpen() {
        return isSettingOpen;
    }

    public void setIsSettingOpen(boolean settingOpen) {
        isSettingOpen = settingOpen;
    }
}
