package com.arch.eric.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arch.eric.mvp.BaseMvpPresenter;
import com.arch.eric.mvp.MvpContact;
import com.arch.eric.mvp.factory.PresenterFactory;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseMvpActivity extends AppCompatActivity implements MvpContact {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        initView();
        onPrepare();
    }

    @Override
    public <V extends BaseMvpView, P extends BaseMvpPresenter<V>> P getPresenter(Class<P> cls, V view) {
        P presenter = PresenterFactory.create(cls, view);
        getLifecycle().addObserver(presenter);
        return presenter;
    }
}
