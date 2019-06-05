package com.lcz.mua.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lcz.mua.R;
import com.lcz.mua.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class Test extends BaseActivity {
    @BindView(R.id.textView2)
    TextView textView2;
    private Unbinder mBind;

    @Override
    protected int initViewId() {
        return R.layout.activity_test;
    }

    @Override
    protected void process(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
