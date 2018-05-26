package com.arch.eric.mvp.proxy;

import com.arch.eric.mvp.BaseMvpPresenter;
import com.arch.eric.mvp.factory.PresenterFactory;
import com.arch.eric.mvp.view.BaseMvpView;

/**
 * Created by eric on 2018/5/25
 */
public interface PresenterFactoryProxy {
     <V extends BaseMvpView,P extends BaseMvpPresenter<V>> P getPresenter(Class<P> cls, V view);
}
