package com.arch.eric.mvvm.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by eric on 2018/5/26
 */
public abstract class BaseViewModel<T> extends ViewModel
        implements LifecycleObserver, SingleObserver<T> {
    protected CompositeDisposable mDisposable = new CompositeDisposable();
    // 请求的标记，用于区分错误回调
    private String mReqTag = "ReqTag";
    private FailureCallback mCallback;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable.add(d);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void clear() {
        mDisposable.clear();
    }

    @Override
    public void onError(Throwable e) {
        if (mCallback != null) {
            mCallback.onFailure(mReqTag, e.getMessage());
        }
    }

    public void setFailureCallback(String reqTag, FailureCallback callback) {
        mReqTag = reqTag;
        mCallback = callback;
    }

    public interface FailureCallback {
        void onFailure(String reqTag, String error);
    }
}
