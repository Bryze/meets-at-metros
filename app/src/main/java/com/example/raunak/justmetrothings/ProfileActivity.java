package com.example.raunak.justmetrothings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by RAUNAK on 20-02-2017.
 */

public class ProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    private FirebaseAuth firebase_auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebase_auth =FirebaseAuth.getInstance();
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(ProfileActivity.this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(3);
        setupTablayout();
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowup = getMenuInflater();
        blowup.inflate(R.menu.profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out : firebase_auth.signOut();
                finish();
                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);
        }
        return true;
    }

    private  void setupTablayout(){
        tabLayout.getTabAt(0).setIcon(R.drawable.logs_image_changer).setText("LOGS");
        tabLayout.getTabAt(1).setIcon(R.drawable.info_image_changer).setText("INFO");
        tabLayout.getTabAt(2).setIcon(R.drawable.settings_image_changer).setText("SETTINGS");
    }
}
