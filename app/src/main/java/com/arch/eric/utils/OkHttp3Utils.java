package com.arch.eric.utils;

import android.content.Context;



import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by hyx on 2017/2/16.
 */

public class OkHttp3Utils {
    private static volatile OkHttp3Utils sInstance;
    private static volatile OkHttpClient sClient = null;
    private final String TAG = OkHttp3Utils.class.getSimpleName();

    private OkHttp3Utils(Context context) {
        getOkHttpClientInstance(context);
    }

    /**
     * 获取工具类实例
     *
     * @param context 上下文对象
     * @return OkHttp3Utils实例
     */
    public static OkHttp3Utils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OkHttp3Utils.class) {
                if (sInstance == null) {
                    sInstance = new OkHttp3Utils(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取okHttpClient的单实例
     *
     * @param context 上下文对象
     * @return OkHttpClient实例
     */
    public static OkHttpClient getOkHttpClientInstance(Context context) {
        if (sClient == null) {
            synchronized (OkHttpClient.class) {
                if (sClient == null) {
                    //设置缓存目录和大小
                    int cacheSize = 256 << 20; // 256 MB
                    File cacheFile = new File(context.getCacheDir(), "responses");
                    Cache cache = new Cache(cacheFile, cacheSize);

                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS).cache(cache);
                    //      .addInterceptor(new BaseInterceptor())
                    //      .addNetworkInterceptor(new NetworkBaseInterceptor())

  /*                  if (BuildConfig.DEBUG) // 只在调试版本添加日志拦截器
                    {
                        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                //打印retrofit日志
                                LogUtils.d("RetrofitLog:retrofitBack = " + message);
                            }
                        }).setLevel(HttpLoggingInterceptor.Level.BODY));
                    }*/

                    sClient = builder.build();
                }
            }
        }
        return sClient;
    }

    /*
    static class NetworkBaseInterceptor implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);

            String serverCache = originalResponse.header("Cache-Control");
            if (TextUtils.isEmpty(serverCache)) //服务端无缓存设置
            {
                String cacheControl = request.cacheControl().toString();
                Response resp = originalResponse.newBuilder()
                        .addHeader("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
                return resp;
            }
            else
            {
                // 如果服务端设置相应的缓存策略，那么直接使用服务端的设置
                return originalResponse;
            }
        }
    }*/

/*    // 拦截器，用于添加Http Header
    static class BaseInterceptor implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();
            request = request.newBuilder()
                    .header("s0", "")
                    .header("s1", "")
                    .header("s2", "")
                    .method(request.method(), request.body())
                    .build();


            return chain.proceed(request);
        }
    }*/
}
