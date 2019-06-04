package com.lcz.myutilapp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcz.myutilapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private BaseFragment currentFragment;


    public MainFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragmentList = fragments;
        currentFragment = mFragmentList.get(0);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

}
