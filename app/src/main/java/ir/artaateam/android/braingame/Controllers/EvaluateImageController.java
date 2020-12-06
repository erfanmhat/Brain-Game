package ir.artaateam.android.braingame.Controllers;

import ir.artaateam.android.braingame.R;

public class EvaluateImageController {
    static EvaluateImageController instance=null;
    final int imageIdTrue = R.drawable.evaluate_true;
    final int imageIdFalse = R.drawable.evaluate_false;

    private EvaluateImageController() {
    }

    public static EvaluateImageController getInstance(){
        if(instance==null){
            instance=new EvaluateImageController();
        }
        return instance;
    }

    public int getTrueId(){
        return imageIdTrue;
    }

    public int getFalseId(){
        return imageIdFalse;
    }
}
