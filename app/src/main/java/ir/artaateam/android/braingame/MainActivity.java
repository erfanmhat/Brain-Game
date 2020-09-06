package ir.artaateam.android.braingame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showGameFirstFragment();
    }

    private void showGameFirstFragment() {
        AppFirstFragment appFirstFragment = new AppFirstFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, appFirstFragment)
                .commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showGameFirstFragment();
    }
}