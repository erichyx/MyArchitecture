package com.arch.eric.repository;

import com.arch.eric.api.ApiService;
import com.arch.eric.api.ServiceGenerator;
import com.arch.eric.entity.JokeResultEntity;

import io.reactivex.Single;

/**
 * Created by eric on 2017/11/12.
 */

public class JokeRepo
{
    public Single<JokeResultEntity> getJokes(int count)
    {
        return ServiceGenerator.createService(ApiService.class).getJokes(count);
    }
}
