package cn.eric.basicore.arch.observer;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by eric on 2018/1/7.
 */

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private CompositeDisposable mCompositeDisposable;
    protected String errorMsg;

    public BaseSingleObserver(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        errorMsg = e.getMessage();
    }
}
