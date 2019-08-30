package com.supernova.features.onboarding.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.supernova.features.onboarding.fragment.onBoardingFragment;

public class ScreenSliderPagerAdapter extends FragmentPagerAdapter {

    public ScreenSliderPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return onBoardingFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
