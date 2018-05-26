package com.arch.eric.utils;

import android.content.Context;


import com.arch.eric.app.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyx on 2017/2/16.
 */

public class Retrofit2Utils
{
    private static Retrofit sRetrofit;

    public static Retrofit getInstance(Context context)
    {
        if (sRetrofit == null)
        {
            synchronized (Retrofit2Utils.class)
            {
                if (sRetrofit == null)
                {
                    sRetrofit = new Retrofit.Builder().baseUrl(Constants.BaseURL)
                            .client(OkHttp3Utils.getOkHttpClientInstance(context))
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return sRetrofit;
    }
}
