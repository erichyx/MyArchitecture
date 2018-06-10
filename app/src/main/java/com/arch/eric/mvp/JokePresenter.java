package com.arch.eric.mvp;

import com.arch.eric.entity.JokeEntity;
import com.arch.eric.entity.JokeResultEntity;

import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter;
import com.arch.eric.data.remote.RemoteRepo;

import cn.eric.basicore.arch.mvp.presenter.BaseSimpleMvpPresenter;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eric on 2018/1/7.
 */

public class JokePresenter extends BaseSimpleMvpPresenter<JokeView, JokeResultEntity> {

    private RemoteRepo mJokeRepo;

    public JokePresenter() {
        mJokeRepo = new RemoteRepo();
    }

    public void fetchJokes(int count) {
        if (mView != null) {
            mView.setLoadingIndicator(true);
        }

        mJokeRepo.getJokes(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSuccess(JokeResultEntity jokeResultEntity) {
        if (mView != null) {
            mView.setLoadingIndicator(false);
            mView.showJokes(jokeResultEntity.getJokeList());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mView != null) {
            mView.setLoadingIndicator(false);
        }
    }
}
