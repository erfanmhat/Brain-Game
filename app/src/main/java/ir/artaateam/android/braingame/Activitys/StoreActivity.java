package ir.artaateam.android.braingame.Activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ir.artaateam.android.braingame.AdaptersAndHolders.StoreFragmentPagerAdapter;
import ir.artaateam.android.braingame.R;


public class StoreActivity extends AppCompatActivity {
    private static TabLayout storeTabLayout;
    private static ViewPager storeViewPager;
    private StoreFragmentPagerAdapter storeFragmentPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        findViews();
        configure();
    }

    private void findViews() {
        storeTabLayout =findViewById(R.id.store_tab_layout);
        storeViewPager =findViewById(R.id.store_view_pager);
    }

    private void configure() {
        storeFragmentPagerAdapter = new StoreFragmentPagerAdapter(getSupportFragmentManager());
        storeViewPager.setAdapter(storeFragmentPagerAdapter);
        storeTabLayout.setupWithViewPager(storeViewPager);
    }
}
