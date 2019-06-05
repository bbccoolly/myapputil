package com.lcz.myutilapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lcz.myutilapp.R;
import com.lcz.myutilapp.base.APIService;
import com.lcz.myutilapp.base.BaseFragment;
import com.lcz.myutilapp.entity.Repo;
import com.lcz.myutilapp.retrofit.gson.GsonConverterFactory;
import com.lcz.myutilapp.ui.activity.FingerActivity;
import com.lcz.myutilapp.ui.adapter.MainRecyclerViewAdapter;
import com.lcz.myutilapp.util.SecurityUtil;
import com.lcz.myutilapp.util.Trace;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class FragmentHome extends BaseFragment {

    private static final String TAG = "FragmentHome";

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
                    case 1:
                        initRetrofit();
                        break;
                }
            }
        });
    }

    private void initRetrofit() {
        String str = "https://api.github.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(str)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<Repo>> repos = service.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Trace.getTracer().d(TAG,"onResponse - "+response.message());
                Trace.getTracer().d(TAG,"onResponse - "+response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Trace.getTracer().d(TAG,"onFailure - ");
            }
        });
        repos.request();
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
        mStringList.add("Retrofit2 请求网络");
    }

}
