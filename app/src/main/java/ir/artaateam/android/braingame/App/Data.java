package ir.artaateam.android.braingame.App;

import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.dbPackage.DBController;

public class Data {
    private static GameDifficulty gameDifficulty;
    private static DBController dbController;
    private static String name;
    private static int gem;
    private static int coin;
    private static int bestScore;

    public static void config(){
        dbController=new DBController();
        name= dbController.getName();
        gem=dbController.getGem();
        coin=dbController.getCoin();
        bestScore=dbController.getBestScore();
    }


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Data.name = name;
        dbController.saveName(name);
    }

    public static int getGem() {
        return gem;
    }

    public static void setGem(int gem) {
        Data.gem = gem;
        dbController.saveGem(gem);
    }

    public static int getCoin() {
        return coin;
    }

    public static void setCoin(int coin) {
        Data.coin = coin;
        dbController.saveCoin(coin);
    }

    public static int getBestScore() {
        return bestScore;
    }

    public static void setBestScore(int bestScore) {
        Data.bestScore = bestScore;
        dbController.saveBestScore(bestScore);
    }

    public static GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public static void setGameDifficulty(GameDifficulty gameDifficulty) {
        Data.gameDifficulty = gameDifficulty;
    }
}
