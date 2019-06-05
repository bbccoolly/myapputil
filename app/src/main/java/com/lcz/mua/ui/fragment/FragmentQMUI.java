package com.lcz.mua.ui.fragment;

import android.os.Bundle;

import com.lcz.mua.R;
import com.lcz.mua.base.BaseFragment;

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
