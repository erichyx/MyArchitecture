package com.arch.eric.mvp;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by eric on 2018/1/7.
 */

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
