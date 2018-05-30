package com.arch.eric.mvp;

import android.support.annotation.LayoutRes;

import com.arch.eric.mvp.view.BaseMvpView;

/**
 * Created by eric on 2018/5/30
 */
public interface MvpContact {
    @LayoutRes
    int getLayoutId();

    void initView();

    void onPrepare();

    <V extends BaseMvpView,P extends BaseMvpPresenter<V>> P getPresenter(Class<P> cls, V view);
}
