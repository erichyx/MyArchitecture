package com.arch.eric.data.remote;

import com.arch.eric.api.ApiService;
import com.arch.eric.api.ServiceGenerator;
import com.arch.eric.entity.JokeResultEntity;
import com.arch.eric.entity.MovieSubjectEntity;

import io.reactivex.Single;

/**
 * Created by eric on 2017/11/12.
 */

public class RemoteRepo {
    public Single<JokeResultEntity> getJokes(int count) {
        return ServiceGenerator.createService(ApiService.class).getJokes(count);
    }

    public Single<MovieSubjectEntity> getShowingMovie(String city) {
        return ServiceGenerator.createService(ApiService.class).getShowingMovie(city);
    }
}
