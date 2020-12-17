package ir.artaateam.android.braingame;

import android.os.CountDownTimer;

public abstract class SmartCountDownTimer extends  CountDownTimer {
    boolean isStarted;
    boolean isEnded;
    boolean isRunning;
    boolean isStop;

    public SmartCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        isStarted=false;
        isEnded=false;
        isRunning=false;
        isStop=false;
    }

    public void startTimer(){
        isStarted=true;
        isRunning=true;
        super.start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(isStop)return;
        this.on_tick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        if(isStop)return;
        isRunning=false;
        isEnded=true;
        this.on_finish();
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

    public void stop(){
        super.cancel();
        isStop=true;
        isRunning=false;
        isEnded=true;
    }

    public abstract void on_tick(long millisUntilFinished);
    public abstract void on_finish();
}
