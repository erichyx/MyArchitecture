package com.arch.eric.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.arch.eric.data.local.AppDatabase;

/**
 * Created by hyx on 2017/11/12.
 */

public class BasicApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    public static AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    private static AppExecutors mAppExecutors;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        mAppExecutors = new AppExecutors();

        // 每次启动的时候先去删数据
        mAppExecutors.diskIO().execute(() -> getDatabase().movieDao().deleteAllMovie());
    }

    public static AppDatabase getDatabase() {
        return AppDatabase.getInstance(sContext, mAppExecutors);
    }
}
