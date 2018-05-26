package com.arch.eric.api;


import com.arch.eric.entity.JokeResultEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eric on 2017/11/12.
 */

public interface ApiService {
    @GET("random/{count}")
    Single<JokeResultEntity> getJokes(@Path("count") int count);
}
