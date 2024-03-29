package com.lcz.mua.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initViewId());
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initTitle();
        initViewListener();
        process(savedInstanceState);
    }


    protected abstract int initViewId();

    protected abstract void process(Bundle savedInstanceState);

    protected void initView() {

    }

    private void initViewListener() {

    }

    protected void initTitle() {

    }

}
