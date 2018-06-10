package cn.eric.basicore.arch.mvp.uicontroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import cn.eric.basicore.arch.mvp.factory.PresenterFactory;
import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter;
import cn.eric.basicore.arch.mvp.view.BaseMvpView;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseMvpActivity extends AppCompatActivity implements MvpControlBehavior {

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
