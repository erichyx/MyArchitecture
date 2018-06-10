package cn.eric.basicore.net;


import android.content.Context;

import cn.eric.basicore.app.Configurator;

/**
 * Created by hyx on 2017/5/15.
 */

public class ServiceGenerator
{
    private ServiceGenerator() {
    }

    public static <T> T createService(Class<T> serviceClass)
    {
        Context appContext = Configurator.get().getAppContext();
        return Retrofit2Utils.getInstance(appContext).create(serviceClass);
    }
}
