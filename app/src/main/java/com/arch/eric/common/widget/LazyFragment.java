package com.arch.eric.common.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eric on 2017/11/13.
 */

public abstract class LazyFragment extends Fragment
{
    private boolean mIsPrepared;

    public void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared) {
            //异步初始化，在初始化后显示正常UI
            loadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mIsPrepared = true;
        return rootView;
    }

    protected abstract void loadData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }
}
