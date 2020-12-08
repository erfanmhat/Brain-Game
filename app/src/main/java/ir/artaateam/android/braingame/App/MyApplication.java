package ir.artaateam.android.braingame.App;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import ir.artaateam.android.braingame.Controllers.MusicController;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static class main {
        public static final String GAME_MAIN_FONT_PATH = "fonts/game_main_font.otf";
        public static final String GAME_NAME_FONT_PATH = "fonts/game_name_font.otf";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Settings.get().config();
        Data.get().config();
        SQLiteStudioService.instance().start(this);
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onTerminate() {
        SQLiteStudioService.instance().stop();
        super.onTerminate();
    }
}
