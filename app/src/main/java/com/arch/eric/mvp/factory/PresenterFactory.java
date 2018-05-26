package com.arch.eric.mvp.factory;

import com.arch.eric.mvp.BaseMvpPresenter;
import com.arch.eric.mvp.view.BaseMvpView;

import java.lang.reflect.Constructor;

/**
 * Created by eric on 2018/5/25
 */
public class PresenterFactory {

    public static <V extends BaseMvpView, P extends BaseMvpPresenter<V>> P create(Class<P> cls, V view) {
        try {
            P p = cls.newInstance();
            p.inject(view);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(cls.getName() + " can't be instantiated");
        }
    }
}
