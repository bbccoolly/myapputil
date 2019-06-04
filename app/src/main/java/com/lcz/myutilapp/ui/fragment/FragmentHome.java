package com.lcz.myutilapp.ui.fragment;

import android.os.Bundle;

import com.lcz.myutilapp.R;
import com.lcz.myutilapp.base.BaseFragment;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class FragmentHome extends BaseFragment {

    public static BaseFragment getInstance() {
        FragmentHome fragmentHome = new FragmentHome();
        return fragmentHome;
    }

    @Override
    protected int initViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void process(Bundle savedInstanceState) {

    }
}
