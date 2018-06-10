package cn.eric.basicore.arch.mvp.uicontroller;

import android.support.annotation.LayoutRes;

import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter;
import cn.eric.basicore.arch.mvp.view.BaseMvpView;

/**
 * Created by eric on 2018/5/30
 */
public interface MvpControlBehavior {
    @LayoutRes
    int getLayoutId();

    void initView();

    void onPrepare();

    <V extends BaseMvpView,P extends BaseMvpPresenter<V>> P getPresenter(Class<P> cls, V view);
}
