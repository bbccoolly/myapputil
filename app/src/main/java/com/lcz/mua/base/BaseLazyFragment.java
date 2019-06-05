package com.lcz.mua.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public abstract class BaseLazyFragment extends BaseFragment {
    private boolean hasCreateView;
    private boolean isVisible;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hasCreateView = true;
        firstLoadData();
    }

    private void firstLoadData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (hasCreateView) {
            onFragmentVisibleChanged(isVisibleToUser);
            isVisible = isVisibleToUser;
        }
    }

    public void onFragmentVisibleChanged(boolean isVisible) {

    }

    public boolean isCurrentVisible() {
        return isVisible;
    }

    public void setCurrentVisible(boolean flag) {
        this.isVisible = flag;
    }
}
