package com.arch.eric.mvvm.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;

import com.arch.eric.entity.JokeEntity;
import com.arch.eric.entity.JokeResultEntity;
import com.arch.eric.repository.JokeRepo;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eric on 2017/11/12.
 */

public class JokeViewModel extends BaseViewModel<JokeResultEntity>
{
    private MutableLiveData<List<JokeEntity>> mObservableJokes;
    private JokeRepo mJokeRepo = new JokeRepo();
    private boolean mRefreshing = false;

    public boolean isRefreshing() {
        return mRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        mRefreshing = refreshing;
    }

    public LiveData<List<JokeEntity>> getJokes(int count)
    {
        if (mObservableJokes == null) {
            mObservableJokes = new MutableLiveData<>();
        }

        mRefreshing = true;
        mJokeRepo.getJokes(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
        return mObservableJokes;
    }

    @Override
    public void onSuccess(JokeResultEntity jokeResultEntity) {
        mObservableJokes.setValue(jokeResultEntity.getJokeList());
        mRefreshing = false;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mRefreshing = false;
    }
}
