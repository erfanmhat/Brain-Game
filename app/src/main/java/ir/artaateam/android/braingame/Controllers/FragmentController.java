package ir.artaateam.android.braingame.Controllers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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

    public static void removeFragment(FragmentActivity activity, Fragment fragment) {
        if (fragment == null) return;
        activity.getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    public static void showFirstFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = FirstFragment;
        FirstFragment firstFragment = new FirstFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, firstFragment)
                .commit();
    }

    public static void showMainFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = MainFragment;
        MainFragment mainFragment = new MainFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, mainFragment)
                .commit();
    }

    public static void showGameShapeAndColorFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = GameShapeAndColorFragment;
        GameShapeAndColorFragment gameShapeAndColorFragment = new GameShapeAndColorFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, gameShapeAndColorFragment)
                .commit();
    }

    public static void showSingUpFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = SingUpFragment;
        SingUpFragment singUpFragment = new SingUpFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, singUpFragment)
                .commit();
    }

    public static void showShowScoreFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = ShowScoreFragment;
        ShowScoreFragment showScoreFragment = new ShowScoreFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, showScoreFragment)
                .commit();
    }

    public static void showAboutUsFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = AboutUsFragment;
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, aboutUsFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showHowToDoFragment(@NonNull FragmentActivity activity) {
        fragmentsStatus = HowToDoFragment;
        HowToDoFragment howToDoFragment = new HowToDoFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, howToDoFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void showSettingsFragment(@NonNull FragmentActivity activity) {
        SettingsFragment settingsFragment = new SettingsFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.settings_frame, settingsFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showItemsAnimationFragment(@NonNull FragmentActivity activity) {
        ItemsAnimationFragment itemsAnimationFragment = new ItemsAnimationFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, itemsAnimationFragment, null)
                .addToBackStack(null)
                .commit();
    }

    public static void showAnimationsFragment(@NonNull FragmentActivity activity) {
        AnimationsFragment animationsFragment = new AnimationsFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame, animationsFragment)
                .commit();
    }
}
