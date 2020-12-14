package ir.artaateam.android.braingame.Controllers;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import ir.artaateam.android.braingame.Fragments.AboutUsFragment;
import ir.artaateam.android.braingame.Fragments.AnimationsFragment;
import ir.artaateam.android.braingame.Fragments.AppFirstFragment;
import ir.artaateam.android.braingame.Fragments.AppMainFragment;
import ir.artaateam.android.braingame.Fragments.GameShapeAndColorFragment;
import ir.artaateam.android.braingame.Fragments.HowToDoFragment;
import ir.artaateam.android.braingame.Fragments.ItemsAnimationFragment;
import ir.artaateam.android.braingame.Fragments.SettingsFragment;
import ir.artaateam.android.braingame.Fragments.ShowScoreFragment;
import ir.artaateam.android.braingame.Fragments.SingUpFragment;
import ir.artaateam.android.braingame.R;

public class FragmentController {
    public static void showGameFirstFragment(@NonNull Activity activity) {
        AppFirstFragment appFirstFragment = new AppFirstFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, appFirstFragment)
                .commit();
    }

    public static void showAppMainFragment(@NonNull Activity activity) {
        AppMainFragment appMainFragment = new AppMainFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, appMainFragment)
                .commit();
    }

    public static void showGameShapeAndColorFragment(@NonNull Activity activity) {
        GameShapeAndColorFragment gameShapeAndColorFragment = new GameShapeAndColorFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, gameShapeAndColorFragment)
                .commit();
    }

    public static void showSingUpFragment(@NonNull Activity activity) {
        SingUpFragment singUpFragment = new SingUpFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, singUpFragment)
                .commit();
    }

    public static void showShowScoreFragment(@NonNull Activity activity, int scoreInt, boolean isNewBestScore) {
        ShowScoreFragment showScoreFragment = new ShowScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("score", scoreInt);
        bundle.putBoolean("isNewBestScore", isNewBestScore);
        showScoreFragment.setArguments(bundle);

        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, showScoreFragment)
                .commit();
    }

    public static void showAboutUsFragment(@NonNull Activity activity) {
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, aboutUsFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showHowToDoFragment(@NonNull Activity activity) {
        HowToDoFragment howToDoFragment = new HowToDoFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, howToDoFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showSettingsFragment(@NonNull Activity activity) {
        SettingsFragment settingsFragment = new SettingsFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.settings_frame, settingsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showItemsAnimationFragment(@NonNull Activity activity) {
        ItemsAnimationFragment itemsAnimationFragment = new ItemsAnimationFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, itemsAnimationFragment, null)
                .addToBackStack(null)
                .commit();
    }

    public static void showAnimationsFragment(@NonNull Activity activity) {
        AnimationsFragment animationsFragment = new AnimationsFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, animationsFragment)
                .commit();
    }
}
