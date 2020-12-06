package ir.artaateam.android.braingame;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ir.artaateam.android.braingame.App.Settings;
import ir.artaateam.android.braingame.App.MyApplication;

public class SettingsPreferences {
    private final static String PREFERENCES_NAME="SETTINGS_PREFERENCES";
    private static final String KEY="SETTINGS";
    private SharedPreferences sharedPreferences;
    private static SettingsPreferences instance = null;

    private SettingsPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SettingsPreferences getInstance() {
        if (instance == null) {
            instance = new SettingsPreferences(MyApplication.getContext());
        }
        return instance;
    }

    public void putSettings(Settings settings) {
        Gson gson = new Gson();
        String settingsJson = gson.toJson(settings, Settings.class);
        sharedPreferences.edit().putString(KEY, settingsJson).apply();
    }

    public Settings getSettings() {
        Gson gson = new Gson();
        String settingsJson = sharedPreferences.getString(KEY, null);
        if (settingsJson == null) {
            return new Settings();
        }
        return gson.fromJson(settingsJson, Settings.class);
    }
}
