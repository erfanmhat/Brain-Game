package ir.artaateam.android.braingame.Controllers;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;

import ir.artaateam.android.braingame.Enums.FragmentsStatus;
import ir.artaateam.android.braingame.Fragments.AboutUsFragment;
import ir.artaateam.android.braingame.Fragments.AnimationsFragment;
import ir.artaateam.android.braingame.Fragments.FirstFragment;
import ir.artaateam.android.braingame.Fragments.MainFragment;
import ir.artaateam.android.braingame.Fragments.GameShapeAndColorFragment;
import ir.artaateam.android.braingame.Fragments.HowToDoFragment;
import ir.artaateam.android.braingame.Fragments.ItemsAnimationFragment;
import ir.artaateam.android.braingame.Fragments.SettingsFragment;
import ir.artaateam.android.braingame.Fragments.ShowScoreFragment;
import ir.artaateam.android.braingame.Fragments.SingUpFragment;
import ir.artaateam.android.braingame.R;

import static ir.artaateam.android.braingame.Enums.FragmentsStatus.*;

public class FragmentController {
    private static FragmentsStatus fragmentsStatus = NULLFragment;

    public static FragmentsStatus getFragmentsStatus() {
        return fragmentsStatus;
    }

    public static void removeFragment(Activity activity, Fragment fragment) {
        if (fragment == null) return;
        activity.getFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    public static void showFirstFragment(@NonNull Activity activity) {
        fragmentsStatus = FirstFragment;
        ir.artaateam.android.braingame.Fragments.FirstFragment firstFragment = new FirstFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, firstFragment)
                .commit();
    }

    public static void showMainFragment(@NonNull Activity activity) {
        fragmentsStatus = MainFragment;
        ir.artaateam.android.braingame.Fragments.MainFragment mainFragment = new MainFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, mainFragment)
                .commit();
    }

    public static void showGameShapeAndColorFragment(@NonNull Activity activity) {
        fragmentsStatus = GameShapeAndColorFragment;
        GameShapeAndColorFragment gameShapeAndColorFragment = new GameShapeAndColorFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, gameShapeAndColorFragment)
                .commit();
    }

    public static void showSingUpFragment(@NonNull Activity activity) {
        fragmentsStatus = SingUpFragment;
        SingUpFragment singUpFragment = new SingUpFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, singUpFragment)
                .commit();
    }

    public static void showShowScoreFragment(@NonNull Activity activity, int scoreInt, boolean isNewBestScore) {
        fragmentsStatus = ShowScoreFragment;
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
        fragmentsStatus = AboutUsFragment;
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, aboutUsFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showHowToDoFragment(@NonNull Activity activity) {
        fragmentsStatus = HowToDoFragment;
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
