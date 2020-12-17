package ir.artaateam.android.braingame.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Enums.FragmentsStatus;
import ir.artaateam.android.braingame.Fragments.GameShapeAndColorFragment;
import ir.artaateam.android.braingame.R;

public class MainActivity extends AppCompatActivity {
    private boolean showFirstFragment = true;

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
        if (showFirstFragment) {
            FragmentController.showFirstFragment(MainActivity.this);
            showFirstFragment = false;
        } else if (FragmentController.getFragmentsStatus() == FragmentsStatus.GameShapeAndColorFragment) {
            FragmentController.showShowScoreFragment(
                    MainActivity.this,
                    GameShapeAndColorFragment.getScoreInt(),
                    GameShapeAndColorFragment.getIsNewBestScore());
        } else {
            FragmentController.showMainFragment(MainActivity.this);
        }
    }
}