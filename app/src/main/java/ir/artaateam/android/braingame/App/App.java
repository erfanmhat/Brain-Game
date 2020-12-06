package ir.artaateam.android.braingame.App;

import android.util.Log;
import android.widget.Toast;

public class App {
    public static void t(String massage){
        Toast.makeText(MyApplication.getContext(),massage,Toast.LENGTH_SHORT).show();
    }
    public static void l(String massage){
        Log.e("LLLL",massage);
    }
}
