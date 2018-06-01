package com.arch.eric.api;


import com.arch.eric.entity.JokeResultEntity;
import com.arch.eric.entity.MovieSubjectEntity;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by eric on 2017/11/12.
 */

public interface ApiService {
    @GET("http://api.icndb.com/jokes/random/{count}")
    Single<JokeResultEntity> getJokes(@Path("count") int count);


    @GET("https://api.douban.com/v2/movie/in_theaters")
    Single<MovieSubjectEntity> getShowingMovie(@Query("city") String city);
}
