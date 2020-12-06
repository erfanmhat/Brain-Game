package ir.artaateam.android.braingame.dbPackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ir.artaateam.android.braingame.App.MyApplication;

public class DBController extends SQLiteOpenHelper {
    public DBController() {
        super(MyApplication.getContext(), DBValues.DB_NAME, null, 1);
        createTablesPlayer();
        if (isGameOpenedForFirstTime()) {
            savePlayerFirstData();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTablesPlayer() {
        String queryString =
                " CREATE TABLE IF NOT EXISTS " +
                        DBValues.Tables.PLAYER_TABLE + " ( " +
                        DBValues.Tables.PlayerTableColumns.NAME + " VARCHAR(20) , " +
                        DBValues.Tables.PlayerTableColumns.GEM + " INTEGER , " +
                        DBValues.Tables.PlayerTableColumns.COIN + " INTEGER , " +
                        DBValues.Tables.PlayerTableColumns.BEST_SCORE + " INTEGER " +
                        " ); ";
        this.getWritableDatabase().execSQL(queryString);
    }

    private void savePlayerFirstData() {
        ContentValues value = new ContentValues();
        value.put(DBValues.Tables.PlayerTableColumns.NAME, "");
        value.put(DBValues.Tables.PlayerTableColumns.COIN, 1000);
        value.put(DBValues.Tables.PlayerTableColumns.GEM, 100);
        value.put(DBValues.Tables.PlayerTableColumns.BEST_SCORE, 0);

        this.getWritableDatabase().insert(
                DBValues.Tables.PLAYER_TABLE,
                null,
                value);
    }

    public boolean isGameOpenedForFirstTime() {
        String queryString = " SELECT * FROM " + DBValues.Tables.PLAYER_TABLE;
        Cursor cursor = this.getReadableDatabase().rawQuery(queryString, null);
        int count = cursor.getCount();
        cursor.close();
        return count == 0;
    }

    public String getName() {
        String nameString;
        String queryString =
                " SELECT " +
                        DBValues.Tables.PlayerTableColumns.NAME +
                        " FROM " +
                        DBValues.Tables.PLAYER_TABLE;
        Cursor cursor = this.getReadableDatabase().rawQuery(queryString, null);
        cursor.moveToFirst();
        nameString = cursor.getString(cursor.getColumnIndex(DBValues.Tables.PlayerTableColumns.NAME));
        cursor.close();
        return nameString;
    }

    public void saveName(String name) {
        String queryString =
                " UPDATE " + DBValues.Tables.PLAYER_TABLE +
                        " SET " + DBValues.Tables.PlayerTableColumns.NAME +
                        " = \"" + name + "\" ; ";
        this.getWritableDatabase().execSQL(queryString);
    }

    public int getGem() {
        int gemInt;
        String queryString =
                " SELECT " +
                        DBValues.Tables.PlayerTableColumns.GEM +
                        " FROM " +
                        DBValues.Tables.PLAYER_TABLE;
        Cursor cursor = this.getReadableDatabase().rawQuery(queryString, null);
        cursor.moveToFirst();
        gemInt = cursor.getInt(cursor.getColumnIndex(DBValues.Tables.PlayerTableColumns.GEM));
        cursor.close();
        return gemInt;
    }

    public void saveGem(int gem) {
        String queryString =
                " UPDATE " + DBValues.Tables.PLAYER_TABLE +
                        " SET " + DBValues.Tables.PlayerTableColumns.GEM +
                        " = " + gem + " ; ";
        this.getWritableDatabase().execSQL(queryString);
    }

    public int getCoin() {
        int coinInt;
        String queryString =
                " SELECT " +
                        DBValues.Tables.PlayerTableColumns.COIN +
                        " FROM " +
                        DBValues.Tables.PLAYER_TABLE;
        Cursor cursor = this.getReadableDatabase().rawQuery(queryString, null);
        cursor.moveToFirst();
        coinInt = cursor.getInt(cursor.getColumnIndex(DBValues.Tables.PlayerTableColumns.COIN));
        cursor.close();
        return coinInt;
    }

    public void saveCoin(int coin) {
        String queryString =
                " UPDATE " + DBValues.Tables.PLAYER_TABLE +
                        " SET " + DBValues.Tables.PlayerTableColumns.COIN +
                        " = " + coin + " ; ";
        this.getWritableDatabase().execSQL(queryString);
    }

    public int getBestScore() {
        int bestScore;
        String queryString =
                " SELECT " +
                        DBValues.Tables.PlayerTableColumns.BEST_SCORE +
                        " FROM " +
                        DBValues.Tables.PLAYER_TABLE;
        Cursor cursor = this.getReadableDatabase().rawQuery(queryString, null);
        cursor.moveToFirst();
        bestScore = cursor.getInt(cursor.getColumnIndex(DBValues.Tables.PlayerTableColumns.BEST_SCORE));
        cursor.close();
        return bestScore;
    }

    public void saveBestScore(int bestScore) {
        String queryString =
                " UPDATE " + DBValues.Tables.PLAYER_TABLE +
                        " SET " + DBValues.Tables.PlayerTableColumns.BEST_SCORE +
                        " = " + bestScore + " ; ";
        this.getWritableDatabase().execSQL(queryString);
    }
}
