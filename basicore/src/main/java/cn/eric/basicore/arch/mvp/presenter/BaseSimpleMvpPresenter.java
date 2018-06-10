package cn.eric.basicore.arch.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import cn.eric.basicore.arch.mvp.view.BaseMvpView;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseSimpleMvpPresenter<V extends BaseMvpView, T> extends BaseMvpPresenter<V>
        implements SingleObserver<T> {

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable.add(d);
    }
}
