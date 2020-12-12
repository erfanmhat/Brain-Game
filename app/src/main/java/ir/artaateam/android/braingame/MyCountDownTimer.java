package ir.artaateam.android.braingame;

import android.os.CountDownTimer;

public abstract class MyCountDownTimer extends  CountDownTimer {
    boolean isStarted=false;
    boolean isEnded=false;
    boolean isRunning=false;

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }


    public void startTimer(){
        isStarted=true;
        isRunning=true;
        super.start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        finish();
        isRunning=false;
        isEnded=true;
    }

    public boolean isEnded(){
        return isEnded;
    }

    public boolean isStarted(){
        return isStarted;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public abstract void tick(long millisUntilFinished);
    public abstract void finish();
}
