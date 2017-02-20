package com.example.raunak.justmetrothings;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by RAUNAK on 19-02-2017.
 */

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public SimpleFragmentPageAdapter(Context context, FragmentManager fm){
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return  new LogsFragment();
        }
        else if(position==1){
            return  new InfoFragment();
        }
        else
            return  new SettingsFragment();

    }

    @Override
    public int getCount() {
        return 3;
    }
}
