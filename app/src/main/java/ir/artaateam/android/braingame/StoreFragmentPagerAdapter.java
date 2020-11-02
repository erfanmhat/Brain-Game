package ir.artaateam.android.braingame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class StoreFragmentPagerAdapter extends FragmentPagerAdapter {
    String[] items={"gem","coin","items","free"};
    public StoreFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new GemStoreFragment();
            }
            case 1:{
                return new CoinStoreFragment();
            }
            case 2:{
                return new ItemsStoreFragment();
            }
            case 3:{
                return new FreeStoreFragment();
            }
            default:{
                return new GemStoreFragment();
            }
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
