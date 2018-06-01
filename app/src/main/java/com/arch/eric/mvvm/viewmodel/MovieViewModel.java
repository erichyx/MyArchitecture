package com.arch.eric.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arch.eric.app.BasicApp;
import com.arch.eric.data.local.AppDatabase;
import com.arch.eric.entity.MovieSubjectEntity;
import com.arch.eric.data.remote.RemoteRepo;
import com.arch.eric.entity.MovieSubjectEntity.SubjectsBean;
import com.arch.eric.mvvm.DataFetcher;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eric on 2017/11/12.
 */

public class MovieViewModel extends BaseViewModel<MovieSubjectEntity> {
    private MutableLiveData<MovieSubjectEntity> mObservableMovies;
    private RemoteRepo mRemoteRepo = new RemoteRepo();
    private final AppDatabase mDatabase = BasicApp.getDatabase();

    public LiveData<List<SubjectsBean>> getShowingMovie(String city) {
        return new DataFetcher<List<SubjectsBean>, MovieSubjectEntity>() {

            @Override
            protected void saveCallResult(@NonNull MovieSubjectEntity item) {
                BasicApp.getAppExecutors().diskIO().execute(() -> {
                    mDatabase.movieDao().insertAll(item.getSubjects());
                });
            }

            @Override
            protected boolean shouldFetch(@Nullable List<SubjectsBean> data) {
                // 写自己的判断逻辑，用于判断数据是否新鲜
                // 这里返回true，让它每次都去请求网络最新数据
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<SubjectsBean>> loadFromDb() {
                return mDatabase.movieDao().getAll();
            }

            @NonNull
            @Override
            protected LiveData<MovieSubjectEntity> createCall() {
                return createRealCall(city);
            }
        }.getAsLiveData();
    }

    private LiveData<MovieSubjectEntity> createRealCall(String city) {
        // 这里必须每次实例化一个新对象，否则会导致再次调用时数据变化的回调累积
        mObservableMovies = new MutableLiveData<>();
        mRemoteRepo.getShowingMovie(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

        return mObservableMovies;
    }

    @Override
    public void onSuccess(MovieSubjectEntity movieSubjectEntity) {
        // 这里设置数据之后，就会引起上方saveCallResult()的回调
        mObservableMovies.setValue(movieSubjectEntity);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mObservableMovies.setValue(null);
    }
}
