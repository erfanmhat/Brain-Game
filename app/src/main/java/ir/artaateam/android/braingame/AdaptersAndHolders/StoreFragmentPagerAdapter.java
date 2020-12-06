package ir.artaateam.android.braingame.AdaptersAndHolders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ir.artaateam.android.braingame.Fragments.CoinStoreFragment;
import ir.artaateam.android.braingame.Fragments.FreeStoreFragment;
import ir.artaateam.android.braingame.Fragments.GemStoreFragment;
import ir.artaateam.android.braingame.Fragments.ItemsStoreFragment;

public class StoreFragmentPagerAdapter extends FragmentPagerAdapter {
    String[] items={"gem","coin","items","free"};
    public StoreFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GemStoreFragment();
        } else if (position == 1) {
            return new CoinStoreFragment();
        } else if (position == 2) {
            return new ItemsStoreFragment();
        } else {
            return new FreeStoreFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return items[position];
    }
}
