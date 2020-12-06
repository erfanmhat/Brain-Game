package ir.artaateam.android.braingame.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.Fragments.AboutUsFragment;
import ir.artaateam.android.braingame.Fragments.AppFirstFragment;
import ir.artaateam.android.braingame.Fragments.AppMainFragment;
import ir.artaateam.android.braingame.Fragments.HowToDoFragment;
import ir.artaateam.android.braingame.Fragments.ItemsAnimationFragment;
import ir.artaateam.android.braingame.Fragments.SettingsFragment;
import ir.artaateam.android.braingame.Fragments.ShowScoreFragment;
import ir.artaateam.android.braingame.Fragments.SingUpFragment;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.Fragments.Game1GameFragment;

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
    }


}