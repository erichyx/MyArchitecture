package cn.eric.basicore.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by hyx on 2017/6/9.
 */

public class GsonContext {
    private static volatile Gson sInstance;

    public static Gson getInstance() {
        return sInstance;
    }

    private GsonContext() {
    }

    public static Gson getGson() {
        if (sInstance == null) {
            synchronized (GsonContext.class) {
                if (sInstance == null) {
                    sInstance = new GsonBuilder()
                            .registerTypeAdapter(Integer.class, new IntegerAdapter())
                            .registerTypeAdapter(int.class, new IntegerAdapter())
                            .registerTypeAdapter(Long.class, new LongAdapter())
                            .registerTypeAdapter(long.class, new LongAdapter())
                            .registerTypeAdapter(Double.class, new DoubleAdapter())
                            .registerTypeAdapter(double.class, new DoubleAdapter())
                            .create();
                }
            }
        }
        return sInstance;
    }
}
