package com.lcz.myutilapp.ui.fragment;

import android.os.Bundle;

import com.lcz.myutilapp.R;
import com.lcz.myutilapp.base.BaseFragment;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class FragmentQMUI extends BaseFragment {

    public static BaseFragment getInstance() {
        FragmentQMUI fragmentQMUI = new FragmentQMUI();
        return fragmentQMUI;
    }

    @Override
    protected int initViewId() {
        return R.layout.fragment_qmui;
    }

    @Override
    protected void process(Bundle savedInstanceState) {

    }
}