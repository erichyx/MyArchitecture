package com.arch.eric.api;


import com.arch.eric.app.BasicApp;
import com.arch.eric.utils.Retrofit2Utils;

/**
 * Created by hyx on 2017/5/15.
 */

public class ServiceGenerator
{
    private ServiceGenerator() {
    }

    public static <T> T createService(Class<T> serviceClass)
    {
        return Retrofit2Utils.getInstance(BasicApp.getContext()).create(serviceClass);
    }
}
