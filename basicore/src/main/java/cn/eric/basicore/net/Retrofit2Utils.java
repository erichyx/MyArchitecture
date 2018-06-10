package cn.eric.basicore.net;

import android.content.Context;

import cn.eric.basicore.app.Configurator;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyx on 2017/2/16.
 */

public class Retrofit2Utils {
    private static Retrofit sRetrofit;

    public static Retrofit getInstance(Context context) {
        if (sRetrofit == null) {
            synchronized (Retrofit2Utils.class) {
                if (sRetrofit == null) {
                    String baseUrl = Configurator.get().getBaseUrl();
                    sRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
                            .client(OkHttp3Utils.getOkHttpClient(context))
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return sRetrofit;
    }
}
