package ir.artaateam.android.braingame.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentController.showGameFirstFragment(MainActivity.this);
        //FragmentController.showAnimationsFragment(MainActivity.this);
    }


}