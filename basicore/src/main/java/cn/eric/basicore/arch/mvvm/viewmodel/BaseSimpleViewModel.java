package cn.eric.basicore.arch.mvvm.viewmodel;

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
public abstract class BaseSimpleViewModel<T> extends ViewModel
        implements LifecycleObserver, SingleObserver<T> {
    protected CompositeDisposable mDisposable = new CompositeDisposable();
    protected String errorMsg;

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
        errorMsg = e.getMessage();
    }
}
