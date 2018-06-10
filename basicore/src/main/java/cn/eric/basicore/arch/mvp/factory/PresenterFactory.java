package cn.eric.basicore.arch.mvp.factory;

import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter;
import cn.eric.basicore.arch.mvp.view.BaseMvpView;

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
