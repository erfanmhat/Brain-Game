package ir.artaateam.android.braingame.App;

import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.dbPackage.DBController;

public class Data {
    private GameDifficulty gameDifficulty;
    private DBController dbController;
    private String name;
    private int gem;
    private int coin;
    private int bestScore;
    private static Data instance;

    public static Data get() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public void config() {
        dbController = new DBController();
        name = dbController.getName();
        gem = dbController.getGem();
        coin = dbController.getCoin();
        bestScore = dbController.getBestScore();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        dbController.saveName(name);
    }

    public int getGem() {
        return gem;
    }

    public void setGem(int gem) {
        this.gem = gem;
        dbController.saveGem(gem);
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
        dbController.saveCoin(coin);
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
        dbController.saveBestScore(bestScore);
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }
}
