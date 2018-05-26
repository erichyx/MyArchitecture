package com.arch.eric.mvp.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arch.eric.mvp.BaseMvpPresenter;
import com.arch.eric.mvp.factory.PresenterFactory;
import com.arch.eric.mvp.proxy.RealPresenterFactoryFactoryProxy;
import com.arch.eric.mvp.proxy.PresenterFactoryProxy;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseMvpActivity extends AppCompatActivity implements PresenterFactoryProxy {

    private PresenterFactoryProxy mPresenterFactoryProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        mPresenterFactoryProxy = new RealPresenterFactoryFactoryProxy();

        initView();
        onPrepare();
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
