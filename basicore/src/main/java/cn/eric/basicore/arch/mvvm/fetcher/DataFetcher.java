package cn.eric.basicore.arch.mvvm.fetcher;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cn.eric.basicore.app.AppExecutors;


/**
 * Created by eric on 2018/5/31
 */

public abstract class DataFetcher<ResultType, RequestType> {
    private static final String TAG = "DataFetcher";

    // 从缓存中加载
    @Nullable
    @MainThread
    protected abstract LiveData<ResultType> loadCache();

    // 创建API调用
    @NonNull
    @MainThread
    protected abstract LiveData<RequestType> createCall();

    @NonNull
    @MainThread
    protected abstract ResultType transform(RequestType source);

    @WorkerThread
    protected abstract void saveResult(@NonNull ResultType item);

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private static final Executor sExecutor = AppExecutors.getInstance().diskIO();

    @MainThread
    public DataFetcher() {
        // 加载中
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadCache();
        if (dbSource != null) {
            result.addSource(dbSource, data -> {
                Log.d(TAG, "获取到缓存数据" + data);
                // 此时数据加载完成，移除缓存源监听
                result.removeSource(dbSource);

                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource);
                } else {
                    // 从缓存数据中获取到有效数据
                    Log.d(TAG, "只显示缓存数据" + data);
                    result.setValue(Resource.success(data));
                }
            });
        } else {
            fetchFromNetwork(null);
        }
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {

        // 重新附加缓存数据源，获取最新的变化值
        if (dbSource != null) {
            result.addSource(dbSource, newData -> {
                Log.d(TAG, "显示缓存数据" + newData);
                result.setValue(Resource.loading(newData));
            });
        }

        LiveData<RequestType> apiResponse = createCall();
        // 增加网络源监听
        result.addSource(apiResponse, response -> {
            // 移除网络源监听
            result.removeSource(apiResponse);
            if (dbSource != null) {
                result.removeSource(dbSource); // 网络数据返回之后移除数据源
            }

            Log.d(TAG, "获取到网络数据" + response);
            if (response == null) {
                result.setValue(Resource.error(onFetchFailInfo(), null));
                return;
            }

            ResultType data = transform(response);
            sExecutor.execute(() -> {
                Log.d(TAG, "保存网络数据" + data);
                saveResult(data);
                result.postValue(Resource.success(data));
            });
        });
    }

    // 默认都从网络加载，子类可以重写此行为
    @MainThread
    protected boolean shouldFetch(@Nullable ResultType data) {
        return true;
    }

    // 获取失败信息
    @MainThread
    public abstract String onFetchFailInfo();

    // 返回代表请求资源的LiveData
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
