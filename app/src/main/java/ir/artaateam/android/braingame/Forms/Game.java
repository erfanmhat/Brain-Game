package ir.artaateam.android.braingame.Forms;

public class Game {
    String gameLevel;

    public Game() {
    }

    public Game(String gameLevel) {
        this.gameLevel = gameLevel;
    }

    public void setGameLevel(String gameLevel) {
        this.gameLevel = gameLevel;
    }

    public String getGameLevel() {
        return gameLevel;
    }
}
