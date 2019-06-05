package com.lcz.mua.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mRootView;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (registerEventBus()) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(initViewId(), null);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView();
        initTitle();
        initViewListener();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        process(savedInstanceState);
    }

    protected abstract int initViewId();

    protected abstract void process(Bundle savedInstanceState);

    protected void initView() {

    }

    protected void initTitle() {
    }

    protected void initViewListener() {

    }

    protected boolean registerEventBus() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (registerEventBus()) {

        }
    }

}
