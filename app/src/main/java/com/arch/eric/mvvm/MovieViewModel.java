package com.arch.eric.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arch.eric.app.BasicApp;
import com.arch.eric.data.local.AppDatabase;
import com.arch.eric.data.local.MovieGenre;
import com.arch.eric.data.local.MovieInfo;
import com.arch.eric.data.local.MovieSubjectEntity;
import com.arch.eric.data.local.MovieSubjectEntity.SubjectsBean;
import com.arch.eric.data.remote.RemoteRepo;

import cn.eric.basicore.arch.mvvm.fetcher.DataFetcher;
import cn.eric.basicore.arch.mvvm.fetcher.Resource;

import java.util.ArrayList;
import java.util.List;

import cn.eric.basicore.arch.mvvm.viewmodel.BaseSimpleViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eric on 2017/11/12.
 */

public class MovieViewModel extends BaseSimpleViewModel<MovieSubjectEntity> {
    private MutableLiveData<MovieSubjectEntity> mObservableMovies;
    private RemoteRepo mRemoteRepo = new RemoteRepo();
    private final AppDatabase mDatabase = BasicApp.getDatabase();

    public LiveData<Resource<List<MovieInfo>>> getShowingMovie(String city, boolean isInit) {
        return new DataFetcher<List<MovieInfo>, MovieSubjectEntity>() {

            @Nullable
            @Override
            protected LiveData<List<MovieInfo>> loadCache() {
                return isInit ? mDatabase.movieDao().getAllMovies() : null;
            }

            @NonNull
            @Override
            protected LiveData<MovieSubjectEntity> createCall() {
                return createRealCall(city);
            }

            @NonNull
            @Override
            protected List<MovieInfo> transform(MovieSubjectEntity source) {
                List<MovieInfo> movieInfos = new ArrayList<>();
                List<SubjectsBean> subjects = source.getSubjects();
                for (SubjectsBean subjectsBean : subjects) {
                    MovieInfo movieInfo = new MovieInfo();
                    movieInfo.setSubject(subjectsBean);
                    movieInfo.setGenres(MovieGenre.transform(subjectsBean.getSubjectId(), subjectsBean.getGenres()));
                    movieInfo.setCasts(subjectsBean.getCasts());
                    movieInfo.setDirectors(subjectsBean.getDirectors());
                    movieInfos.add(movieInfo);
                }
                return movieInfos;
            }

            @Override
            protected void saveResult(List<MovieInfo> item) {
                mDatabase.movieDao().insetAllMovies(item);
            }

            @Override
            public String onFetchFailInfo() {
                return errorMsg;
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
