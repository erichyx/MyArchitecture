package com.arch.eric.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arch.eric.mvp.BaseMvpPresenter;
import com.arch.eric.mvp.proxy.PresenterFactoryProxy;
import com.arch.eric.mvp.proxy.RealPresenterFactoryFactoryProxy;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseMvpFragment extends Fragment implements PresenterFactoryProxy {

    private Unbinder mUnbinder;
    private PresenterFactoryProxy mPresenterFactoryProxy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mPresenterFactoryProxy = new RealPresenterFactoryFactoryProxy();

        initView();
        onPrepare();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView();

    protected abstract void onPrepare();

    @Override
    public <V extends BaseMvpView, P extends BaseMvpPresenter<V>> P getPresenter(Class<P> cls, V view) {
        P presenter = mPresenterFactoryProxy.getPresenter(cls, view);
        getLifecycle().addObserver(presenter);
        return presenter;
    }
}
