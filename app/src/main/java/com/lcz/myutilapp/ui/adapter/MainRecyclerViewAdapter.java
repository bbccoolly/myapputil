package com.lcz.myutilapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcz.myutilapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List<String> mStrings;

    public MainRecyclerViewAdapter(Context context, List<String> strings) {
        mContext = context;
        mStrings = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.model_recycler_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (mStrings != null && mStrings.size() > 0) {
            viewHolder.mTv.setText(mStrings.get(i));
            viewHolder.mAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClickListener(mStrings.get(i), i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStrings == null ? 0 : mStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mTv)
        TextView mTv;
        @BindView(R.id.mAction)
        RelativeLayout mAction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(String s, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setDataAndNotify(List<String> mEntityList) {
        this.mStrings = mEntityList;
        notifyDataSetChanged();
    }
}
