package com.lcz.myutilapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lcz.myutilapp.R;
import com.lcz.myutilapp.base.BaseFragment;
import com.lcz.myutilapp.ui.activity.FingerActivity;
import com.lcz.myutilapp.ui.adapter.MainRecyclerViewAdapter;
import com.lcz.myutilapp.util.SecurityUtil;

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
                switch (position) {
                    case 0:
                        if (SecurityUtil.isPasswordSet()) {
                            askPassword();
                        } else {
                            startActivity(new Intent(mContext, FingerActivity.class));
                        }
                        break;
                }
            }
        });
    }

    private void askPassword() {
        SecurityUtil.authenticateUser(getActivity(), new SecurityUtil.AuthCallBack() {
            @Override
            public void onAuthenticated() {
                startActivity(new Intent(mContext, FingerActivity.class));
            }

            @Override
            public void onError() {
                Toast.makeText(mContext, R.string.wrong_password, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        mStringList.add("Android 指纹");
    }

}
