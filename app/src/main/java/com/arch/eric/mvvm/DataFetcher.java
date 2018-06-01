package com.arch.eric.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;


/**
 * Created by eric on 2018/5/31
 */

public abstract class DataFetcher<ResultType, RequestType> {
    // 保存结果到数据库
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    // 判断是否需要从网络加载
    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    // 从数据库中加载
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // 创建API调用
    @NonNull
    @MainThread
    protected abstract LiveData<RequestType> createCall();

    private final MediatorLiveData<ResultType> result = new MediatorLiveData<>();

    @MainThread
    public DataFetcher() {
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            // 此时数据加载完成，移除数据库源监听
            result.removeSource(dbSource);

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                // 到这里说明已经从数据库获取到有效数据
                // 附加数据库源，这里会马上触发setValue调用，同时能响应数据库在别处的修改
                result.addSource(dbSource, result::setValue);
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<RequestType> apiResponse = createCall();
        // 重新附加数据库源，获取数据库最新的变化值
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);

            // 如果还有激活的观察者，说明网络请求还没返回，该数据库变化是由其他地方触发的
            // 那么需要继续添加数据库源
            if (result.hasActiveObservers()) {
                result.addSource(dbSource, result::setValue);
            } else {
                result.setValue(data);
            }
        });

        // 增加网络源监听
        result.addSource(apiResponse, response -> {
            // 移除所有数据源监听
            result.removeSource(apiResponse);

            // 保存结果到数据库
            if (response != null) {
                saveCallResult(response);
            } else {
                onFetchFailed();
            }
        });
    }

    // 失败回调
    @MainThread
    protected void onFetchFailed() {
    }

    // 返回代表请求资源的LiveData
    public final LiveData<ResultType> getAsLiveData() {
        return result;
    }
}
