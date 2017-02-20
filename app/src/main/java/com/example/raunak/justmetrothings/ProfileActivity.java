package com.example.raunak.justmetrothings;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by RAUNAK on 20-02-2017.
 */

public class ProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(ProfileActivity.this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
        setupTablayout();
    }

    private  void setupTablayout(){
        tabLayout.getTabAt(0).setIcon(R.drawable.logs_image_changer).setText("LOGS");
        tabLayout.getTabAt(1).setIcon(R.drawable.info_image_changer).setText("INFO");
        tabLayout.getTabAt(2).setIcon(R.drawable.settings_image_changer).setText("SETTINGS");
    }
}
