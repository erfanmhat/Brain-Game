package ir.artaateam.android.braingame;

public class User {
    private String username;
    private String nickname;
    private int bestScore;
    private boolean isMusicAllowed = true;
    private boolean isAudioAllowed = true;

    User() {
        username = "";
        nickname = "";
        bestScore = 0;
    }

    User(String username, String nickname, int bestScore) {
        this.username = username;
        this.nickname = nickname;
        this.bestScore = bestScore;
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
