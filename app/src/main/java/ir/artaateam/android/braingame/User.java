package ir.artaateam.android.braingame;

public class User {
    String username;
    String nickname;
    int bestScore;

    User() {
        username = "";
        nickname = "";
        bestScore = 0;
    }

    User(String username, String nickname, int bestScore) {
        this.username=username;
        this.nickname=nickname;
        this.bestScore=bestScore;
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
}
