package ir.artaateam.android.braingame;

public class EvaluateImage {
    static EvaluateImage instance=null;
    final int imageIdTrue = R.drawable.evaluate_true;
    final int imageIdFalse = R.drawable.evaluate_false;

    private EvaluateImage() {
    }

    public static EvaluateImage getInstance(){
        if(instance==null){
            instance=new EvaluateImage();
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
