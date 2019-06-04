package com.lcz.myutilapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcz.myutilapp.R;
import com.lcz.myutilapp.base.BaseFragment;
import com.lcz.myutilapp.ui.adapter.MainRecyclerViewAdapter;
import com.lcz.myutilapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class FragmentHome extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private List<String> mStringList = new ArrayList<>();
    private MainRecyclerViewAdapter mAdapter;

    public static BaseFragment getInstance() {
        FragmentHome fragmentHome = new FragmentHome();
        return fragmentHome;
    }

    @Override
    protected int initViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MainRecyclerViewAdapter(mContext, mStringList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initViewListener() {
        mAdapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(String s, int position) {
                ToastUtil.show(mContext, s);
            }
        });
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        mStringList.add("Android 指纹");

    }

}
