package com.arch.eric.app;

import android.app.Application;
import android.content.Context;

import com.arch.eric.R;
import com.arch.eric.data.local.AppDatabase;

import cn.eric.basicore.app.Configurator;
import cn.eric.basicore.app.AppExecutors;

/**
 * Created by hyx on 2017/11/12.
 */

public class BasicApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 进行基础核心库配置
        Configurator.get()
                .appContext(this)
                .baseUrl("https://www.github.com/")
                .imagePlaceHolder(R.drawable.ic_stub, R.drawable.ic_error)
                .configure();

        // 每次启动的时候先去删数据
        AppExecutors.getInstance().diskIO().execute(() -> getDatabase().movieDao().deleteAllMovie());
    }

    public static AppDatabase getDatabase() {
        Context appContext = Configurator.get().getAppContext();
        return AppDatabase.getInstance(appContext);
    }
}
