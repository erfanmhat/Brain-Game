package ir.artaateam.android.braingame;

public class User {
    private String username;
    private String nickname;
    private int bestScore;
    private boolean isMusicAllowed = true;
    private boolean isAudioAllowed = true;
    private int gem;
    private int coin;
    User() {
        username = "";
        nickname = "";
        bestScore = 0;
        gem=0;
        coin=0;
    }

    public int getGem() {
        return gem;
    }

    public void setGem(int gem) {
        this.gem = gem;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setIsMusicAllowed(boolean musicAllowed) {
        isMusicAllowed = musicAllowed;
    }

    public boolean getIsMusicAllowed() {
        return isMusicAllowed;
    }

    public void setIsAudioAllowed(boolean AudioAllowed) {
        isAudioAllowed = AudioAllowed;
    }

    public boolean getIsAudioAllowed() {
        return isAudioAllowed;
    }
}
