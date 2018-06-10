package cn.eric.basicore.arch.mvvm.fetcher;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by eric on 2018/6/4
 */
// 带状态的通用数据类
public class Resource<T> {
    public final @Status
    int status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@Status int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SUCCESS, ERROR, LOADING})
    public @interface Status {
    }

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int LOADING = 2;
}
