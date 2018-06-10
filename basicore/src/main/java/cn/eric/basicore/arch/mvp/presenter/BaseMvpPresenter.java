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

public abstract class BaseMvpPresenter<V extends BaseMvpView> implements LifecycleObserver {

    protected V mView;
    protected CompositeDisposable mDisposable = new CompositeDisposable();

    public void inject(V mView) {
        this.mView = mView;
    }

    public V getMvpView() {
        return mView;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        mDisposable.clear();
        mView = null;
    }
}
