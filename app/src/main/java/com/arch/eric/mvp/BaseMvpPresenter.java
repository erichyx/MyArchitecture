package com.arch.eric.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arch.eric.mvp.view.BaseMvpView;

import io.reactivex.disposables.Disposable;

/**
 * Created by eric on 2018/1/6.
 */

public abstract class BaseMvpPresenter<V extends BaseMvpView> implements LifecycleObserver {

    protected V mView;
    protected Disposable mDisposable;

    public void inject(V mView) {
        this.mView = mView;
    }

    public V getMvpView() {
        return mView;
    }

    /*@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
    }*/

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        mView = null;
    }
}
