package com.arch.eric.mvp.business;

import com.arch.eric.entity.JokeResultEntity;
import com.arch.eric.mvp.BaseMvpPresenter;
import com.arch.eric.data.remote.RemoteRepo;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eric on 2018/1/7.
 */

public class JokePresenter extends BaseMvpPresenter<JokeView> {

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
                .subscribe(new SingleObserver<JokeResultEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onSuccess(JokeResultEntity entity) {
                        if (mView != null) {
                            mView.setLoadingIndicator(false);
                            mView.showJokes(entity.getJokeList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.setLoadingIndicator(false);
                        }
                    }
                });
    }
}
